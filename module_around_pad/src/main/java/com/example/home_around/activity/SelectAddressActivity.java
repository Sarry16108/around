package com.example.home_around.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.adapter.DeliveryAddressAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.AddressInfoData;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class SelectAddressActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private TextView    mTitle;
    private TextView    mAdd;
    private RecyclerView mDatas;
    private DeliveryAddressAdapter  mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mTitle = findViewById(R.id.title);
        mAdd = findViewById(R.id.add);
        mDatas = findViewById(R.id.data_list);

        mBack.setOnClickListener(this);
        mAdd.setOnClickListener(this);

        mAdapter = new DeliveryAddressAdapter(this);
        mDatas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDatas.setAdapter(mAdapter);

        initData();
    }

    private void initData() {
        List<AddressInfoData> list = new ArrayList<>(10);
        list.add(new AddressInfoData(0, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(1, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(2, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(3, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(4, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(5, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(6, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));
        list.add(new AddressInfoData(7, "大力", "15130909090", "广东省深圳市南山区科兴科学园"));

        mAdapter.setItems(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.add:
                break;
        }
    }
}
