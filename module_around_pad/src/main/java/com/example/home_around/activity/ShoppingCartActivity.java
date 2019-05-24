package com.example.home_around.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.home_around.adapter.ShoppingCartAdapter;
import com.example.home_around.base.BaseTitleActivity;
import com.example.home_around.entity.ShoppingBaseItemData;
import com.example.home_around.entity.ShoppingCartData;
import com.example.home_around.entity.ShoppingCartDividerTagData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnItemClickListener;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 */
public class ShoppingCartActivity extends BaseTitleActivity implements View.OnClickListener {

    private RecyclerView    mDataList;
    private TextView        mCheck;
    private TextView        mSelectAll;
    private TextView        mPrice;
    private TextView        mSettlement;
    private ShoppingCartAdapter mShoppingAdapter;

    private TextView        mCheckAll;
    private TextView        mCheckAllText;
    private TextView        mPayList;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        setTitle("购物车");

        mDataList = findViewById(R.id.data_list);
        mCheck = findViewById(R.id.check);
        mSelectAll = findViewById(R.id.select_all);
        mPrice = findViewById(R.id.price);
        mSettlement = findViewById(R.id.settlement);

        mDataList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mShoppingAdapter = new ShoppingCartAdapter(this);
        mShoppingAdapter.setPriceUpdateView(mPrice);
        mDataList.setAdapter(mShoppingAdapter);
        mShoppingAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                ActManager.toGoodsDetailOfService(ShoppingCartActivity.this, 0);
            }
        });

        mBack.setOnClickListener(this);
        mCheck.setOnClickListener(this);
        mSelectAll.setOnClickListener(this);

        initData();
    }


    private void initData() {
        List<ShoppingBaseItemData> items = new ArrayList<>(10);

        items.add(new ShoppingCartDividerTagData("耐克专卖店"));
        items.add(new ShoppingCartData.ShoppingCartItem(1, "走啊走", "M", "黑色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg"));
        items.add(new ShoppingCartData.ShoppingCartItem(2, "走啊走", "S", "红色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg"));
        items.add(new ShoppingCartData.ShoppingCartItem(3, "别走啊", "L", "蓝色色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg"));
        items.add(new ShoppingCartData.ShoppingCartItem(4, "晚一会", "XXL", "黑色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg"));

        items.add(new ShoppingCartDividerTagData("精品专卖店"));
        items.add(new ShoppingCartData.ShoppingCartItem(5, "撒由", "M", "黑色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-28/2018-12-28-b2bb4bea-4d1a-4cb2-a38a-72976ea48351.jpg"));
        items.add(new ShoppingCartData.ShoppingCartItem(6, "哪啦", "S", "红色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-28/2018-12-28-b2bb4bea-4d1a-4cb2-a38a-72976ea48351.jpg"));
        items.add(new ShoppingCartData.ShoppingCartItem(7, "霓虹金", "L", "蓝色色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-28/2018-12-28-b2bb4bea-4d1a-4cb2-a38a-72976ea48351.jpg"));
        items.add(new ShoppingCartData.ShoppingCartItem(8, "瓦塔西瓦", "XXL", "黑色", 37.00f, 2, 100, "http://192.168.1.23/resource-file/2018-12-28/2018-12-28-b2bb4bea-4d1a-4cb2-a38a-72976ea48351.jpg"));

        mShoppingAdapter.setItems(items);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.check:
            case R.id.select_all:
                selectAll();
                break;
            case R.id.settlement:
                ActManager.toSubmitOrderOfAppoint(this, 0);
                break;
        }
    }

    private void selectAll() {
        mCheck.setSelected(!mCheck.isSelected());

        if (mCheck.isSelected()) {
            mSelectAll.setText("取消全选");
            mShoppingAdapter.selectAll(true);
        } else {
            mSelectAll.setText("全选");
            mShoppingAdapter.selectAll(false);
        }
    }
}
