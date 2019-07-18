package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.GroupID;
import com.lmsllcdrdapp.lms.backend.models.SignForm;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.ApplyGroupOperation;
import com.lmsllcdrdapp.lms.backend.operations.UserSignInOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.dialogs.PopupDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity implements RequestObserver {

    @BindView(R.id.course_name_textView)
    TextView courseNameTextView;
    @BindView(R.id.course_date_textView)
    TextView courseDateTextView;
    @BindView(R.id.course_price_textView)
    TextView coursePriceTextView;
    @BindView(R.id.pay_button)
    Button payButton;

    Group group;
    private static final int REQUEST_GROUP_APPLY = 1;


    public PaymentActivity() {
        super(R.layout.activity_payment, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarTextView.setText(getString(R.string.payment));

        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());

        group = (Group) getIntent().getSerializableExtra(Constants.INTENT_OBJECT);
        courseNameTextView.setText(group.getCourse().getName());
        courseDateTextView.setText(group.getStartDate());
        coursePriceTextView.setText(String.valueOf(group.getCourse().getPrice()));
    }

    @OnClick(R.id.pay_button)
    public void onViewClicked() {
        groupID(group.getId());
    }

    private void groupID(String id) {
        GroupID groupID = new GroupID(id);
        ApplyGroupOperation operation = new ApplyGroupOperation(groupID, REQUEST_GROUP_APPLY, true, this);
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
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), getString(R.string.request_server_error), this);
                } else if (code != 401) {
                    ErrorDialog.showMessageDialog(getString(R.string.invalid_request), errorMsg, this);
                }
            }
        } else if (resultObject != null) {
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
            popupDialog.showMessageDialog("Success!", "You applied to course successfully.", this, false);
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }
}
