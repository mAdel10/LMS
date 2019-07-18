package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.SignForm;

import okhttp3.ResponseBody;

public class CenterSignUpOperation extends BaseOperation<ResponseBody> {

    private SignForm signForm;

    public CenterSignUpOperation(SignForm signForm, Object requestID, boolean isShowLoadingDialog, Context context) {
        super(requestID, isShowLoadingDialog, context);
        this.signForm = signForm;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doCenterSignUp(signForm);
    }
}
