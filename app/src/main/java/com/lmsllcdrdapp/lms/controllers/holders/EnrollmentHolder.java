package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class EnrollmentHolder extends RecyclerView.ViewHolder {

    public CardView layout;
    public ImageView imageView;
    public TextView titleTextView;
    public TextView upcomingTextView;
    public TextView passedTextView;
    public SeekBar seekBar;

    public EnrollmentHolder(View view) {
        super(view);
        layout = view.findViewById(R.id.item_enrollment_cardView);
        imageView = view.findViewById(R.id.item_enrollment_imageView);
        titleTextView = view.findViewById(R.id.item_enrollment_title_textView);
        upcomingTextView = view.findViewById(R.id.item_enrollment_upcoming_textView);
        passedTextView = view.findViewById(R.id.item_enrollment_passed_textView);
        seekBar = view.findViewById(R.id.item_enrollment_seekBar);
    }
}