package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.SearchFrom;

import java.util.List;

public class SearchCentersOperation extends BaseOperation<List<Center>> {

    private SearchFrom searchFrom;

    public SearchCentersOperation(SearchFrom searchFrom, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.searchFrom = searchFrom;
    }

    @Override
    public List<Center> doMain() throws Throwable {
        return OperationsManager.getInstance().doSearchCenters(searchFrom);
    }
}