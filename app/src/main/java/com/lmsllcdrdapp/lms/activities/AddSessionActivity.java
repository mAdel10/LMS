package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.Session;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.AddSessionOperation;
import com.lmsllcdrdapp.lms.controllers.adapters.MaterialsAdapter;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.dialogs.PopupDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSessionActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.video_url_editText)
    EditText videoUrlEditText;
    @BindView(R.id.materials_editText)
    EditText materialsEditText;
    @BindView(R.id.quiz_editText)
    EditText quizEditText;
    @BindView(R.id.date_editText)
    EditText dateEditText;
    @BindView(R.id.time_editText)
    EditText timeEditText;
    @BindView(R.id.form_createSession_button)
    Button formCreateSessionButton;
    @BindView(R.id.add_material_button)
    ImageButton addMaterialButton;

    private RecyclerView recyclerView;
    private MaterialsAdapter materialsAdapter;

    private String video_url;
    private String getMaterial;
    private List<String> materials;
    private String quiz;
    private String date;
    private String time;
    private Group group;

    private final static int REQUEST_SESSION = 1;

    public AddSessionActivity() {
        super(R.layout.activity_add_session, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.add_session));
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        group = (Group) getIntent().getSerializableExtra(Constants.INTENT_GROUP);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        materials = new ArrayList<>();
        materialsAdapter = new MaterialsAdapter(materials, this);
        recyclerView.setAdapter(materialsAdapter);
    }

    @OnClick({R.id.form_createSession_button, R.id.add_material_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.form_createSession_button:
                getInputData();
                break;
            case R.id.add_material_button:
                addItem();
                break;
        }
    }

    private void getInputData() {
        video_url = videoUrlEditText.getText().toString().trim();
        materials = materialsAdapter.getMaterial();
        quiz = quizEditText.getText().toString().trim();
        date = dateEditText.getText().toString().trim();
        time = timeEditText.getText().toString().trim();

        if (video_url.isEmpty() || materials.isEmpty() || quiz.isEmpty() || date.isEmpty() || time.isEmpty()) {
            if (video_url.isEmpty()) {
                videoUrlEditText.setError("Is Required");
            } else if (materials.isEmpty()) {
                materialsEditText.setError("Is Required");
            } else if (quiz.isEmpty()) {
                quizEditText.setError("Is Required");
            } else if (date.isEmpty()) {
                dateEditText.setError("Is Required");
            } else {
                timeEditText.setError("Is Required");
            }
        } else {
            addSession();
        }
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
        } else if (resultObject != null && requestId.equals(REQUEST_SESSION)) {
            PopupDialog popupDialog = new PopupDialog(new PopupDialog.ErrorDialogListener() {
                @Override
                public void onOkClick() {
                    finish();
                }

                @Override
                public void onCancelClick() {
                    finish();
                }
            });
            popupDialog.showMessageDialog("Success", "You Add Session successfully.", AddSessionActivity.this, false);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    public void addSession() {
        Session session = new Session(group.getId(), video_url, materials, quiz, date + " " + time);
        AddSessionOperation operation = new AddSessionOperation(session, REQUEST_SESSION, true, this);
        operation.addRequestObserver(this);
        operation.execute();
    }

    private void addItem() {
        getMaterial = materialsEditText.getText().toString().trim();
        materialsAdapter.addItem(getMaterial);
        materialsEditText.setText("");
    }
}
