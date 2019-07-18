package com.lmsllcdrdapp.lms.activities;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.fragments.ChatFragment;
import com.lmsllcdrdapp.lms.fragments.EnrollmentsFragment;
import com.lmsllcdrdapp.lms.fragments.MainFragment;
import com.lmsllcdrdapp.lms.fragments.MoreFragment;
import com.lmsllcdrdapp.lms.fragments.NotificationFragment;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.lmsllcdrdapp.lms.views.UIEngine;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener, View.OnClickListener, RequestObserver {

    private BottomNavigationView bottomNavigationView;

    public MainActivity() {
        super(R.layout.activity_main, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        initBottomNavigation();
        loadStartFragment();
        toolbarSearchEditText.setVisibility(View.VISIBLE);
        toolbarFilterImageView.setVisibility(View.VISIBLE);
        toolbarQrImageView.setVisibility(View.VISIBLE);

        toolbarQrImageView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QRActivity.class);
            startActivity(intent);
        });

        toolbarSearchEditText.setCursorVisible(false);
        toolbarSearchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String searchWord = toolbarSearchEditText.getText().toString().trim();
                if (!searchWord.isEmpty()) {
                    performSearch(searchWord);
                    toolbarSearchEditText.setText("");
                    View view = MainActivity.this.getCurrentFocus();
                    if (view != null) {
                        Utilities.hideSoftKeyboard(MainActivity.this, view);
                    }
                }
                return true;
            }
            return false;
        });

        toolbarSearchEditText.setOnClickListener(v -> {
            toolbarSearchEditText.requestFocus();
            toolbarSearchEditText.setCursorVisible(true);
        });

        toolbarSearchEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) toolbarSearchEditText.setCursorVisible(false);
        });

        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        }

        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    private void performSearch(String word) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.INTENT_KEY, word);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission denied to open you Camera.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 2: {
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initBottomNavigation() {
        UIEngine.initialize(MainActivity.this);
        bottomNavigationView = findViewById(R.id.navigation);
        Menu m = bottomNavigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    UIEngine.applyFontToMenuItem(subMenuItem, UIEngine.Fonts.APP_FONT_LIGHT);
                }
            }

            UIEngine.applyFontToMenuItem(mi, UIEngine.Fonts.APP_FONT_LIGHT);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
    }

    private void loadStartFragment() {
        loadFragment(new MainFragment());
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                toolbarTextView.setText(R.string.app_name);
                loadStartFragment();
                break;
            case R.id.navigation_enrollments:
                toolbarTextView.setText(R.string.enrollments);
                loadFragment(new EnrollmentsFragment());
                break;
            case R.id.navigation_chat:
                toolbarTextView.setText(R.string.chat);
                loadFragment(new ChatFragment());
                break;
            case R.id.navigation_notification:
                toolbarTextView.setText(R.string.notifications);
                loadFragment(new NotificationFragment());
                break;
            case R.id.navigation_more:
                toolbarTextView.setText(R.string.notifications);
                loadFragment(new MoreFragment());
                break;
            default:
                break;
        }

        if (toolbarTextView.getText().toString().equals(getString(R.string.app_name))) {
            toolbarFilterImageView.setVisibility(View.VISIBLE);
        } else {
            toolbarFilterImageView.setVisibility(View.GONE);
        }

        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void handleRequestFinished(Object requestId, Throwable error, Object resultObject) {

    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
