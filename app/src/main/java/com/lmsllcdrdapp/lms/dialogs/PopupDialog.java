package com.lmsllcdrdapp.lms.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.activities.AddSessionActivity;
import com.lmsllcdrdapp.lms.utilities.Utilities;
import com.lmsllcdrdapp.lms.views.UIEngine;

public class PopupDialog {

    private static AlertDialog alert;
    private ErrorDialogListener listener;

    public PopupDialog(ErrorDialogListener listener) {
        this.listener = listener;
    }

    public void showMessageDialog(final String title, final String message, final Activity activity, final boolean showCancel) {
        if (activity == null)
            return;
        UIEngine.initialize(activity.getApplicationContext());
        activity.runOnUiThread(() -> {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            if (!Utilities.isNullString(title))
                dialog.setTitle(UIEngine.createSpannableString(title, UIEngine.Fonts.APP_FONT_SEMI_BOLD));
            dialog.setMessage(UIEngine.createSpannableString(message, UIEngine.Fonts.APP_FONT_REGULAR));
            dialog.setCancelable(false);

            dialog.setPositiveButton(activity.getString(R.string.ok), (dialog1, whichButton) -> listener.onOkClick());

            if (showCancel) {
                dialog.setNegativeButton(activity.getString(R.string.cancel), (dialog12, whichButton) -> {
                    if (alert != null && alert.isShowing())
                        alert.dismiss();
                    listener.onCancelClick();
                });
            }

            if (alert != null && alert.isShowing())
                alert.dismiss();
            alert = dialog.create();
            alert.show();
        });
    }

    public interface ErrorDialogListener {
        void onOkClick();

        void onCancelClick();
    }
}
