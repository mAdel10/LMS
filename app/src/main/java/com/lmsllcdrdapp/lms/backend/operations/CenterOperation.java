package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Center;

public class CenterOperation extends BaseOperation<Center> {
    private String id;

    public CenterOperation(String id, Object requestID, boolean isShowLoadingDialog, Context context) {
        super(requestID, isShowLoadingDialog, context);
        this.id = id;
    }

    @Override
    public Center doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCenter(id);
    }
}
