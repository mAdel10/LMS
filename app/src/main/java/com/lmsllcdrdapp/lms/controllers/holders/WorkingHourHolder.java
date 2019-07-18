package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class WorkingHourHolder extends RecyclerView.ViewHolder {

    public TextView fromTextView;
    public TextView toTextView;

    public WorkingHourHolder(View view) {
        super(view);
        fromTextView = view.findViewById(R.id.item_working_hour_from_textView);
        toTextView = view.findViewById(R.id.item_working_hour_to_textView);
    }
}