package com.jeong.covidinfo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MypagerAdapter extends FragmentStateAdapter {
    public int mCount;
    public MypagerAdapter(FragmentActivity fragmentActivity, int count){
        super(fragmentActivity);
        mCount = count;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //int index = getRealPosition(position);
        if(position == 0) return new Fragment_1();
        else if(position == 1) return  new Fragment_2();
//        else if(index == 2) return  new Fragment_3();
        else return new Fragment_3();
    }

    @Override
    public int getItemCount() {
        return mCount; //스크롤 할수 있는 횟수? 갯수?
    }
    //public int getRealPosition(int position) { return position % mCount; }
}
