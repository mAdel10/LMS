package com.lmsllcdrdapp.lms.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.GroupByCourseIdOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.SubmitCourseAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.lmsllcdrdapp.lms.views.AppChromeClient;
import com.lmsllcdrdapp.lms.views.AppWebViewClient;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CourseActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.course_imageView)
    ImageView courseImageView;
    @BindView(R.id.course_videoView)
    WebView webView;
    @BindView(R.id.course_apply_button)
    Button applyButton;
    @BindView(R.id.course_instructor_imageView)
    CircleImageView instructorImageView;
    @BindView(R.id.course_center_imageView)
    CircleImageView centerImageView;
    @BindView(R.id.course_instructor_name_textView)
    TextView instructorNameTextView;
    @BindView(R.id.course_center_name_textView)
    TextView centerNameTextView;
    @BindView(R.id.course_instructor_position_textView)
    TextView instructorPositionTextView;
    @BindView(R.id.course_center_address_textView)
    TextView centerAddressTextView;
    @BindView(R.id.course_instructor_layout)
    LinearLayout instructorLayout;
    @BindView(R.id.course_name_TextView)
    TextView courseNameTextView;
    @BindView(R.id.course_description_TextView)
    TextView courseDescriptionTextView;
    @BindView(R.id.course_content_textView)
    TextView courseContentTextView;
    @BindView(R.id.course_sessions_textView)
    TextView courseSessionsTextView;
    @BindView(R.id.course_maxNumber_textView)
    TextView courseMaxNumberTextView;
    @BindView(R.id.course_price_textView)
    TextView coursePriceTextView;

    @BindView(R.id.course_sessionsList_textView)
    TextView sessionListTextView;

    private final static int REQUEST_Groups = 1;
    private SubmitCourseAdapter submitCourseAdapter;
    private RecyclerView recyclerView;
    private Course course;

    private List<Group> groups;

    public CourseActivity() {
        super(R.layout.activity_course, true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        toolbarTextView.setText(getString(R.string.course_details));
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        course = (Course) getIntent().getSerializableExtra(Constants.INTENT_OBJECT);

        Picasso.with(this)
                .load(course.getImage())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder)
                .into(courseImageView);

        if (course.getInstructor() != null && course.getInstructor().getUser() != null) {
            Picasso.with(this)
                    .load(course.getInstructor().getUser().getImage())
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .resize(100, 100)
                    .centerCrop()
                    .placeholder(R.drawable.img_vector_placeholder)
                    .into(instructorImageView);

            String name = course.getInstructor().getFirstName() + " " + course.getInstructor().getLastName();
            instructorNameTextView.setText(name);

            instructorPositionTextView.setText(course.getInstructor().getPosition());
        }
        if (course.getCenter() != null && course.getCenter().getUser() != null) {

//            Picasso.with(this)
//                    .load(course.getCenter().getUser().getImage())
//                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                    .resize(100, 100)
//                    .centerCrop()
//                    .placeholder(R.drawable.img_vector_placeholder)
//                    .into(centerImageView);

            webView.getSettings().setJavaScriptEnabled(true);
            AppChromeClient appChromeClient = new AppChromeClient(this, this);
            webView.setWebViewClient(new AppWebViewClient());
            webView.setWebChromeClient(appChromeClient);

            webView.loadUrl(course.getVideoUrl());

            centerNameTextView.setText(course.getCenter().getName());
            centerAddressTextView.setText(course.getCenter().getAddress());
        }

        courseNameTextView.setText(course.getName());
        courseDescriptionTextView.setText(course.getDescription());
        courseContentTextView.setText(course.getContent());
        courseSessionsTextView.setText(String.valueOf(course.getNumberOfSessions()));
        courseMaxNumberTextView.setText(String.valueOf(course.getMaxNumbers()));
        coursePriceTextView.setText(String.valueOf(course.getPrice()));

        if (course.getSessions() != null && !course.getSessions().isEmpty()) {
            String defaultText = "- Session";
            StringBuilder text = new StringBuilder();
            int index = 1;
            for (String session : course.getSessions()) {
                text.append(defaultText).append(" ").append(index++).append("\n");
                text.append(session);
                text.append("\n" + "\n");
            }
            sessionListTextView.setText(text);
        }

        getGroups(course.getId());
    }

    @OnClick({R.id.course_apply_button, R.id.course_instructor_layout, R.id.course_center_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.course_apply_button:

                if (groups == null) {
                    getGroups(course.getId());
                    Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show();
                    return;
                } else if (groups.isEmpty()) {
                    ErrorDialog.showMessageDialog("", "No Group Found", this);
                } else {
                    openGroupDialog();
                }
                break;
            case R.id.course_instructor_layout:
                Intent i = new Intent(this, InstructorActivity.class);
                i.putExtra(Constants.INTENT_ID, course.getInstructor().getId());
                startActivity(i);
                break;
            case R.id.course_center_layout:
                Intent i1 = new Intent(this, CenterActivity.class);
                i1.putExtra(Constants.INTENT_ID, course.getCenter().getId());
                startActivity(i1);
                break;

        }
    }


    public void openGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams")
        View dialogView = inflater.inflate(R.layout.dialog_choose_group, null);

        Button submitButton = dialogView.findViewById(R.id.submit_button);
        ImageView closeImageView = dialogView.findViewById(R.id.dialog_close_imageView);
        recyclerView = dialogView.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        submitButton.setOnClickListener(v -> {
            if (submitCourseAdapter.getSelectedGroup() != null) {
                dialog.dismiss();
                Intent i = new Intent(CourseActivity.this, PaymentActivity.class);
                i.putExtra(Constants.INTENT_OBJECT, submitCourseAdapter.getSelectedGroup());
                startActivity(i);
                finish();
            } else {
                ErrorDialog.showMessageDialog(getString(R.string.invalid_request), "Select group.", this);
            }
        });


        closeImageView.setOnClickListener(v -> dialog.dismiss());

        if (groups != null) {
            submitCourseAdapter = new SubmitCourseAdapter(groups, this);
            recyclerView.setAdapter(submitCourseAdapter);
        } else {
            getGroups(course.getId());
        }
    }


    private void getGroups(String id) {
        GroupByCourseIdOperation operation = new GroupByCourseIdOperation(id, REQUEST_Groups,
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
        } else if (requestId.equals(REQUEST_Groups)) {
            groups = (List<Group>) resultObject;
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
