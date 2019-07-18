package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class NotificationHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView bodyTextView;
    public TextView dateTextView;

    public NotificationHolder(View view) {
        super(view);
        imageView = view.findViewById(R.id.item_notification_imageView);
        bodyTextView = view.findViewById(R.id.item_notification_title_textView);
        dateTextView = view.findViewById(R.id.item_notification_date_textView);
    }
}