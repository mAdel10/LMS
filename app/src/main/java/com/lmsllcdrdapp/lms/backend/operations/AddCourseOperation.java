package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Course;

import okhttp3.ResponseBody;

/**
 * Created by Mohamed-Mansor on 5/1/2019.
 */

public class AddCourseOperation  extends BaseOperation<ResponseBody> {
    private Course course;

    public AddCourseOperation(Course course, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.course = course;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doInstructorAddCourse(course);
    }
}
