package com.lmsllcdrdapp.lms.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.InstructorRate;
import com.lmsllcdrdapp.lms.backend.observers.CTHttpError;
import com.lmsllcdrdapp.lms.backend.observers.RequestObserver;
import com.lmsllcdrdapp.lms.backend.operations.InstructorRateOperation;
import com.lmsllcdrdapp.lms.dialogs.ErrorDialog;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class InstructorRateActivity extends BaseActivity implements RequestObserver {

    RatingBar ratingBar;
    private Button rate;

    private InstructorRate instructorRate;
    private static final int REQUEST_INSTRUCTOR_RATE = 1;

    public InstructorRateActivity() {
        super(R.layout.activity_instructor_rate, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {

        ratingBar = findViewById(R.id.rating_bar);
        rate = findViewById(R.id.rate_now_instructor);
        AtomicReference<Float> currentRate = new AtomicReference<>((float) 0);
        AtomicBoolean rated = new AtomicBoolean(false);

        String studentId = UserManager.getInstance().getCurrentUser().getId();
        String instructorId = (String) getIntent().getSerializableExtra(Constants.INTENT_OBJECT);

        rate.setOnClickListener(view -> {
            if (!rated.get()) {
                currentRate.set(ratingBar.getRating());
                rated.set(true);
                instructorRate = new InstructorRate(studentId, instructorId, ratingBar.getRating());
                setInstructorRate();
                finish();
            } else {
                Toast.makeText(this, "You Rated Before", Toast.LENGTH_SHORT).show();
                ratingBar.setRating(currentRate.get());
            }
        });

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
        }
    }

    @Override
    public void requestCanceled(Integer requestId, Throwable error) {

    }

    @Override
    public void updateStatus(Integer requestId, String statusMsg) {

    }

    private void setInstructorRate() {
        InstructorRateOperation operation = new InstructorRateOperation(instructorRate, REQUEST_INSTRUCTOR_RATE, false, this);
        operation.addRequestObserver(this);
        operation.execute();
    }
}
