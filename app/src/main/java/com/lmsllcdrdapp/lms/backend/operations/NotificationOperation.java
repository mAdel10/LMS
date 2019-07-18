package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Notification;

import java.util.List;

public class NotificationOperation extends BaseOperation<List<Notification>> {

    public NotificationOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }
    @Override
    public List<Notification> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetNotification();
    }
}
