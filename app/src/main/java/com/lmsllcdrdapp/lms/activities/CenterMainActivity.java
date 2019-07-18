package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.CenterOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.CenterPagerAdapter;
import com.lmsllcdrdapp.lms.controllers.adapters.InstructorPagerAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.managers.TokenManager;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CenterMainActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.center_imageView)
    CircleImageView centerImageView;
    @BindView(R.id.center_name_textView)
    TextView centerNameTextView;
    @BindView(R.id.center_email_textView)
    TextView centerEmailTextView;
    @BindView(R.id.center_ratingBar)
    RatingBar centerRatingBar;
    @BindView(R.id.center_rate_textView)
    TextView centerRateTextView;
    @BindView(R.id.instructor_main_edit_btn)
    Button instructorMainEditBtn;
    @BindView(R.id.instructor_main_logout_btn)
    Button instructorMainLogoutBtn;
    @BindView(R.id.center_rating_layout)
    LinearLayout centerRatingLayout;
    @BindView(R.id.center_add_group_button)
    Button centerAddGroupButton;
    @BindView(R.id.center_add_course_button)
    Button centerAddCourseButton;
    @BindView(R.id.center_calender_button)
    Button calenderButton;
    @BindView(R.id.center_actions_layout)
    LinearLayout centerActionsLayout;

    @BindView(R.id.center_main_tabLayout)
    TabLayout centerMainTabLayout;
    @BindView(R.id.center_viewPager)
    ViewPager centerViewPager;
    private FragmentManager fm;
    private CenterPagerAdapter pagerAdapter;

    private final static int REQUEST_GET_CENTER = 1;

    public CenterMainActivity() {
        super(R.layout.activity_center_main, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setVisibility(View.VISIBLE);

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        String id = UserManager.getInstance().getCurrentUser().getCenter().getId();
        getCenter(id);

        fm = (this).getSupportFragmentManager();
        initViews();
    }

    public void getCenter(String id) {
        CenterOperation centerOperation = new CenterOperation(id, REQUEST_GET_CENTER, true, this);
        centerOperation.addRequestObserver(this);
        centerOperation.execute();
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
        } else if (requestId.equals(REQUEST_GET_CENTER)) {
            Center center = (Center) resultObject;
            centerNameTextView.setText(center.getName());
            centerRatingBar.setNumStars(5);
            centerRatingBar.setRating(center.getRate());
            String rate = center.getRate() + " (" + center.getRateCount() + ")";
            centerRateTextView.setText(rate);
            centerEmailTextView.setText(center.getUser().getEmail());

            Picasso.with(this)
                    .load(center.getUser().getImage())
                    .fit()
                    .centerCrop()
                    .into(centerImageView);
        }
    }

    public void initViews() {
        pagerAdapter = new CenterPagerAdapter(fm);
        centerViewPager.setAdapter(pagerAdapter);
        centerMainTabLayout.setupWithViewPager(centerViewPager);
        centerMainTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    @OnClick({R.id.instructor_main_edit_btn, R.id.instructor_main_logout_btn,
            R.id.center_add_group_button, R.id.center_add_course_button,
            R.id.center_calender_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.instructor_main_edit_btn:
                break;
            case R.id.instructor_main_logout_btn:
                UserManager.getInstance().logout();
                TokenManager.getInstance().delete();
                Intent intent = new Intent(this, IntroActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.center_add_group_button:
                Intent intent1 = new Intent(this, AddGroupActivity.class);
                startActivity(intent1);
                break;
            case R.id.center_add_course_button:
                Intent intent2 = new Intent(this, AddCourseActivity.class);
                startActivity(intent2);
                break;
            case R.id.center_calender_button:
                Center center = UserManager.getInstance().getCurrentUser().getCenter();
                if (center.getWorkingHours() != null && !center.getWorkingHours().isEmpty()) {
                    Intent intent3 = new Intent(this, CalenderActivity.class);
                    intent3.putExtra(Constants.INTENT_ID, center);
                    startActivity(intent3);
                } else {
                    Toast.makeText(this, "No calender available.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
