package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CoursesByInstructorIdOperation;
import com.lmsllcdrdapp.lms.backend.operations.InstructorOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.CourseAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class InstructorActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.instructor_imageView)
    CircleImageView instructorImageView;
    @BindView(R.id.instructor_name_textView)
    TextView instructorNameTextView;
    @BindView(R.id.instructor_get_details_position)
    TextView instructorGetDetailsPosition;
    @BindView(R.id.instructor_get_details_birthDate)
    TextView instructorGetDetailsBirthDate;
    @BindView(R.id.instructor_get_details_cv)
    TextView instructorDetailsCV;
    @BindView(R.id.instructor_details_ratingBar)
    RatingBar instructorDetailsRatingBar;
    @BindView(R.id.item_instructor_rate_textView)
    TextView itemInstructorRateTextView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CourseAdapter courseAdapter;
    private Instructor instructor;
    private String instructorId;

    private final static int REQUEST_COURSE_BY_INSTRUCTOR_ID = 1;
    private final static int REQUEST_INSTRUCTOR_BY_ID = 2;

    public InstructorActivity() {
        super(R.layout.activity_instructor, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setText(getString(R.string.instructor_details));
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
        toolbarTextView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        instructorId = getIntent().getStringExtra(Constants.INTENT_ID);
        getInstructorById(instructorId);
    }

    private void getCourseByInstructorId(String id) {
        CoursesByInstructorIdOperation operation = new CoursesByInstructorIdOperation(id, REQUEST_COURSE_BY_INSTRUCTOR_ID,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getInstructorById(String id) {
        InstructorOperation operation = new InstructorOperation(id, REQUEST_INSTRUCTOR_BY_ID,
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
        } else if (requestId.equals(REQUEST_COURSE_BY_INSTRUCTOR_ID)) {
            List<Course> courses = (List<Course>) resultObject;
            courseAdapter = new CourseAdapter(courses, this, instructor.getCenter(), instructor);
            recyclerView.setAdapter(courseAdapter);
        } else if (requestId.equals((REQUEST_INSTRUCTOR_BY_ID))) {
            instructor = (Instructor) resultObject;
            if (instructor.getUser() != null) {
                Picasso.with(this)
                        .load(instructor.getUser().getImage())
                        .placeholder(R.drawable.img_placeholder)
                        .into(instructorImageView);

                String name = instructor.getFirstName() + " " + instructor.getLastName();
                instructorNameTextView.setText(name);

                instructorGetDetailsPosition.setText(instructor.getPosition());
                instructorGetDetailsBirthDate.setText(instructor.getDateOfBirth());
                instructorDetailsCV.setText(instructor.getCvUrl());
                instructorDetailsRatingBar.setRating(instructor.getRate());

                if (instructor.getRateCount() != 0) {
                    String rate = ((instructor.getRate() / instructor.getRateCount()) + "/5");
                    itemInstructorRateTextView.setText(rate);
                } else {
                    itemInstructorRateTextView.setText("0/5");
                }
            }

            getCourseByInstructorId(instructorId);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

}
