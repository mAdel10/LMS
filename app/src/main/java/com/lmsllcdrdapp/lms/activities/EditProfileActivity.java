package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.EditProfileForm;
import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.UpdateProfileOperation;
import com.lmsllcdrdapp.lms.backend.operations.UserProfileOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;

public class EditProfileActivity extends BaseActivity implements View.OnClickListener, RequestObserver {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private Button editProfileBtb;

    private String firstName;
    private String lastName;
    private String phone;

    private User user;
    private static final int REQUEST_EDIT_PROFILE = 1;
    private static final int REQUEST_REFRESH_PROFILE = 2;

    public EditProfileActivity() {
        super(R.layout.activity_edit_profile, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getText(R.string.edit_profile));

        firstNameEditText = findViewById(R.id.edit_profile_firstName_editText);
        lastNameEditText = findViewById(R.id.edit_profile_lastName_editText);
        phoneNumberEditText = findViewById(R.id.edit_profile_phone_editText);
        editProfileBtb = findViewById(R.id.edit_profile_button);
        editProfileBtb.setOnClickListener(this);

        User user = UserManager.getInstance().getCurrentUser();
        fillData(user);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_profile_button) {
            if (getInputData()) {
                updateProfile();
            }
        }
    }

    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {
        if (error != null) {
            if (error instanceof CTHttpError) {
                int code = ((CTHttpError) error).getStatusCode();
                String errorMsg = ((CTHttpError) error).getErrorMsg();
                if (code == -1 || Utilities.isNullString(errorMsg)) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), this.getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(this.getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (resultObject != null && requestId.equals(REQUEST_EDIT_PROFILE)) {
            refreshProfile();

        } else if (resultObject != null && requestId.equals(REQUEST_REFRESH_PROFILE)) {
            User user = (User) resultObject;
            switch (user.getUserType()) {
                case User.TYPE_STUDENT:
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case User.TYPE_INSTRUCTOR:
                    i = new Intent(this, InstructorMainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case User.TYPE_CENTER:
                    i = new Intent(this, CenterMainActivity.class);
                    startActivity(i);
                    finish();
                    break;

            }
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    private void fillData(User user) {
        if (UserManager.getInstance().isStudent()) {
            firstNameEditText.setText(user.getStudent().getFirstName());
            lastNameEditText.setText(user.getStudent().getLastName());
        }
        if (UserManager.getInstance().isInstructor()) {
            firstNameEditText.setText(user.getInstructor().getFirstName());
            lastNameEditText.setText(user.getInstructor().getLastName());
        }
        if (UserManager.getInstance().isCenter()) {
            firstNameEditText.setText(user.getCenter().getName());
        }
        phoneNumberEditText.setText(user.getPhone());
    }

    private boolean getInputData() {
        firstName = firstNameEditText.getText().toString().trim();
        lastName = lastNameEditText.getText().toString().trim();
        phone = phoneNumberEditText.getText().toString().trim();

        if (firstName.isEmpty()) {
            firstNameEditText.setError("First name required");
            return false;
        } else if (lastName.isEmpty()) {
            lastNameEditText.setError("Last name required");
            return false;
        } else if (phone.isEmpty()) {
            phoneNumberEditText.setError("phone number required");
            return false;
        }

        return true;
    }

    private void updateProfile() {
        EditProfileForm editProfileForm = new EditProfileForm(firstName, lastName, phone);
        UpdateProfileOperation operation = new UpdateProfileOperation(editProfileForm, REQUEST_EDIT_PROFILE, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }


    private void refreshProfile() {
        UserProfileOperation operation = new UserProfileOperation(REQUEST_REFRESH_PROFILE, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}
