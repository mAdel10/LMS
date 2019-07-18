package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.GroupID;

import okhttp3.ResponseBody;



public class ApplyGroupOperation extends BaseOperation<ResponseBody> {
    private GroupID groupID;

    public ApplyGroupOperation(GroupID groupID, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.groupID = groupID;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doGetGroupID(groupID);
    }
}
