package com.lmsllcdrdapp.lms.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.AddGroupOperation;
import com.lmsllcdrdapp.lms.backend.operations.CoursesByCenterIdOperation;
import com.lmsllcdrdapp.lms.backend.operations.CoursesByInstructorIdOperation;
import com.lmsllcdrdapp.lms.views.AppSpinnerAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddGroupActivity extends BaseActivity implements RequestObserver, AdapterView.OnItemSelectedListener {

    @BindView(R.id.addgroup_course_spinner)
    AppCompatSpinner courseSpinner;
    @BindView(R.id.addgroup_create_button)
    Button createButton;

    private EditText date;
    private EditText time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog mTimePicker;

    private final static int REQUEST_GET_COURSE = 1;
    private final static int REQUEST_INSTRUCTOR_ADD_GROUP = 2;

    private List<Course> courses;
    private Group group;

    public AddGroupActivity() {
        super(R.layout.activity_add_group, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.add_group));
        toolbarSearchEditText.setVisibility(View.GONE);

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        date = findViewById(R.id.addgroup_date_editText);
        time = findViewById(R.id.addgroup_time_editText);
        group = new Group();
        getCourses();
        date.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            final int year = cal.get(Calendar.YEAR);
            final int month = cal.get(Calendar.MONTH);
            final int day = cal.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(AddGroupActivity.this, (datePicker, thisday, thismonth, thisyear) -> date.setText(thisday + "-" + (thismonth + 1) + "-" + thisyear), year, month, day);
            datePickerDialog.show();
        });

        time.setOnClickListener(v -> {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            mTimePicker = new TimePickerDialog(AddGroupActivity.this, (timePicker, selectedHour, selectedMinute) -> time.setText("" + selectedHour + ":" + selectedMinute + ":00"), hour, minute, false);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        });

    }

    @OnClick(R.id.addgroup_create_button)
    public void onViewClicked() {
        group.setStartDate(date.getText().toString().trim() + " " + time.getText().toString().trim());
        centerAddGroup();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {
        if (error != null) {
            if (error instanceof CTHttpError) {
                int code = ((CTHttpError) error).getStatusCode();
                String errorMsg = ((CTHttpError) error).getErrorMsg();
                if (code == -1 || Utilities.isNullString(errorMsg)) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), this.getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(this.getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (resultObject != null) {
            AppSpinnerAdapter spinnerAdapter;
            switch ((int) requestId) {
                case REQUEST_GET_COURSE:
                    courses = new ArrayList<>();
                    courses.add(new Course("-1", getString(R.string.course)));
                    courses.addAll((Collection<? extends Course>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, android.R.layout.simple_spinner_item, courses);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    courseSpinner.setAdapter(spinnerAdapter);
                    courseSpinner.setOnItemSelectedListener(this);
                    break;

                case REQUEST_INSTRUCTOR_ADD_GROUP:
                    Intent i = new Intent(this, AddCourseActivity.class);
                    this.startActivity(i);
                    finish();
                    break;
            }
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.addgroup_course_spinner) {
            group.setCourseId(courses.get(position).getId());
            group.setInstructorId(courses.get(position).getInstructorId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getCourses() {
        String id = UserManager.getInstance().getCurrentUser().getCenter().getId();
        CoursesByCenterIdOperation operation = new CoursesByCenterIdOperation(id,
                REQUEST_GET_COURSE, true, this);
        operation.addRequestObserver(this);
        operation.execute();

    }

    private void centerAddGroup() {
        AddGroupOperation operation = new AddGroupOperation(group, REQUEST_INSTRUCTOR_ADD_GROUP,
                true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}
