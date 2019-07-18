package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Country;

import java.util.List;

public class CountryOperation extends BaseOperation<List<Country>> {

    public CountryOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }

    @Override
    public List<Country> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCountry();
    }
}
