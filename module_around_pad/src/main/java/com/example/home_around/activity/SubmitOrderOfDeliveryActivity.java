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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.home_around.adapter.DeliverySelectedGoodsAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.DeliverySelectedGoodsData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.AmountView;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 提交订单
 */
public class SubmitOrderOfDeliveryActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView       mBack;
    private RelativeLayout  mEditAddress;
    private TextView        mName;
    private TextView        mAddress;
    private LinearLayout    mReachTime;
    private TextView        mTimeValue;
    private LinearLayout    mPayType;
    private TextView        mType;
    private TextView        mMerchantName;
    private RecyclerView    mDatas;
    private LinearLayout    mDeliveryPrice;
    private TextView        mPrice;
    private LinearLayout    mInvoiceType;
    private TextView        mSelType;
    private TextView        mBottomPrice;
    private TextView        mPay;
    private DeliverySelectedGoodsAdapter    mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_submit_order_delivery;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mEditAddress = findViewById(R.id.edit_address);
        mName = findViewById(R.id.name);
        mAddress = findViewById(R.id.address);
        mReachTime = findViewById(R.id.reach_time);
        mTimeValue = findViewById(R.id.time_value);
        mPayType = findViewById(R.id.pay_type);
        mType = findViewById(R.id.type);
        mMerchantName = findViewById(R.id.merchant_name);
        mDatas = findViewById(R.id.datas);
        mDeliveryPrice = findViewById(R.id.delivery_price);
        mPrice = findViewById(R.id.price);
        mInvoiceType = findViewById(R.id.invoice_type);
        mSelType = findViewById(R.id.sel_type);
        mBottomPrice = findViewById(R.id.bottom_price);
        mPay = findViewById(R.id.settlement);

        mBack.setOnClickListener(this);
        mEditAddress.setOnClickListener(this);
        mReachTime.setOnClickListener(this);
        mPayType.setOnClickListener(this);
        mDeliveryPrice.setOnClickListener(this);
        mInvoiceType.setOnClickListener(this);
        mPay.setOnClickListener(this);
        mDatas.setFocusable(false);

        mAdapter = new DeliverySelectedGoodsAdapter(this);
        mDatas.setLayoutManager(new LinearLayoutManager(this));
        mDatas.setAdapter(mAdapter);

        initData();
    }

    private void initData() {
        mName.setText("大梨   18018751849");
        mAddress.setText("广东省深圳市南山区科兴科学园");
        mType.setText("支付宝");
        mMerchantName.setText("星巴克旗舰店");
        mPrice.setText("¥0.00");

        List<DeliverySelectedGoodsData> list = new ArrayList<>(3);
        list.add(new DeliverySelectedGoodsData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15.00", 2));
        list.add(new DeliverySelectedGoodsData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15.00", 1));
        list.add(new DeliverySelectedGoodsData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15.00", 2));

        mAdapter.setItems(list);
        SpannableStringBuilder builder = new SpannableStringBuilder().
                append("¥").append(String.format("%.2f", mAdapter.getTotalPrice()), new RelativeSizeSpan(1.5f), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mBottomPrice.setText(builder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.edit_address:
                ActManager.toSelectAddress(this);
                break;
            case R.id.reach_time:
                break;
            case R.id.pay_type:
                selectPayType();
                break;
            case R.id.delivery_price:
                break;
            case R.id.invoice_type:
                break;
            case R.id.settlement:
                ActManager.toPayResult(this, 0);
                break;
        }
    }


    Dialog mDialog;
    private void selectPayType() {
        mDialog = new Dialog(this, R.style.AppNoActionBarWidthFull);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_pay_type_selector);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity =Gravity.BOTTOM;
        window.setAttributes(layoutParams);


        ImageView confirm = mDialog.findViewById(R.id.close);
        LinearLayout zhifubao = mDialog.findViewById(R.id.zhifubao);
        LinearLayout yinlian = mDialog.findViewById(R.id.yinlian);
        LinearLayout weixin = mDialog.findViewById(R.id.weixin);

        confirm.setOnClickListener(clickListener);
        zhifubao.setOnClickListener(clickListener);
        yinlian.setOnClickListener(clickListener);
        weixin.setOnClickListener(clickListener);

        mDialog.show();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDialog.dismiss();
            switch (v.getId()) {
                case R.id.zhifubao:
                    mType.setText("支付宝");
                    ToastUtils.shortToast(mDialog.getContext(), "支付宝");
                    break;
                case R.id.yinlian:
                    mType.setText("云闪付");
                    ToastUtils.shortToast(mDialog.getContext(), "云闪付");
                    break;
                case R.id.weixin:
                    mType.setText("微信支付");
                    ToastUtils.shortToast(mDialog.getContext(), "微信支付");
                    break;
            }
        }
    };
}
