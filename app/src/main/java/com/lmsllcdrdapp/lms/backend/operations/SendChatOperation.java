package com.lmsllcdrdapp.lms.backend.operations;

import android.content.Context;

import com.lmsllcdrdapp.lms.backend.api.OperationsManager;
import com.lmsllcdrdapp.lms.backend.models.Chat;

import okhttp3.ResponseBody;

/**
 * Created by Mohamed-Mansor on 5/1/2019.
 */

public class SendChatOperation extends BaseOperation<ResponseBody> {

    private Chat chat;

    public SendChatOperation(Chat chat, Object requestID, boolean isShowLoadingDialog, Context context) {
        super(requestID, isShowLoadingDialog, context);
        this.chat = chat;
    }

    @Override
    public ResponseBody doMain() throws Throwable {
        return OperationsManager.getInstance().doSendChat(chat);
    }
}
