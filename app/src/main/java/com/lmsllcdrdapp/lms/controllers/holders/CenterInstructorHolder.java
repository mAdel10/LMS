package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class CenterInstructorHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;

    public CenterInstructorHolder(View view) {
        super(view);
        nameTextView = view.findViewById(R.id.item_center_instructor_textView);
    }
}