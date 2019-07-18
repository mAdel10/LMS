package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CentersOperation;
import com.lmsllcdrdapp.lms.backend.operations.InstructorSignUpOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.InputValidator;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.lmsllcdrdapp.lms.views.AppSpinnerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstructorSignUpActivity extends BaseActivity implements RequestObserver, AdapterView.OnItemSelectedListener {

    @BindView(R.id.signUp_firstName_editText)
    EditText signUpFirstNameEditText;
    @BindView(R.id.signUp_lastName_editText)
    EditText signUpLastNameEditText;
    @BindView(R.id.signUp_email_editText)
    EditText signUpEmailEditText;
    @BindView(R.id.signUp_phone_editText)
    EditText signUpPhoneEditText;
    @BindView(R.id.signUp_password_editText)
    EditText signUpPasswordEditText;
    @BindView(R.id.signUp_confirmPassword_editText)
    EditText signUpConfirmPasswordEditText;
    @BindView(R.id.signUp_centers_spinner)
    AppCompatSpinner centersSpinner;
    @BindView(R.id.signUp_signUp_button)
    Button signUpSignUpButton;

    private String firstName, lastName, email, phone, password, centerId;
    private List<Center> centers;

    private static final int REQUEST_INSTRUCTOR_SIGN_UP = 1;
    private static final int REQUEST_GET_CENTERS = 2;

    public InstructorSignUpActivity() {
        super(R.layout.activity_instructor_sign_up, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.sign_up));

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        centersSpinner.setOnItemSelectedListener(this);
        centers = new ArrayList<>();
        getCenters();
    }

    @OnClick(R.id.signUp_signUp_button)
    public void onViewClicked() {
        getData();
        instructorSignUp();
    }

    public void getData() {
        if (!InputValidator.registerValidation(this, signUpFirstNameEditText, signUpLastNameEditText, signUpEmailEditText, signUpPasswordEditText,
                signUpConfirmPasswordEditText, signUpPhoneEditText))
            return;

        firstName = signUpFirstNameEditText.getText().toString().trim();
        lastName = signUpLastNameEditText.getText().toString().trim();
        email = signUpEmailEditText.getText().toString().trim();
        phone = signUpPhoneEditText.getText().toString().trim();
        password = signUpPasswordEditText.getText().toString().trim();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {
        if (error != null) {
            if (error instanceof CTHttpError) {
                int code = ((CTHttpError) error).getStatusCode();
                String errorMsg = ((CTHttpError) error).getErrorMsg();
                if (code == -1 || Utilities.isNullString(errorMsg)) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (resultObject != null && requestId.equals(REQUEST_INSTRUCTOR_SIGN_UP)) {
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
            finish();
        } else if (resultObject != null && requestId.equals(REQUEST_GET_CENTERS)) {
            centers = (List<Center>) resultObject;
            AppSpinnerAdapter spinnerAdapter = new AppSpinnerAdapter(this, android.R.layout.simple_spinner_item, centers);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            centersSpinner.setAdapter(spinnerAdapter);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.signUp_centers_spinner) {
            centerId = centers.get(position).getId();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void instructorSignUp() {
        SignForm signForm = new SignForm(firstName, lastName, email, phone, password, centerId, true);
        InstructorSignUpOperation instructorSignUpOperation = new InstructorSignUpOperation(signForm, REQUEST_INSTRUCTOR_SIGN_UP, true, this);
        instructorSignUpOperation.addRequestObserver(this);
        instructorSignUpOperation.execute();
    }

    public void getCenters() {
        CentersOperation operation = new CentersOperation(REQUEST_GET_CENTERS, false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}
