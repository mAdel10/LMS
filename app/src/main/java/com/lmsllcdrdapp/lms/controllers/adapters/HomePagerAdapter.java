package com.lmsllcdrdapp.lms.controllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new Fragment();
                return fragment;

            case 1:
                fragment = new Fragment();
                return fragment;

            case 2:
                fragment = new Fragment();
                return fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Upcoming";

            case 1:
                return "Passed";

            case 2:
                return "Certificates";

            default:
                return null;
        }
    }
}
