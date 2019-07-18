package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.SearchFrom;

import java.util.List;

public class SearchCoursesOperation extends BaseOperation<List<Course>> {

    private SearchFrom searchFrom;

    public SearchCoursesOperation(SearchFrom searchFrom, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.searchFrom = searchFrom;
    }

    @Override
    public List<Course> doMain() throws Throwable {
        return OperationsManager.getInstance().doSearchCourses(searchFrom);
    }
}