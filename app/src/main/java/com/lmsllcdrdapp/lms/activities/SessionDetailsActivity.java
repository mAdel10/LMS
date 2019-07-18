package com.lmsllcdrdapp.lms.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.Session;
import com.lmsllcdrdapp.lms.controllers.adapters.MaterialAdapter;
import com.lmsllcdrdapp.lms.dialogs.QRDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.views.AppChromeClient;
import com.lmsllcdrdapp.lms.views.AppWebViewClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SessionDetailsActivity extends BaseActivity {

    @BindView(R.id.sessions_details_videoView)
    WebView sessionsDetailsVideoView;
    @BindView(R.id.sessions_details_quiz_btn)
    Button sessionsDetailsQuizBtn;
    @BindView(R.id.sessions_details_qr_button)
    Button qRCodeButton;

    private Group group;
    private Session session;

    private RecyclerView recyclerView;
    MaterialAdapter materialAdapter;
    private List<String> material;

    public SessionDetailsActivity() {
        super(R.layout.activity_session_details, true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.dession_details));

        session = (Session) getIntent().getSerializableExtra(Constants.INTENT_SESSION);
        group = (Group) getIntent().getSerializableExtra(Constants.INTENT_GROUP);
        material = session.getMaterials();

        if (session.getVideoURL() != null) {
            sessionsDetailsVideoView.getSettings().setJavaScriptEnabled(true);
            AppChromeClient appChromeClient = new AppChromeClient(this, this);
            sessionsDetailsVideoView.setWebViewClient(new AppWebViewClient());
            sessionsDetailsVideoView.setWebChromeClient(appChromeClient);
            sessionsDetailsVideoView.loadUrl(session.getVideoURL());
        }

        if (UserManager.getInstance().isStudent()) {
            qRCodeButton.setVisibility(View.GONE);
        }

        recyclerView = findViewById(R.id.sessions_details_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));

        materialAdapter = new MaterialAdapter(material, this);
        recyclerView.setAdapter(materialAdapter);
    }

    @OnClick({R.id.sessions_details_quiz_btn, R.id.sessions_details_qr_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sessions_details_quiz_btn:
                Uri quiz = Uri.parse(session.getQuizURL());
                Intent i = new Intent(Intent.ACTION_VIEW, quiz);
                startActivity(i);
                break;
            case R.id.sessions_details_qr_button:
                QRDialog.showDialog(this, group, session);
                break;
        }
    }
}