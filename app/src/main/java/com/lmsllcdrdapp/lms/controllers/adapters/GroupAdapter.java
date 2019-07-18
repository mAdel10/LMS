package com.lmsllcdrdapp.lms.controllers.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.activities.ChatActivity;
import com.lmsllcdrdapp.lms.activities.SessionActivity;
import com.lmsllcdrdapp.lms.backend.models.Group;
import com.lmsllcdrdapp.lms.controllers.holders.GroupHolder;
import com.lmsllcdrdapp.lms.helpers.Constants;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder>{

    private List<Group> groups;
    private Context context;
    private Activity activity;

    public GroupAdapter(List<Group> groups, Context context, Activity activity ) {
        this.groups = groups;
        this.context = context;
        this.activity = activity;
    }

    public GroupAdapter(List<Group> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        final Group group = groups.get(position);
        if (group.getCourse() != null) {
            holder.courseName.setText(group.getCourse().getName());
            if (group.getCourse().getCenter() != null) {
                holder.courseName.setText(group.getCourse().getCenter().getName());
            }
        }
        holder.enrollmentNumber.setText(String.valueOf(group.getEnrolledNumber()));
        holder.groupStartDate.setText(group.getStartDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGroupDialog(group);
            }
        });

    }

    public void openGroupDialog(Group group) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams")
        View dialogView = inflater.inflate(R.layout.dialog_choose_instructor_group, null);
        ImageView closeImageView = dialogView.findViewById(R.id.dialog_close_imageViews);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView details = dialogView.findViewById(R.id.dialog_details_textView);
        TextView chat = dialogView.findViewById(R.id.dialog_chat_textView);

        closeImageView.setOnClickListener(v -> dialog.dismiss());

        details.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(activity, SessionActivity.class);
                i.putExtra(Constants.INTENT_OBJECT, group);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(activity, ChatActivity.class);
                i.putExtra(Constants.INTENT_ID,group.getId());
                i.putExtra(Constants.INTENT_KEY,group.getCourse().getName());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


}
