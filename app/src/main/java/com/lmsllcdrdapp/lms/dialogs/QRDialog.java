package com.lmsllcdrdapp.lms.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.backend.models.Session;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.Context.WINDOW_SERVICE;

public class QRDialog {

    private static final String URL = "https://www.lms.com/-";

    public static void showDialog(Activity activity, Group group, Session session) {
        @SuppressLint("InflateParams")
        View v = activity.getLayoutInflater().inflate(R.layout.dialog_qr_code, null);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
        mBuilder.setView(v);

        ImageView imageView = v.findViewById(R.id.dialog_qr_imageView);
        ImageView closeImageView = v.findViewById(R.id.dialog_qr_close_imageView);

        WindowManager manager = (WindowManager) activity.getSystemService(WINDOW_SERVICE);
        assert manager != null;
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        String inputValue = URL + group.getCourse().getName() + "-" + session.getSessionNumber();
        QRGEncoder qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);

        try {
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v(QRDialog.class.getName(), e.toString());
        }

        Dialog dialog = mBuilder.create();
        dialog.setCancelable(true);
        dialog.show();

        closeImageView.setOnClickListener(view -> dialog.dismiss());
    }
}
