package com.lmsllcdrdapp.lms.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.backend.models.Student;
import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.UserProfileOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.CenterPagerAdapter;
import com.lmsllcdrdapp.lms.controllers.adapters.ProfilePageAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.squareup.picasso.Picasso;

public class profileActivity extends BaseActivity implements RequestObserver {
    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;

    private TabLayout profile_tabLayout;
    private ViewPager profileViewPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentManager fm;
    private ProfilePageAdapter pagerAdapter;


    private static final int REQUEST_USERS_PROFILE = 1;
    private static final int GET_CURRENT_COURSE = 2;
    private static final int GET_PASSED_COURSE = 3;

    public profileActivity() {
        super(R.layout.activity_profile, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getText(R.string.profile));
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        profileImageView = findViewById(R.id.profile_profile_imageView);
        nameTextView = findViewById(R.id.profile_name_textView);
        emailTextView = findViewById(R.id.profile_email_textView);
        phoneTextView = findViewById(R.id.profile_phone_textView);

        viewPager = findViewById(R.id.profile_viewPager);
        tabLayout = findViewById(R.id.profile_tabLayout);

        fm = (this).getSupportFragmentManager();

        pagerAdapter = new ProfilePageAdapter(fm);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        if (!Utilities.isConnected(this)) {
            fillData(UserManager.getInstance().getCurrentUser());
        } else {
            getUserProfile();
        }
    }

    private void getUserProfile() {
        UserProfileOperation operation = new UserProfileOperation(REQUEST_USERS_PROFILE,
                false, this);
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
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), this.getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(this.getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (resultObject != null && (int) requestId == REQUEST_USERS_PROFILE) {
            User user = (User) resultObject;
            fillData(user);
        }
    }

    private void fillData(User user) {
        Picasso.with(this)
                .load(user.getImage())
                .placeholder(R.drawable.img_vector_placeholder)
                .into(profileImageView);
        String name = user.getStudent().getFirstName() + " " + user.getStudent().getLastName();
        nameTextView.setText(name);

        emailTextView.setText(user.getEmail());
        phoneTextView.setText(user.getPhone());
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
