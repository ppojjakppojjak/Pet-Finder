package com.ppojjakppojjak.ppojjakppojjak;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter_cat extends FragmentStatePagerAdapter{
    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_cat(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                TabFragment1_cat tabFragment1_cat = new TabFragment1_cat();
                return tabFragment1_cat;
            case 1:
                TabFragment2_cat tabFragment2_cat = new TabFragment2_cat();
                return tabFragment2_cat;
            case 2:
                TabFragment3_cat tabFragment3_cat = new TabFragment3_cat();
                return tabFragment3_cat;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
