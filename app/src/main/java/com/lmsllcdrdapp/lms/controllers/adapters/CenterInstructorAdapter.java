package com.lmsllcdrdapp.lms.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.Instructor;
import com.lmsllcdrdapp.lms.controllers.holders.CenterInstructorHolder;

import java.util.Collection;
import java.util.List;

public class CenterInstructorAdapter extends RecyclerView.Adapter<CenterInstructorHolder> {

    private List<Center> centers;
    private List<Instructor> instructors;
    private Context context;
    private int type;

    public static final int CENTER = 1;
    public static final int INSTRUCTORS = 2;

    @SuppressWarnings("unchecked")
    public CenterInstructorAdapter(List<?> list, Context context, int type) {
        this.type = type;
        switch (type) {
            case CENTER:
                this.centers = (List<Center>) list;
                break;
            case INSTRUCTORS:
                this.instructors = (List<Instructor>) list;
                break;
        }
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public CenterInstructorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_center_instructor, parent, false);
        return new CenterInstructorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterInstructorHolder holder, int position) {
        if (centers != null) {
            Center center = centers.get(position);
            holder.nameTextView.setText(center.getName());

        } else if (instructors != null) {
            Instructor instructor = instructors.get(position);
            String name = instructor.getFirstName() + " " + instructor.getLastName();
            holder.nameTextView.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        switch (type) {
            case CENTER:
                return centers.size();
            case INSTRUCTORS:
                return instructors.size();
            default:
                return centers.size();
        }
    }

    @SuppressWarnings("unchecked")
    public void addItems(List<?> newItems) {
        switch (type) {
            case CENTER:
                centers.addAll((Collection<? extends Center>) newItems);
                break;
            case INSTRUCTORS:
                instructors.addAll((Collection<? extends Instructor>) newItems);
                break;
        }
        notifyDataSetChanged();
    }
}