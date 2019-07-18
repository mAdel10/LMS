package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.City;
import com.lmsllcdrdapp.lms.backend.models.Country;

import java.util.List;

public class CityOperation extends BaseOperation<List<City>> {

    public CityOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }

    @Override
    public List<City> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCity();
    }
}
