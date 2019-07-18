package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Instructor;

public class InstructorOperation extends BaseOperation<Instructor> {

    String  id;
    public InstructorOperation(String id, Object requestID, boolean isShowLoadingDialog, Context context) {
        super(requestID, isShowLoadingDialog, context);
        this.id = id;
    }

    @Override
    public Instructor doMain() throws Throwable {
        return OperationsManager.getInstance().doGetInstructor(id);
    }
}
