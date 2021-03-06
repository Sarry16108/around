package com.example.home_around.activity;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.adapter.DeliverySelectedGoodsAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.DeliverySelectedGoodsData;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情页
 */
public class OrderDetailActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private ImageView   mStateIcon;
    private LinearLayout    mStateBar;
    private TextView    mStateText;
    private TextView    mExtInfo;
    private TextView    mMechantName;
    private RecyclerView mDatas;
    private LinearLayout    mReachTimeBar;
    private TextView    mPrice;
    private TextView    mPhone;
    private TextView    mName;
    private TextView    mOrderInfo;
    private TextView    mTotalPrice;
    private TextView    mOrderNum;
    private TextView    mCopy;
    private TextView    mSubmitTime;
    private TextView    mPayType;
    private TextView    mBottomPrice;
    private TextView    mCancelOrder;
    private TextView    mRebuy;
    private TextView    mDeleteOrder;
    private TextView    mPayImmediate;
    private DeliverySelectedGoodsAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_detail;
    }


    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mStateIcon = findViewById(R.id.state_icon);
        mStateBar = findViewById(R.id.state_bar);
        mStateText = findViewById(R.id.state_text);
        mExtInfo = findViewById(R.id.ext_info);
        mMechantName = findViewById(R.id.merchant_name);
        mDatas = findViewById(R.id.datas);
        mReachTimeBar = findViewById(R.id.reach_time);
        mPrice = findViewById(R.id.price);
        mPhone = findViewById(R.id.phone);
        mName = findViewById(R.id.name);
        mOrderInfo = findViewById(R.id.order_info);
        mTotalPrice = findViewById(R.id.total_price);
        mOrderNum = findViewById(R.id.order_num);
        mCopy = findViewById(R.id.copy);
        mSubmitTime = findViewById(R.id.submit_time);
        mPayType = findViewById(R.id.pay_type);
        mBottomPrice = findViewById(R.id.bottom_price);
        mCancelOrder = findViewById(R.id.cancel_order);
        mRebuy = findViewById(R.id.rebuy);
        mDeleteOrder = findViewById(R.id.delete_order);
        mPayImmediate = findViewById(R.id.pay_immediate);


        mBack.setOnClickListener(this);
        mCopy.setOnClickListener(this);
        mCancelOrder.setOnClickListener(this);
        mRebuy.setOnClickListener(this);
        mDeleteOrder.setOnClickListener(this);
        mPayImmediate.setOnClickListener(this);

        mAdapter = new DeliverySelectedGoodsAdapter(this);
        mDatas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDatas.setAdapter(mAdapter);

        initData();
    }

    private void initData() {
        mMechantName.setText("什么店铺啊");
        String prePrice = "100.00";
        SpannableStringBuilder builder2 = new SpannableStringBuilder().
                append("¥").append(prePrice, new RelativeSizeSpan(1.5f), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mBottomPrice.setText(builder2);
        mPayImmediate.setSelected(true);

        List<DeliverySelectedGoodsData> list = new ArrayList<>(3);
        list.add(new DeliverySelectedGoodsData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", 2));
        list.add(new DeliverySelectedGoodsData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", 1));
        list.add(new DeliverySelectedGoodsData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", 2));

        mAdapter.setItems(list);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.copy:
                break;
            case R.id.cancel_order:
                goodsTypesSelect();
                break;
            case R.id.rebuy:
                break;
            case R.id.delete_order:
                break;
            case R.id.pay_immediate:
                break;
        }
    }


    private TextView check1, check2, check3;
    private Dialog mDialog;
    private void goodsTypesSelect() {
        mDialog = new Dialog(this, R.style.AppNoActionBarWidthFull);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_order_cancel);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity =Gravity.BOTTOM;
        window.setAttributes(layoutParams);


        mDialog.findViewById(R.id.close).setOnClickListener(onClickListener);
        mDialog.findViewById(R.id.settlement).setOnClickListener(onClickListener);
        mDialog.findViewById(R.id.item1).setOnClickListener(onClickListener);
        mDialog.findViewById(R.id.item2).setOnClickListener(onClickListener);
        mDialog.findViewById(R.id.item3).setOnClickListener(onClickListener);
        check1 = mDialog.findViewById(R.id.check1);
        check2 = mDialog.findViewById(R.id.check2);
        check3 = mDialog.findViewById(R.id.check3);

        mDialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item1:
                case R.id.item2:
                case R.id.item3:
                    check1.setSelected(R.id.item1 == v.getId());
                    check2.setSelected(R.id.item2 == v.getId());
                    check3.setSelected(R.id.item3 == v.getId());
                    break;
                case R.id.close:
                    mDialog.dismiss();
                    break;
                case R.id.settlement:
                    mDialog.dismiss();
                    break;
            }
        }
    };
}
