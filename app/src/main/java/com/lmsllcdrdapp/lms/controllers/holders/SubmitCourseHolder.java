package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class SubmitCourseHolder extends RecyclerView.ViewHolder {

    public TextView groupTextView;
    public CheckBox groupCheckBox;

    public SubmitCourseHolder(View view) {
        super(view);
        groupTextView = view.findViewById(R.id.group_textView);
        groupCheckBox = view.findViewById(R.id.group_checkbox);
    }
}