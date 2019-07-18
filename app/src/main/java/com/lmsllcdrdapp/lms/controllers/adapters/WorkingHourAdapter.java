package com.lmsllcdrdapp.lms.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.WorkingHour;
import com.lmsllcdrdapp.lms.controllers.holders.WorkingHourHolder;

import java.util.List;

public class WorkingHourAdapter extends RecyclerView.Adapter<WorkingHourHolder> {

    private List<WorkingHour> workingHours;
    private Context context;

    public WorkingHourAdapter(List<WorkingHour> workingHours, Context context) {
        this.workingHours = workingHours;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public WorkingHourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_working_hour, parent, false);
        return new WorkingHourHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkingHourHolder holder, int position) {
        WorkingHour workingHour = workingHours.get(position);
        holder.fromTextView.setText(workingHour.getFrom());
        holder.toTextView.setText(workingHour.getTo());
    }

    @Override
    public int getItemCount() {
        return workingHours.size();
    }
}