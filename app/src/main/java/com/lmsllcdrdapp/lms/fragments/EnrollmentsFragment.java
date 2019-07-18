package com.lmsllcdrdapp.lms.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.EnrollmentOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.EnrollmentAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.List;

public class EnrollmentsFragment extends Fragment implements RequestObserver {

    private RecyclerView recyclerView;
    private EnrollmentAdapter enrollmentAdapter;
    private Context context;
    private final static int REQUEST_ENROLLMENT = 1;

    public EnrollmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enrollments, container, false);
        context = getActivity().getApplicationContext();
        initViews(view);
        getEnrollment();
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getEnrollment() {
        EnrollmentOperation operation = new EnrollmentOperation(REQUEST_ENROLLMENT, false, context);
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
                } else if (code != 401)
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), errorMsg, getActivity());
            }
        } else if (resultObject != null && requestId.equals(REQUEST_ENROLLMENT)) {
            List<Group> groups = (List<Group>) resultObject;
            enrollmentAdapter = new EnrollmentAdapter(groups, context);
            recyclerView.setAdapter(enrollmentAdapter);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
