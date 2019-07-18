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
import com.lmsllcdrdapp.lms.backend.operations.CourseOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.ProfileCoursesAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.List;
import java.util.Objects;

public class PassedFragment extends Fragment implements RequestObserver {

    private RecyclerView recyclerView;
    private Context context;
    private ProfileCoursesAdapter profileCoursesAdapter;
    private List<Course> PassedCourses;

    private static final int GET_PASSED_COURSE = 1;

    public PassedFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_passed, container, false);
        context = Objects.requireNonNull(getActivity()).getApplicationContext();

        recyclerView = view.findViewById(R.id.passed_course_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
        getPassedCourses();
        return view;

    }

    private void getPassedCourses() {
        CourseOperation operation = new CourseOperation(GET_PASSED_COURSE,
                false, context);
        operation.addRequestObserver(this);
        operation.execute();
    }

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
        } else if (requestId.equals(GET_PASSED_COURSE)) {
            PassedCourses = (List<Course>) resultObject;
            profileCoursesAdapter = new ProfileCoursesAdapter(PassedCourses, context,getActivity());
            recyclerView.setAdapter(profileCoursesAdapter);

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
