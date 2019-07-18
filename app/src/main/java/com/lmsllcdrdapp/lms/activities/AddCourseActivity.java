package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Category;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.models.Language;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.AddCourseOperation;
import com.lmsllcdrdapp.lms.backend.operations.CategoryOperation;
import com.lmsllcdrdapp.lms.backend.operations.CenterInstructorsOperation;
import com.lmsllcdrdapp.lms.backend.operations.LanguageOperation;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.views.AppSpinnerAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCourseActivity extends BaseActivity implements RequestObserver, AdapterView.OnItemSelectedListener {

    @BindView(R.id.form_coursename_editText)
    EditText courseNameEditText;
    @BindView(R.id.form_coursedesc_editText)
    EditText courseDescEditText;
    @BindView(R.id.form_coursecontent_editText)
    EditText courseContentEditText;
    @BindView(R.id.form_coursemaxnum_editText)
    EditText courseMaxNumEditText;
    @BindView(R.id.form_numofsession_editText)
    EditText numOfSessionEditText;
    @BindView(R.id.form_price_editText)
    EditText priceEditText;
    @BindView(R.id.form_instructor_spinner)
    AppCompatSpinner instructorSpinner;
    @BindView(R.id.form_language_spinner)
    AppCompatSpinner languageSpinner;
    @BindView(R.id.form_category_spinner)
    AppCompatSpinner categorySpinner;
    @BindView(R.id.form_createcouurse_button)
    Button createCourseButton;

    private String courseName;
    private String courseDesc;
    private String courseContent;
    private int courseMaxNum;
    private String numOfSession;
    private double coursePrice;
    private Course course;

    private List<Instructor> instructors;
    private List<Category> categories;
    private List<Language> languages;

    private static final int REQUEST_INSTRUCTOR_ADD_COURSE = 1;
    private final static int REQUEST_GET_INSTRUCTORS = 2;
    private final static int REQUEST_GET_LANGUAGES = 3;
    private final static int REQUEST_GET_CATEGORIES = 4;

    public AddCourseActivity() {
        super(R.layout.activity_add_course, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.create_course));
        toolbarSearchEditText.setVisibility(View.GONE);

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        course = new Course();
        fillTypeSpinner();

        getInstructor(UserManager.getInstance().getCurrentUser().getCenter().getId());
        getLanguage();
        getCategories();
    }

    @SuppressWarnings("unchecked")
    private void fillTypeSpinner() {
        List<String> types = new ArrayList<>();
        AppSpinnerAdapter spinnerAdapter = new AppSpinnerAdapter(this, android.R.layout.simple_spinner_item, types);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void getInputData() {
        courseName = courseNameEditText.getText().toString().trim();
        courseDesc = courseDescEditText.getText().toString().trim();
        courseContent = courseContentEditText.getText().toString().trim();
        courseMaxNum = Integer.parseInt(courseMaxNumEditText.getText().toString().trim());
        numOfSession = numOfSessionEditText.getText().toString().trim();
        coursePrice = Double.parseDouble(priceEditText.getText().toString().trim());

        instructorAddCourse();
    }

    @OnClick(R.id.form_createcouurse_button)
    public void onViewClicked() {
        getInputData();
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
                case REQUEST_GET_INSTRUCTORS:
                    instructors = new ArrayList<>();
                    instructors.add(new Instructor("-1", getString(R.string.instructor), ""));
                    instructors.addAll((Collection<? extends Instructor>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, android.R.layout.simple_spinner_item, instructors);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    instructorSpinner.setAdapter(spinnerAdapter);
                    instructorSpinner.setOnItemSelectedListener(this);
                    break;
                case REQUEST_GET_CATEGORIES:
                    categories = new ArrayList<>();
                    categories.add(new Category("-1", getString(R.string.category)));
                    categories.addAll((Collection<? extends Category>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, android.R.layout.simple_spinner_item, categories);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorySpinner.setAdapter(spinnerAdapter);
                    categorySpinner.setOnItemSelectedListener(this);
                    break;
                case REQUEST_GET_LANGUAGES:
                    languages = new ArrayList<>();
                    languages.add(new Language("-1", getString(R.string.language)));
                    languages.addAll((Collection<? extends Language>) resultObject);
                    spinnerAdapter = new AppSpinnerAdapter(this, android.R.layout.simple_spinner_item, languages);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    languageSpinner.setAdapter(spinnerAdapter);
                    languageSpinner.setOnItemSelectedListener(this);
                    break;
                case REQUEST_INSTRUCTOR_ADD_COURSE:
                    Intent i = new Intent(this, AddGroupActivity.class);
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
        switch (parent.getId()) {
            case R.id.form_instructor_spinner:
                if (position == 0) course.setInstructorId(null);
                else course.setInstructorId(instructors.get(position).getId());
                break;
            case R.id.form_language_spinner:
                if (position == 0) course.setLanguageId(null);
                else course.setCategoryId(categories.get(position).getId());
                break;
            case R.id.form_category_spinner:
                if (position == 0) course.setCategoryId(null);
                else course.setLanguageId(languages.get(position).getId());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getInstructor(String centerId) {
        CenterInstructorsOperation operation = new CenterInstructorsOperation(centerId, REQUEST_GET_INSTRUCTORS,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getLanguage() {
        LanguageOperation operation = new LanguageOperation(REQUEST_GET_LANGUAGES,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void getCategories() {
        CategoryOperation operation = new CategoryOperation(REQUEST_GET_CATEGORIES,
                false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void instructorAddCourse() {
        course = new Course(courseName, courseDesc, courseContent, courseMaxNum, numOfSession,
                coursePrice, course.getLanguageId(), course.getCategoryId(), course.getInstructorId());
        AddCourseOperation operation = new AddCourseOperation(course, REQUEST_INSTRUCTOR_ADD_COURSE,
                true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}
