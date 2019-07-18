package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;
import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Session;

import java.util.List;

public class SessionByGroupIdOperation extends BaseOperation<List<Session>>{

    public String id;

    public SessionByGroupIdOperation(String id, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.id = id;
    }

    @Override
    public List<Session> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetSessionByGroupId(id);
    }
}