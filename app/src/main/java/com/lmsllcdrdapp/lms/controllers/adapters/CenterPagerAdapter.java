package com.lmsllcdrdapp.lms.controllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lmsllcdrdapp.lms.fragments.CourseFragment;
import com.lmsllcdrdapp.lms.fragments.GroupsFragment;

public class CenterPagerAdapter extends FragmentPagerAdapter {

    public CenterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CourseFragment();
            case 1:
                return new GroupsFragment();
            case 2:
                return new GroupsFragment();
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
                return "Courses";

            case 1:
                return "Groups";

            case 3:
                return "Instructors";
            default:
                return null;
        }
    }
}