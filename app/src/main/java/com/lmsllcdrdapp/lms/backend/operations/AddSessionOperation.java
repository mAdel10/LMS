package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.activities.AddSessionActivity;
import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.Session;

import okhttp3.ResponseBody;

public class AddSessionOperation extends BaseOperation<ResponseBody> {
    private Session session;

    public AddSessionOperation(Session session, Object requestID, boolean isShowLoadingDialog, AddSessionActivity activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.session = session;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doAddSession(session);
    }
}
