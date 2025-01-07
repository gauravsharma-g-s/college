package com.example.android.college.papers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PapersFragmentPagerAdapter extends FragmentPagerAdapter {
    Context mContext;

    public PapersFragmentPagerAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        mContext=context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Semester1();
            case 1:
                return new semester2();
            case 2:
                return new semester3();
            case 3:
                return new semester4();
            case 4:
                return new semester5();
            case 5:
                return new semester6();
            case 6:
                return new semester7();
            case 7:
                return new semester8();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Semester 1";
            case 1:
                return "Semester 2";
            case 2:
                return "Semester 3";
            case 3:
                return "Semester 4";
            case 4:
                return "Semester 5";
            case 5:
                return "Semester 6";
            case 6:
                return "Semester 7";
            case 7:
                return "Semester 8";
            default:
                return null;


        }
    }
}
