package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.models.Token;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.StudentSignUpOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.InputValidator;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.signUp_firstName_editText)
    EditText FirstNameEditText;
    @BindView(R.id.signUp_lastName_editText)
    EditText LastNameEditText;
    @BindView(R.id.signUp_email_editText)
    EditText EmailEditText;
    @BindView(R.id.signUp_phone_editText)
    EditText PhoneEditText;
    @BindView(R.id.signUp_password_editText)
    EditText PasswordEditText;
    @BindView(R.id.signUp_confirmPassword_editText)
    EditText ConfirmPasswordEditText;
    @BindView(R.id.signUp_signUp_button)
    Button SignUpButton;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;

    private static final int REQUEST_STUDENT_SIGN_UP = 1;

    public SignUpActivity() {
        super(R.layout.activity_sign_up, true);
    }

    @OnClick({R.id.signUp_signUp_button, R.id.signUp_terms_textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.signUp_signUp_button:
                getInputData();
                break;
            case R.id.signUp_terms_textView:
                break;
        }
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.sign_up));

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
    }

    private void getInputData() {
        if (!InputValidator.registerValidation(this, FirstNameEditText, LastNameEditText, EmailEditText,
                 PasswordEditText, ConfirmPasswordEditText , PhoneEditText)) {
            return;
        }

        firstName = FirstNameEditText.getText().toString().trim();
        lastName = LastNameEditText.getText().toString().trim();
        email = EmailEditText.getText().toString().trim();
        phone = PhoneEditText.getText().toString().trim();
        password = PasswordEditText.getText().toString().trim();
        confirmPassword = ConfirmPasswordEditText.getText().toString().trim();

        studentSignUp();
    }

    private void studentSignUp() {
        SignForm signForm = new SignForm(firstName, lastName, email, phone, password);
        StudentSignUpOperation operation = new StudentSignUpOperation(signForm, REQUEST_STUDENT_SIGN_UP, true, this);
        operation.addRequestObserver(this);
        operation.execute();
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
        } else if (resultObject != null && requestId.equals(REQUEST_STUDENT_SIGN_UP)) {
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
