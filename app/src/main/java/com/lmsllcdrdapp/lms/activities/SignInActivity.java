package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.models.Token;
import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.UserProfileOperation;
import com.lmsllcdrdapp.lms.backend.operations.UserSignInOperation;
import com.lmsllcdrdapp.lms.backend.operations.UserSignInSocialOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.InputValidator;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import com.lmsllcdrdapp.lms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity implements RequestObserver {

    private CallbackManager callbackManager;

    @BindView(R.id.signIn_email_editText)
    EditText signInEmailEditText;
    @BindView(R.id.signIn_password_editText)
    EditText signInPasswordEditText;
    @BindView(R.id.signIn_forget_password_text_view)
    TextView signInForgetPasswordTextView;
    @BindView(R.id.signIn_sign_in_button)
    Button signInSignInButton;
    @BindView(R.id.signIn_google_button)
    Button signInGoogleButton;
    @BindView(R.id.signIn_facebook_button)
    Button signInFacebookButton;
    @BindView(R.id.signIn_back_button)
    Button signInBackButton;
    @BindView(R.id.login_button)
    LoginButton loginButton;

    private String email;
    private String password;

    private static final int REQUEST_USER_SIGN_IN = 1;
    private static final int REQUEST_GET_USER = 2;
    private static final int REQUEST_USER_SIGN_IN_SOCIAL = 3;

    public SignInActivity() {
        super(R.layout.activity_sign_in, false);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken == null) {

                Toast.makeText(SignInActivity.this, "User 1ogeed out", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    userSignInSocial(email);
                    String id = object.getString("id");

                    String image_url = object.getString("https://graph.facebook.com/" + id + "/pictures?type=normal");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.signIn_email_editText, R.id.signIn_password_editText,
            R.id.signIn_forget_password_text_view, R.id.signIn_sign_in_button,
            R.id.signIn_google_button, R.id.signIn_facebook_button, R.id.signIn_back_button})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.signIn_email_editText:
                break;
            case R.id.signIn_password_editText:
                break;
            case R.id.signIn_forget_password_text_view:
                break;
            case R.id.signIn_sign_in_button:
                if (Utilities.isConnected(this)) {
                    getInputData();
                } else {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), getString(R.string.no_internet_connection), this);
                }
                break;
            case R.id.signIn_facebook_button:
                loginButton.performClick();
                break;
            case R.id.signIn_back_button:
                finish();
                break;
            case R.id.login_button:
                break;
        }
    }

    private void getInputData() {
        if (!InputValidator.loginValidation(this, signInEmailEditText, signInPasswordEditText)) {
            return;
        }

        email = signInEmailEditText.getText().toString().trim();
        password = signInPasswordEditText.getText().toString().trim();
        userSignIn();
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
        } else if (resultObject != null) {
            switch ((int) requestId) {
                case REQUEST_USER_SIGN_IN:
                    getAccountData();
                    break;
                case REQUEST_GET_USER:
                case REQUEST_USER_SIGN_IN_SOCIAL:
                    Intent i;
                    User user = (User) resultObject;
                    switch (user.getUserType()) {
                        case User.TYPE_STUDENT:
                            i = new Intent(this, MainActivity.class);
                            break;
                        case User.TYPE_INSTRUCTOR:
                            i = new Intent(this, InstructorMainActivity.class);
                            break;
                        case User.TYPE_CENTER:
                            i = new Intent(this, CenterMainActivity.class);
                            break;
                        default:
                            i = new Intent(this, MainActivity.class);
                            break;
                    }
                    UserManager.getInstance().saveUser(user);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
            }
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }


    private void userSignIn() {
        SignForm signForm = new SignForm(email, password);
        UserSignInOperation operation = new UserSignInOperation(signForm, REQUEST_USER_SIGN_IN, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getAccountData() {
        UserProfileOperation operation = new UserProfileOperation(REQUEST_GET_USER, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void userSignInSocial(String email) {
        SignForm signForm = new SignForm(this.email);
        UserSignInSocialOperation operation = new UserSignInSocialOperation(signForm, REQUEST_USER_SIGN_IN, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}
