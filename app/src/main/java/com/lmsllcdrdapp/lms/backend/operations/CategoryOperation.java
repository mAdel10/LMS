package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Category;

import java.util.List;

public class CategoryOperation extends BaseOperation<List<Category>> {

    public CategoryOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
    }

    @Override
    public List<Category> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCategory();
    }
}
