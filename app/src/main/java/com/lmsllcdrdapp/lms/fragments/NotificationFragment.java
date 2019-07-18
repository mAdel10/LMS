package com.lmsllcdrdapp.lms.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Notification;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.NotificationOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.NotificationAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.List;

public class NotificationFragment extends Fragment implements RequestObserver {

    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private Context context;
    private final static int REQUEST_NOTIFICATION = 1;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        context = getActivity().getApplicationContext();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        getNotifications();
        return view;
    }

    private void getNotifications() {
        NotificationOperation operation = new NotificationOperation(REQUEST_NOTIFICATION,
                false, getActivity().getApplication());
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
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), getString(R.string.request_server_error), getActivity());
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), errorMsg, getActivity());
                }
            }
        } else if (requestId.equals(REQUEST_NOTIFICATION)) {
            List<Notification> notifications = (List<Notification>) resultObject;
            notificationAdapter = new NotificationAdapter(notifications, context);
            recyclerView.setAdapter(notificationAdapter);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
