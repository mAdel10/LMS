package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Notification;

import java.io.IOException;
import java.util.List;

public class NotificationsOperation extends BaseOperation<List<Notification>> {

    public NotificationsOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }

    @Override
    public List<Notification> doMain() throws IOException {
         return OperationsManager.getInstance().doGetNotification();
    }
}
