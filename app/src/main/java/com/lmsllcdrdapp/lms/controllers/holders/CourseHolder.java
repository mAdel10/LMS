package com.lmsllcdrdapp.lms.controllers.holders;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class CourseHolder extends RecyclerView.ViewHolder {

    public CardView layout;
    public ImageView courseImageView;
    public TextView courseNameTextView;
    public TextView coursePriceTextView;
    public TextView centerNameTextView;
    public TextView rateTextView;
    public TextView locationTextView;
    public RatingBar ratingBar;

    public CourseHolder(View view) {
        super(view);
        layout = view.findViewById(R.id.item_course_cardView);
        courseImageView = view.findViewById(R.id.item_course_imageView);
        courseNameTextView = view.findViewById(R.id.item_course_title_textView);
        coursePriceTextView = view.findViewById(R.id.item_course_price_textView);
        centerNameTextView = view.findViewById(R.id.item_course_center_name_textView);
        rateTextView = view.findViewById(R.id.item_course_rate_textView);
        locationTextView = view.findViewById(R.id.item_course_location_textView);
        ratingBar = view.findViewById(R.id.item_course_ratingBar);

    }
}