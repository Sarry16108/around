package com.example.home_around.activity;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_around.adapter.MerchantsAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.MerchantsData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家列表
 */
public class MerchantsActivity extends BaseNormalActivity implements View.OnClickListener {
    private RecyclerView    mDataList;
    private MerchantsAdapter mAdapter;

    private ImageView   mBack;
    private TextView    mTitle;
    private RelativeLayout  mShoppingCart;
    private TextView    mItemsCount;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_merchants;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mDataList = findViewById(R.id.data_list);
        mBack = findViewById(R.id.back);
        mTitle = findViewById(R.id.title);
        mShoppingCart = findViewById(R.id.shopping_cart);
        mItemsCount = findViewById(R.id.items_count);


        mBack.setOnClickListener(this);
        mShoppingCart.setOnClickListener(this);



        mAdapter = new MerchantsAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mDataList.setLayoutManager(gridLayoutManager);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                MerchantsData.MerchantsItemData itemData = mAdapter.getItem(pos);

                ActManager.toMerchantDetailOfActual(MerchantsActivity.this, itemData.getId());
            }

        });

        mTitle.setText("商家列表");
        initData();
    }

    private void initData() {
        List<MerchantsData.MerchantsItemData> list = new ArrayList<>(10);
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        list.add(new MerchantsData.MerchantsItemData("http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));


        mAdapter.setItems(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.shopping_cart:
                ActManager.toShoppingCart(this);
                break;
        }
    }
}
