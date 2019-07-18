package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Chat;
import com.lmsllcdrdapp.lms.backend.models.Group;

import java.util.List;

public class ChatOperation extends BaseOperation<List<Group>> {

    public ChatOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);

    }
    @Override
    public List<Group> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetChat();
    }

}
