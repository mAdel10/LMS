package com.lmsllcdrdapp.lms.controllers.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.activities.SessionActivity;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.controllers.holders.EnrollmentHolder;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EnrollmentAdapter extends RecyclerView.Adapter<EnrollmentHolder> {

    private List<Group> groups;
    private Context context;

    public EnrollmentAdapter(List<Group> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public EnrollmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enrollment, parent, false);
        return new EnrollmentHolder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull EnrollmentHolder holder, int position) {
        Group group = groups.get(position);

        if (groups.get(position).getCourse() != null) {
            holder.titleTextView.setText(group.getCourse().getName());

            Picasso.with(getContext())
                    .load(groups.get(position).getCourse().getImage())
                    .placeholder(R.drawable.img_placeholder)
                    .into(holder.imageView);
        }

        holder.upcomingTextView.setText(group.getStartDate());

        //String passed = "Passed: " + group.getPassed() + "%";
        //holder.passedTextView.setText(passed);
        //holder.seekBar.setProgress(enrollment.getPassed());

        holder.itemView.setOnClickListener(v -> {
        });

        holder.seekBar.setOnTouchListener((view, motionEvent) -> true);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SessionActivity.class);
            intent.putExtra(Constants.INTENT_OBJECT, group);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
}