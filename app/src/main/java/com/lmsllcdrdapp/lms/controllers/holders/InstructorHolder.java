package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class InstructorHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView nameTextView;
    public TextView position;
    public TextView cv;
    public RatingBar ratingBar;
    public TextView ratingBarText;

    public InstructorHolder(View view) {
        super(view);

        imageView = view.findViewById(R.id.instructor_imageView);
        nameTextView = view.findViewById(R.id.instructor_name_textView);
        position = view.findViewById(R.id.instructor_get_details_position);
        cv = view.findViewById(R.id.instructor_get_details_cv);
        ratingBar = view.findViewById(R.id.instructor_details_ratingBar);
        ratingBarText = view.findViewById(R.id.item_instructor_rate_textView);
    }
}

