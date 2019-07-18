package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Group;

import okhttp3.ResponseBody;

public class AddGroupOperation extends BaseOperation<ResponseBody> {

    private Group group;

    public AddGroupOperation(Group group, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.group = group;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doCenterAddGroup(group);
    }
}
