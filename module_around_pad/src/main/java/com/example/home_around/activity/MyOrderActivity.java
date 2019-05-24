package com.example.home_around.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_around.adapter.FragmentViewPagerAdapter;
import com.example.home_around.adapter.MerchantsAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.MerchantsData;
import com.example.home_around.fragments.OrderAllFragment;
import com.example.home_around.fragments.OrderFinishedFragment;
import com.example.home_around.fragments.OrderRefundFragment;
import com.example.home_around.fragments.OrderWaitPayFragment;
import com.example.home_around.fragments.OrderWaitReceiveFragment;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家列表：要优化的地方多了，问题是没时间
 */
public class MyOrderActivity extends BaseNormalActivity implements View.OnClickListener {
    private ViewPager               mDataList;
    private FragmentViewPagerAdapter mAdapter;

    private ImageView   mBack;
    private TextView    mAll;
    private TextView    mWaitPay;
    private TextView    mWaitReceive;
    private TextView    mFinished;
    private TextView    mRefund;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mDataList = findViewById(R.id.data_list);
        mBack = findViewById(R.id.back);
        mAll = findViewById(R.id.all);
        mWaitPay = findViewById(R.id.wait_pay);
        mWaitReceive = findViewById(R.id.wait_receive);
        mFinished = findViewById(R.id.finished);
        mRefund = findViewById(R.id.refund);

        mBack.setOnClickListener(this);
        mAll.setOnClickListener(this);
        mWaitPay.setOnClickListener(this);
        mWaitReceive.setOnClickListener(this);
        mFinished.setOnClickListener(this);
        mRefund.setOnClickListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new FragmentViewPagerAdapter(fragmentManager);
        mAdapter.addFragment(new OrderAllFragment());
        mAdapter.addFragment(new OrderWaitPayFragment());
        mAdapter.addFragment(new OrderWaitReceiveFragment());
        mAdapter.addFragment(new OrderFinishedFragment());
        mAdapter.addFragment(new OrderRefundFragment());

        mAll.setSelected(true);
        mDataList.setAdapter(mAdapter);
        mDataList.setCurrentItem(0);
        mDataList.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                mAll.setSelected(0 == i);
                mWaitPay.setSelected(1 == i);
                mWaitReceive.setSelected(2 == i);
                mFinished.setSelected(3 == i);
                mRefund.setSelected(4 == i);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.all:
                mDataList.setCurrentItem(0);
                break;
            case R.id.wait_pay:
                mDataList.setCurrentItem(1);
                break;
            case R.id.wait_receive:
                mDataList.setCurrentItem(2);
                break;
            case R.id.finished:
                mDataList.setCurrentItem(3);
                break;
            case R.id.refund:
                mDataList.setCurrentItem(4);
                break;
        }
    }
}
