package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Center;

import java.util.List;

public class CentersOperation extends BaseOperation<List<Center>> {

    public CentersOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }

    @Override
    public List<Center> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCenters();
    }
}
