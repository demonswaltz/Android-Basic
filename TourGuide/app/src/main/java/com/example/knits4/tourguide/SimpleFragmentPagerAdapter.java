package com.example.knits4.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    int fagments = 4;
    private String[] tabTitles = new String[4];

    public SimpleFragmentPagerAdapter(FragmentManager fm, String tab1, String tab2, String tab3, String tab4) {
        super(fm);
        tabTitles[0] = tab1;
        tabTitles[1] = tab2;
        tabTitles[2] = tab3;
        tabTitles[3] = tab4;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HotelFragment();
        } else if (position == 1) {
            return new GasFragment();
        } else if (position == 2) {
            return new ParkFragment();
        } else {
            return new AttractionFragment();
        }
    }

    @Override
    public int getCount() {
        return fagments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
