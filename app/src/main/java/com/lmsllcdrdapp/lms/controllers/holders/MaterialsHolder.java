package com.lmsllcdrdapp.lms.controllers.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;

public class MaterialsHolder extends RecyclerView.ViewHolder {

    public TextView materialTextView;
    public ImageButton closeImageButton;

    public MaterialsHolder(View view) {
        super(view);
        materialTextView = view.findViewById(R.id.material_textView);
        closeImageButton = view.findViewById(R.id.close_button);

    }
}