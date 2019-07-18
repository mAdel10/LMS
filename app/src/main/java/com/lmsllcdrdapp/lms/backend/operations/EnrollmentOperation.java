package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Group;

import java.util.List;

public class EnrollmentOperation extends BaseOperation<List<Group>> {

    public EnrollmentOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }
    @Override
    public List<Group> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetEnrollment();
    }
}
