package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Course;

import java.util.List;

public class CoursesByCenterIdOperation extends BaseOperation<List<Course>> {

    private String id;

    public CoursesByCenterIdOperation(String id, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.id = id;
    }

    @Override
    public List<Course> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCoursesByCenterId(id);
    }
}
