package com.lmsllcdrdapp.lms.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.GroupsByCenterIdOperation;
import com.lmsllcdrdapp.lms.backend.operations.GroupsByInstructorIdOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.GroupAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.List;
import java.util.Objects;

public class GroupsFragment extends Fragment implements RequestObserver {

    private RecyclerView recyclerView;
    private Context context;
    private String id;
    private String userType;

    private final static int REQUEST_GROUPS = 1;

    public GroupsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        context = Objects.requireNonNull(getActivity()).getApplicationContext();

        recyclerView = view.findViewById(R.id.group_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));

        userType = UserManager.getInstance().getCurrentUser().getUserType();
        if (userType.equals("center")) {
            id = UserManager.getInstance().getCurrentUser().getCenter().getId();
        } else if (userType.equals("instructor")) {
            id = UserManager.getInstance().getCurrentUser().getInstructor().getId();
        }
        getGroupsByCreatorId(id);

        return view;
    }

    public void getGroupsByCreatorId(String id) {
        if (userType.equals("center")) {
            GroupsByCenterIdOperation operation = new GroupsByCenterIdOperation(id, REQUEST_GROUPS, false, context);
            operation.addRequestObserver(this);
            operation.execute();
        } else if (userType.equals("instructor")) {
            GroupsByInstructorIdOperation operation = new GroupsByInstructorIdOperation(id, REQUEST_GROUPS, false, context);
            operation.addRequestObserver(this);
            operation.execute();
        }
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
        } else if (requestId.equals(REQUEST_GROUPS)) {
            List<Group> groups = (List<Group>) resultObject;
            GroupAdapter groupAdapter = new GroupAdapter(groups, context, getActivity());
            recyclerView.setAdapter(groupAdapter);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
