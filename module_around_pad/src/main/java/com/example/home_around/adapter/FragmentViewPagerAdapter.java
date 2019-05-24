package com.example.home_around.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lib_generic.base.SfyBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mManger;
    private List<SfyBaseFragment>   mFragments = new ArrayList<>(3);

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mManger = fm;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(SfyBaseFragment fragment) {
        mFragments.add(fragment);
    }

    public List<SfyBaseFragment> getFragments() {
        return mFragments;
    }

}
