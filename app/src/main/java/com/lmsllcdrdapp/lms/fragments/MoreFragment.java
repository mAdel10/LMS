package com.lmsllcdrdapp.lms.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.activities.EditProfileActivity;
import com.lmsllcdrdapp.lms.activities.IntroActivity;
import com.lmsllcdrdapp.lms.activities.profileActivity;
import com.lmsllcdrdapp.lms.backend.models.EditProfileForm;
import com.lmsllcdrdapp.lms.backend.models.Student;
import com.lmsllcdrdapp.lms.backend.models.User;
import com.lmsllcdrdapp.lms.managers.TokenManager;
import com.lmsllcdrdapp.lms.managers.UserManager;
import com.squareup.picasso.Picasso;

public class MoreFragment extends Fragment implements View.OnClickListener {
    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private TextView logoutTextView;
    private Button editProfileBtn;
    private Button profileBtn;
    private Context context;
    private User user;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        context = getActivity().getApplicationContext();

        init(view);

        if (UserManager.getInstance().isUserLoggedIn()) {
            user = UserManager.getInstance().getCurrentUser();
            fillData(user.getStudent());
            Picasso.with(getActivity().getApplicationContext())
                    .load(user.getImage())
                    .placeholder(R.drawable.img_vector_placeholder)
                    .into(profileImageView);

            emailTextView.setText(user.getEmail());
            phoneTextView.setText(user.getPhone());

        }
        return view;
    }

    private void init(View view) {
        profileImageView = view.findViewById(R.id.more_imageView);
        nameTextView = view.findViewById(R.id.more_name_textView);
        emailTextView = view.findViewById(R.id.more_email_textView);
        phoneTextView = view.findViewById(R.id.more_phone_textView);

        editProfileBtn = view.findViewById(R.id.more_edit_profile_button);
        editProfileBtn.setOnClickListener(this);

        logoutTextView = view.findViewById(R.id.more_logout_button);
        logoutTextView.setOnClickListener(this);

        profileBtn = view.findViewById(R.id.more_profile_lms_button);
        profileBtn.setOnClickListener(this);
    }

    private void fillData(Student student) {
        String name = student.getFirstName() + " " + student.getLastName();
        nameTextView.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_logout_button:
                UserManager.getInstance().logout();
                TokenManager.getInstance().delete();
                Intent intent = new Intent(context, IntroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                getActivity().finish();
                break;
            case R.id.more_edit_profile_button:
                 intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            case R.id.more_profile_lms_button:
                 intent = new Intent(context, profileActivity.class);
                startActivity(intent);
        }
    }
}
