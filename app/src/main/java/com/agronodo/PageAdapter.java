package com.agronodo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.agronodo.Tabs.Tab1;

public class PageAdapter extends FragmentStatePagerAdapter{

    int numTabs;

    public PageAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
