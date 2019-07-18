package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;
import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.models.Token;
import com.lmsllcdrdapp.lms.managers.TokenManager;

public class UserSignInOperation extends BaseOperation<Token> {

    private SignForm signForm;

    public UserSignInOperation(SignForm signForm, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.signForm = signForm;
    }

    @Override
    public Token doMain() throws Throwable {
        Token token = OperationsManager.getInstance().doUserSignIn(signForm);
        TokenManager.getInstance().saveToken(token);
        return token;
    }
}
