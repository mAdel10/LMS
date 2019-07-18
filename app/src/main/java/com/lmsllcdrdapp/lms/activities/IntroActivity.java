package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lmsllcdrdapp.lms.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroActivity extends BaseActivity {

    @BindView(R.id.intro_student_button)
    Button studentButton;
    @BindView(R.id.intro_educational_center_button)
    Button educationalCenterButton;
    @BindView(R.id.intro_instructor_button)
    Button instructorButton;
    @BindView(R.id.into_sign_in_button)
    Button signInButton;

    public IntroActivity() {
        super(R.layout.activity_intro, false);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
    }

    @OnClick({R.id.intro_student_button,
            R.id.intro_educational_center_button,
            R.id.intro_instructor_button,
            R.id.into_sign_in_button})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.intro_student_button:
                intent = new Intent(this, SignUpActivity.class);
                break;
            case R.id.intro_educational_center_button:
                intent = new Intent(this, CenterSignUpActivity.class);
                break;
            case R.id.intro_instructor_button:
                intent = new Intent(this, InstructorSignUpActivity.class);
                break;
            case R.id.into_sign_in_button:
                intent = new Intent(this, SignInActivity.class);
                break;

        }
        startActivity(intent);
    }
}
