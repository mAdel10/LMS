package com.lmsllcdrdapp.lms.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.managers.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_logo_imageView)
    ImageView splashLogoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        splashLogoImageView = findViewById(R.id.splash_logo_imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        splashLogoImageView.startAnimation(animation);

        FirebaseMessaging.getInstance().subscribeToTopic("lms").addOnCompleteListener(task -> Log.d("subscribeToTopic", "onComplete: subscribed"));

        new Handler().postDelayed(() -> {
            Intent i;
            if (UserManager.getInstance().isUserLoggedIn()) {
                if (UserManager.getInstance().isInstructor()) {
                    i = new Intent(SplashActivity.this, InstructorMainActivity.class);
                    startActivity(i);
                } else if (UserManager.getInstance().isStudent()) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                } else if (UserManager.getInstance().isCenter()) {
                    i = new Intent(SplashActivity.this, CenterMainActivity.class);
                    startActivity(i);
                }
            } else {
                i = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(i);
            }
            finish();
        }, Constants.SPLASH_TIME_OUT);
    }
}




