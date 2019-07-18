package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CenterSignUpOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.InputValidator;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CenterSignUpActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.signUp_name_editText)
    EditText signUpNameEditText;
    @BindView(R.id.signUp_email_editText)
    EditText signUpEmailEditText;
    @BindView(R.id.signUp_phone_editText)
    EditText signUpPhoneEditText;
    @BindView(R.id.signUp_password_editText)
    EditText signUpPasswordEditText;
    @BindView(R.id.signUp_confirmPassword_editText)
    EditText signUpConfirmPasswordEditText;
    @BindView(R.id.signUp_signUp_button)
    Button signUpSignUpButton;
    @BindView(R.id.signUp_latitude_editText)
    EditText signUpLatitudeEditText;
    @BindView(R.id.signUp_longitude_editText)
    EditText signUpLongitudeEditText;
    @BindView(R.id.signUp_terms_textView)
    TextView signUpTermsTextView;

    private static final int REQUEST_CENTER_SIGN_UP = 1;

    private String name, email, phone, password, latitude, longitude;

    public CenterSignUpActivity() {
        super(R.layout.activity_center_sign_up, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.sign_up));

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
    }

    @OnClick(R.id.signUp_signUp_button)
    public void onViewClicked() {
        getData();
        centerSignUp();
    }

    public void getData() {
        if (!InputValidator.centerRegisterValidation(this, signUpNameEditText, signUpEmailEditText, signUpPasswordEditText, signUpConfirmPasswordEditText, signUpPhoneEditText, signUpLatitudeEditText, signUpLongitudeEditText))
            return;

        name = signUpNameEditText.getText().toString().trim();
        email = signUpEmailEditText.getText().toString().trim();
        phone = signUpPhoneEditText.getText().toString().trim();
        password = signUpPasswordEditText.getText().toString();
        latitude = signUpLatitudeEditText.getText().toString().trim();
        longitude = signUpLongitudeEditText.getText().toString().trim();
    }

    public void centerSignUp() {
        SignForm signForm = new SignForm(name, email, phone, password, latitude, longitude);
        CenterSignUpOperation centerSignUpOperation = new CenterSignUpOperation(signForm, REQUEST_CENTER_SIGN_UP, true, this);
        centerSignUpOperation.addRequestObserver(this);
        centerSignUpOperation.execute();
    }

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
        } else if (resultObject != null && requestId.equals(REQUEST_CENTER_SIGN_UP)) {
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
