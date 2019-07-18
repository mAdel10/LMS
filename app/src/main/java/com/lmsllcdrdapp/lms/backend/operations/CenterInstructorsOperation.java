package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Category;
import com.lmsllcdrdapp.lms.backend.models.Instructor;

import java.util.List;

public class CenterInstructorsOperation extends BaseOperation<List<Instructor>> {

    private String centerId;

    public CenterInstructorsOperation(String centerId, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.centerId = centerId;
    }

    @Override
    public List<Instructor> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetInstructorsByCenterId(centerId);
    }
}
