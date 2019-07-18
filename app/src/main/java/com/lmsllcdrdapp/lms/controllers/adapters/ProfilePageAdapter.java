package com.lmsllcdrdapp.lms.controllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lmsllcdrdapp.lms.fragments.CurrentCourseFragment;
import com.lmsllcdrdapp.lms.fragments.PassedFragment;

public class ProfilePageAdapter extends FragmentPagerAdapter {

    public ProfilePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentCourseFragment();
            case 1:
                return new PassedFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Current";

            case 1:
                return "Passed";

            default:
                return null;
        }
    }
}