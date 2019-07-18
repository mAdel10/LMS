package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Chat;

import java.util.List;

/**
 * Created by Mohamed-Mansor on 5/1/2019.
 */

public class ChatByGroupIdOperation extends BaseOperation<List<Chat>> {

    private String id;

    public ChatByGroupIdOperation(String id, Object requestID, boolean isShowLoadingDialog, Context activity) {
        super(requestID, isShowLoadingDialog, activity);
        this.id = id;
    }

    @Override
    public List<Chat> doMain() throws Throwable {
        return OperationsManager.getInstance().doGetChatByGroupId(id);
    }
}
