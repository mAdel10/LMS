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
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CoursesByCenterIdOperation;
import com.lmsllcdrdapp.lms.backend.operations.CoursesByInstructorIdOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.CourseAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.List;
import java.util.Objects;

public class CourseFragment extends Fragment implements RequestObserver {

    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private Context context;
    private String id;
    private String userType;

    private final static int REQUEST_COURSES = 1;

    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        context = Objects.requireNonNull(getActivity()).getApplicationContext();

        recyclerView = view.findViewById(R.id.course_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));

        userType = UserManager.getInstance().getCurrentUser().getUserType();
        if (userType.equals("center")) {
            id = UserManager.getInstance().getCurrentUser().getCenter().getId();
        } else if (userType.equals("instructor")) {
            id = UserManager.getInstance().getCurrentUser().getInstructor().getId();
        }
        getCoursesByCreatorId(id);

        return view;
    }

    public void getCoursesByCreatorId(String id) {
        if (userType.equals("center")) {
            CoursesByCenterIdOperation operation = new CoursesByCenterIdOperation(id, REQUEST_COURSES, true, context);
            operation.addRequestObserver(this);
            operation.execute();
        } else if (userType.equals("instructor")) {
            CoursesByInstructorIdOperation operation = new CoursesByInstructorIdOperation(id, REQUEST_COURSES, true, context);
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
        } else if (requestId.equals(REQUEST_COURSES)) {
            List<Course> courses = (List<Course>) resultObject;
            courseAdapter = new CourseAdapter(courses, context, null, null);
            recyclerView.setAdapter(courseAdapter);
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
