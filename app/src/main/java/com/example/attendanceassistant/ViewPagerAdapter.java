package com.example.attendanceassistant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Time_table_fragment time_table_fragment=new Time_table_fragment();
        position=position+1;
        Bundle bundle=new Bundle();
        String day=null;
        if(position==1)
            day="Monday";
        else
        if (position==2)
            day="Tuesday";
        else
        if (position==3)
            day="Wednesday";
        else
        if (position==4)
            day="Thursday";
        else
        if (position==5)
            day="Friday";
        else
        if (position==6)
            day="Saturday";
        else
        if (position==7)
            day="Sunday";
        bundle.putString("message",day);
        time_table_fragment.setArguments(bundle);
        return time_table_fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    public CharSequence getPageTitle(int position)
    {
        position=position+1;
        String day=null;
        if(position==1)
            day="Monday";
        else
        if (position==2)
            day="Tuesday";
        else
        if (position==3)
            day="Wednesday";
        else
        if (position==4)
            day="Thursday";
        else
        if (position==5)
            day="Friday";
        else
        if (position==6)
            day="Saturday";
        else
        if (position==7)
            day="Sunday";
        return day;
    }
}
