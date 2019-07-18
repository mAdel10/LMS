package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class SessionHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView;

    public SessionHolder(View view) {
        super(view);
        titleTextView = view.findViewById(R.id.item_session_title_textView);
    }
}
