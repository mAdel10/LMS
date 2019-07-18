package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.Session;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.SessionByGroupIdOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.SessionAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.List;

public class SessionActivity extends BaseActivity implements RequestObserver {

    private RecyclerView recyclerView;
    private Group group;

    private static final int REQUEST_GET_SESSION = 1;

    public SessionActivity() {
        super(R.layout.activity_session, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        toolbarTextView.setText(getString(R.string.sessions));
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
        toolbarTextView.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));

        group = (Group) getIntent().getSerializableExtra(Constants.INTENT_OBJECT);
        getSessionByGroupId(group.getId());

        if (!UserManager.getInstance().isStudent()) {
            toolbarAddSessionButton.setVisibility(View.VISIBLE);
            toolbarAddSessionButton.setOnClickListener(v -> {
                Intent i = new Intent(SessionActivity.this, AddSessionActivity.class);
                i.putExtra(Constants.INTENT_GROUP, group);
                startActivity(i);
            });
        }
    }

    private void getSessionByGroupId(String id) {
        SessionByGroupIdOperation operation = new SessionByGroupIdOperation(id, REQUEST_GET_SESSION,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {
        if (error != null) {
            if (error instanceof CTHttpError) {
                int code = ((CTHttpError) error).getStatusCode();
                String errorMsg = ((CTHttpError) error).getErrorMsg();
                if (code == -1 || Utilities.isNullString(errorMsg)) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (requestId.equals(REQUEST_GET_SESSION)) {
            List<Session> sessions = (List<Session>) resultObject;
            SessionAdapter sessionAdapter = new SessionAdapter(sessions, group, this);
            recyclerView.setAdapter(sessionAdapter);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
