package com.lmsllcdrdapp.lms.backend.operations;//package com.perceptivemind.rafikee.backend.operations.acl;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Token;

public class TokenRefreshOperation extends BaseOperation<Token> {

    private String refreshToken;

    public TokenRefreshOperation(String refreshToken, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.refreshToken = refreshToken;
    }

    @Override
    public Token doMain() throws Throwable {
        return OperationsManager.getInstance().doRefreshToken(refreshToken);
    }
}
