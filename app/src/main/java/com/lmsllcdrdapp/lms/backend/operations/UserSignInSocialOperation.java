package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.models.Token;
import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.managers.TokenManager;
import com.lmsllcdrdapp.lms.managers.UserManager;

public class UserSignInSocialOperation extends BaseOperation<Token> implements RequestObserver {

    private SignForm signForm;

    public UserSignInSocialOperation(SignForm signForm, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.signForm = signForm;
    }

    @Override
    public Token doMain() throws Throwable {
        Token token = OperationsManager.getInstance().doUserSignInSocial(signForm);
        TokenManager.getInstance().saveToken(token);
        getUserData();
        return token;
    }

    private void getUserData() {
        UserProfileOperation operation = new UserProfileOperation(1,
                false, context);
        operation.addRequestObserver(this);
        operation.execute();
    }

    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {
        if (resultObject != null && requestId.equals(1)) {
            User user = (User) resultObject;
            UserManager.getInstance().saveUser(user);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}