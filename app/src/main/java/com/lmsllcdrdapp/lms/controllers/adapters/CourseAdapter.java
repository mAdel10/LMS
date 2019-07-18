package com.lmsllcdrdapp.lms.controllers.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.activities.CourseActivity;
import com.lmsllcdrdapp.lms.activities.MainActivity;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.controllers.holders.CourseHolder;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

    private List<Course> courses;
    private Context context;

    private Center center;
    private Instructor instructor;

    public CourseAdapter(List<Course> courses, Context context, Center center, Instructor instructor) {
        this.courses = courses;
        this.context = context;
        this.center = center;
        this.instructor = instructor;
    }

    private Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        Course course = courses.get(position);
        if (center != null) course.setCenter(center);
        if (instructor != null) course.setInstructor(instructor);

        Picasso.with(getContext())
                .load(course.getImage())
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder)
                .into(holder.courseImageView);

        holder.courseNameTextView.setText(course.getName());

        if (course.getCenter() != null) {
            String name = course.getCenter().getName();
            holder.centerNameTextView.setText(name);
            holder.locationTextView.setText(course.getCenter().getAddress());
        }

        holder.coursePriceTextView.setText(String.valueOf(course.getPrice()));
        holder.ratingBar.setRating(course.getRate());

        if (course.getRateCount() != 0) {
            String rate = ((course.getRate() / course.getRateCount()) + "/5");
            holder.rateTextView.setText(rate);
        } else {
            holder.rateTextView.setText("0/5");
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CourseActivity.class);
            intent.putExtra(Constants.INTENT_OBJECT, course);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
