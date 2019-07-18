package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Chat;
import com.lmsllcdrdapp.lms.backend.models.Course;

import java.util.List;

public class CourseOperation extends BaseOperation<List<Course>> {

    public CourseOperation(Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);

    }
    @Override
    public List<Course> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCourse();
    }

}
