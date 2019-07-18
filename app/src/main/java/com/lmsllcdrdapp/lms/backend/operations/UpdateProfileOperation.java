package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.EditProfileForm;

import okhttp3.ResponseBody;

public class UpdateProfileOperation extends BaseOperation<ResponseBody> {

    private EditProfileForm editProfileForm;

    public UpdateProfileOperation(EditProfileForm editProfileForm, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.editProfileForm = editProfileForm;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doUpdateProfile(editProfileForm);
    }
}

