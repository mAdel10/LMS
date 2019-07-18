package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class GroupHolder extends RecyclerView.ViewHolder {

    public TextView courseName;
    public TextView centerName;
    public TextView enrollmentNumber;
    public TextView groupStartDate;

    public GroupHolder(View view) {
        super(view);
        courseName = view.findViewById(R.id.item_group_course_name);
        centerName = view.findViewById(R.id.item_group_center_name);
        enrollmentNumber = view.findViewById(R.id.item_group_enrollment_number);
        groupStartDate = view.findViewById(R.id.item_group_course_start_date);
    }
}
