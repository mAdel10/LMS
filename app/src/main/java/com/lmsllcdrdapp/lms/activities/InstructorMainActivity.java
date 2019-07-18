package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.InstructorOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.InstructorPagerAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.TokenManager;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstructorMainActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.instructor_imageView)
    ImageView instructorImageView;
    @BindView(R.id.instructor_name_textView)
    TextView instructorNameTextView;
    @BindView(R.id.instructor_email_textView)
    TextView instructorEmailTextView;
    @BindView(R.id.instructor_ratingBar)
    RatingBar instructorRatingBar;
    @BindView(R.id.instructor_rate_textView)
    TextView instructorRateTextView;

    private final static int REQUEST_INSTRUCTOR_BY_ID = 1;
    @BindView(R.id.instructor_main_edit_btn)
    Button instructorMainEditBtn;
    @BindView(R.id.instructor_main_logout_btn)
    Button instructorMainLogoutBtn;
    @BindView(R.id.instructor_rating_layout)
    LinearLayout instructorRatingLayout;
    //    @BindView(R.id.instructor_add_group_button)
//    Button instructorAddGroupButton;
//    @BindView(R.id.instructor_add_course_button)
//    Button instructorAddCourseButton;
    @BindView(R.id.instructor_actions_layout)
    LinearLayout instructorActionsLayout;
    //    @BindView(R.id.instructor_courses_tabItem)
//    TabItem instructorCoursesTabItem;
//    @BindView(R.id.instructor_groups_tabItem)
//    TabItem instructorGroupsTabItem;
    @BindView(R.id.instructor_main_tabLayout)
    TabLayout instructorMainTabLayout;
    @BindView(R.id.instructor_viewPager)
    ViewPager instructorViewPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentManager fm;
    private InstructorPagerAdapter pagerAdapter;

    public InstructorMainActivity() {
        super(R.layout.activity_instructor_main, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        fm = (this).getSupportFragmentManager();
        tabLayout = findViewById(R.id.instructor_main_tabLayout);
        viewPager = findViewById(R.id.instructor_viewPager);

        initViews();
        String id = UserManager.getInstance().getCurrentUser().getInstructor().getId();
        getInstructor(id);
    }

    public void initViews() {
        pagerAdapter = new InstructorPagerAdapter(fm);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    public void getInstructor(String id) {
        InstructorOperation instructorOperation = new InstructorOperation(id, REQUEST_INSTRUCTOR_BY_ID, true, this);
        instructorOperation.addRequestObserver(this);
        instructorOperation.execute();
    }

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
        } else if (requestId.equals(REQUEST_INSTRUCTOR_BY_ID)) {
            Instructor instructor = (Instructor) resultObject;
            String name = instructor.getFirstName() + " " + instructor.getLastName();
            instructorNameTextView.setText(name);
            instructorRatingBar.setNumStars(5);
            String rate = instructor.getRate() + " (" + instructor.getRateCount() + ")";
            instructorRatingBar.setRating(instructor.getRate());
            instructorRateTextView.setText(rate);
            instructorEmailTextView.setText(instructor.getUser().getEmail());

            Picasso.with(this)
                    .load(instructor.getUser().getImage())
                    .fit()
                    .centerCrop()
                    .into(instructorImageView);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    @OnClick({R.id.instructor_main_edit_btn, R.id.instructor_main_logout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.instructor_main_edit_btn:
                Intent intent = new Intent(this, EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.instructor_main_logout_btn:
                UserManager.getInstance().logout();
                TokenManager.getInstance().delete();

                intent = new Intent(this, IntroActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
