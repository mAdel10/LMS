package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "Base_Activity";

    private int mActivityLayout;
    private boolean showToolbar;
    // Main Layout
    protected ViewGroup contentLayout;
    // Toolbar
    protected Toolbar toolbar;
    protected TextView toolbarTextView;
    protected EditText toolbarSearchEditText;
    protected ImageView toolbarFilterImageView;
    protected Button toolbarBackButton;
    protected Button toolbarAddSessionButton;
    protected ImageView toolbarQrImageView;

    public BaseActivity(int activityLayout, boolean showToolbar) {
        this.mActivityLayout = activityLayout;
        this.showToolbar = showToolbar;
    }

    /**
     * Use instead of onCreate in activities.
     *
     * @param bundle Bundle
     */
    protected abstract void doOnCreate(Bundle bundle);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.v(TAG, "onCreate");

        initToolbar();

        contentLayout = findViewById(R.id.base_frameLayout);
        LayoutInflater.from(this).inflate(mActivityLayout, contentLayout, true);
        doOnCreate(savedInstanceState);

        if (showToolbar) {
            Log.v(TAG, "Show toolbar");
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTextView = findViewById(R.id.toolbar_textView);
        toolbarBackButton = findViewById(R.id.toolbar_back_button);
        toolbarSearchEditText = findViewById(R.id.toolbar_search_editText);
        toolbarFilterImageView = findViewById(R.id.toolbar_filter_imageView);
        toolbarQrImageView = findViewById(R.id.toolbar_qr_imageView);
        toolbarAddSessionButton = findViewById(R.id.toolbar_add_session_button);
        setSupportActionBar(toolbar);
    }
}
