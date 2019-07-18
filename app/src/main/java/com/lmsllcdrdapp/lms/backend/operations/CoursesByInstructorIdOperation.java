package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Course;

import java.util.List;

public class CoursesByInstructorIdOperation extends BaseOperation<List<Course>> {
    private String id;

    public CoursesByInstructorIdOperation(String id, Object requestID, boolean isShowLoadingDialog, Context context) {
        super(requestID, isShowLoadingDialog, context);
        this.id = id;
    }

    @Override
    public List<Course> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetCoursesByInstructorId(id);
    }
}
