package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Group;

import java.util.List;

public class GroupsByInstructorIdOperation extends BaseOperation<List<Group>> {

    private String id;

    public GroupsByInstructorIdOperation(String id, Object requestID, boolean isShowLoadingDialog, Context context) {
        super(requestID, isShowLoadingDialog, context);
        this.id = id;
    }

    @Override
    public List<Group> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetGroupsByInstructorId(id);
    }
}
