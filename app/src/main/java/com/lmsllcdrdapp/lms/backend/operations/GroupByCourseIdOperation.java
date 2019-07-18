package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;
import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Group;

import java.util.List;

public class GroupByCourseIdOperation extends BaseOperation<List<Group>> {

    private String id;

    public GroupByCourseIdOperation(String id, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.id = id;
    }

    @Override
    public List<Group> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetGroupsByCourseId(id);
    }
}

