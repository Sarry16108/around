package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.home_around.entity.HeaderData;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends PagerAdapter {

    private Context mContext;
    private List<HeaderData> mAds;

    public ViewAdapter(Context context) {
        mContext = context;
        mAds = new ArrayList<>(5);
    }

    @Override
    public int getCount() {
        return mAds.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public void setData(List<HeaderData> list) {
        mAds.addAll(list);
        notifyDataSetChanged();
    }
}
