package com.lmsllcdrdapp.lms.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lmsllcdrdapp.lms.R;
import com.lmsllcdrdapp.lms.backend.models.Center;
import com.lmsllcdrdapp.lms.backend.models.Course;
import com.lmsllcdrdapp.lms.backend.models.WorkingHour;
import com.lmsllcdrdapp.lms.controllers.adapters.WorkingHourAdapter;
import com.lmsllcdrdapp.lms.helpers.Constants;
import com.lmsllcdrdapp.lms.utilities.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalenderActivity extends BaseActivity {

    @BindView(R.id.working_hour_1_recyclerView)
    RecyclerView workingHourRecyclerView1;
    @BindView(R.id.working_hour_2_recyclerView)
    RecyclerView workingHourRecyclerView2;
    @BindView(R.id.working_hour_3_recyclerView)
    RecyclerView workingHourRecyclerView3;
    @BindView(R.id.working_hour_4_recyclerView)
    RecyclerView workingHourRecyclerView4;
    @BindView(R.id.working_hour_5_recyclerView)
    RecyclerView workingHourRecyclerView5;
    @BindView(R.id.working_hour_6_recyclerView)
    RecyclerView workingHourRecyclerView6;
    @BindView(R.id.working_hour_7_recyclerView)
    RecyclerView workingHourRecyclerView7;

    @BindView(R.id.sun_calender_TextView)
    TextView sunTextView;
    @BindView(R.id.mon_calender_TextView)
    TextView monTextView;
    @BindView(R.id.tue_calender_TextView)
    TextView tueTextView;
    @BindView(R.id.wed_calender_TextView)
    TextView wedTextView;
    @BindView(R.id.thu_calender_TextView)
    TextView thuTextView;
    @BindView(R.id.fri_calender_TextView)
    TextView friTextView;
    @BindView(R.id.sat_calender_TextView)
    TextView satTextView;

    public CalenderActivity() {
        super(R.layout.activity_calender, true);
    }

    @Override
    protected void doOnCreate(Bundle bundle) {
        ButterKnife.bind(this);

        Center center = (Center) getIntent().getSerializableExtra(Constants.INTENT_ID);
        fillWorkingHour(center.getWorkingHours());
        toolbarTextView.setText(getString(R.string.center_calender));
        toolbarTextView.setVisibility(View.VISIBLE);
        toolbarBackButton.setVisibility(View.VISIBLE);
        toolbarBackButton.setOnClickListener(v -> finish());
    }

    private void fillWorkingHour(List<WorkingHour> workingHours) {
        initWorkingHoursRV();
        WorkingHourAdapter workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 1), this);
        workingHourRecyclerView1.setAdapter(workingHourAdapter);
        workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 2), this);
        workingHourRecyclerView2.setAdapter(workingHourAdapter);
        workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 3), this);
        workingHourRecyclerView3.setAdapter(workingHourAdapter);
        workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 4), this);
        workingHourRecyclerView4.setAdapter(workingHourAdapter);
        workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 5), this);
        workingHourRecyclerView5.setAdapter(workingHourAdapter);
        workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 6), this);
        workingHourRecyclerView6.setAdapter(workingHourAdapter);
        workingHourAdapter = new WorkingHourAdapter(getWorkingHourByDay(workingHours, 7), this);
        workingHourRecyclerView7.setAdapter(workingHourAdapter);

        selectCurrentDay();
    }

    private List<WorkingHour> getWorkingHourByDay(List<WorkingHour> workingHours, int day) {
        List<WorkingHour> newWorkingHours = new ArrayList<>();
        for (WorkingHour workingHour : workingHours) {
            if (workingHour.getDay() == day) {
                newWorkingHours.add(workingHour);
            }
        }
        return newWorkingHours;
    }

    private void initWorkingHoursRV() {
        workingHourRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workingHourRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workingHourRecyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workingHourRecyclerView4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workingHourRecyclerView5.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workingHourRecyclerView6.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workingHourRecyclerView7.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void selectCurrentDay() {
        int day = Utilities.getCurrentDayOfWeek();
        switch (day) {
            case Calendar.SUNDAY:
                sunTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sunTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case Calendar.MONDAY:
                monTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                monTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case Calendar.TUESDAY:
                tueTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tueTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case Calendar.WEDNESDAY:
                wedTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                wedTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case Calendar.THURSDAY:
                thuTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                thuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case Calendar.FRIDAY:
                friTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                friTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case Calendar.SATURDAY:
                satTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                satTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
        }
    }
}
