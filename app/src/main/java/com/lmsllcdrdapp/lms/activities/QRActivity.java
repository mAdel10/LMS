package com.lmsllcdrdapp.lms.activities;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Button;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.dialogs.PopupDialog;

public class QRActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener, PopupDialog.ErrorDialogListener {

    private QRCodeReaderView qrCodeReaderView;
    private Button cancelButton;

    public QRActivity() {
        super(R.layout.activity_qr, false);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        qrCodeReaderView = findViewById(R.id.qrdecoderview);
        cancelButton = findViewById(R.id.qr_cancel_button);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setQRDecodingEnabled(true);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setTorchEnabled(true);
        // Use this function to set front camera preview
        // qrCodeReaderView.setFrontCamera();
        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();
        cancelButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        PopupDialog popupDialog = new PopupDialog(this);
        text = text.replace("%20", " ");
        String[] qrContent = text.split("-");
        String message = "Your attendance has been recorded for " + qrContent[2] + " at " + qrContent[1] + " course.";
        popupDialog.showMessageDialog("Valid QR", message, this, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }

    @Override
    public void onOkClick() {
        finish();
    }

    @Override
    public void onCancelClick() {

    }
}
