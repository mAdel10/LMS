package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.InstructorRate;

import okhttp3.ResponseBody;

public class InstructorRateOperation extends BaseOperation<ResponseBody>{

    private InstructorRate instructorRate;

    public InstructorRateOperation(InstructorRate instructorRate, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.instructorRate = instructorRate;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doInstructorRate(instructorRate);
    }
}
