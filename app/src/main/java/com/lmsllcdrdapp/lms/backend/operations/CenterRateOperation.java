package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.CenterRate;

import okhttp3.ResponseBody;

public class CenterRateOperation extends BaseOperation<ResponseBody>{

    private CenterRate centerRate;

    public CenterRateOperation(CenterRate centerRate, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.centerRate = centerRate;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doCenterRate(centerRate);
    }
}
