package com.example.home_around.activity;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.AmountView;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

/**
 * 提交订单
 */
public class SubmitOrderOfServiceActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private TextView    mGoodsTitle;
    private AmountView  mAmountView;
    private TextView    mPrice;
    private TextView    mTotalPrice;
    private TextView    mPhone;
    private TextView    mPay;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_submit_order_service;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mGoodsTitle = findViewById(R.id.goods_title);
        mAmountView = findViewById(R.id.amount_view);
        mPrice = findViewById(R.id.price);
        mTotalPrice = findViewById(R.id.total_price);
        mPhone = findViewById(R.id.phone);
        mPay = findViewById(R.id.ok);

        mBack.setOnClickListener(this);
        mPay.setOnClickListener(this);

        initData();
    }

    private void initData() {
        mGoodsTitle.setText("美食趴入场券+饮品兑换券（平日，不含游乐园门票）");
        final float price = 299.00f;
        final int count = 2;
        float value = price * count;

        mPrice.setText("" + value);
        mTotalPrice.setText("" + value);
        mAmountView.setGoodsStorage(100);
        mAmountView.setCount(count);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                float value = price * amount;
                mPrice.setText("" + value);
                mTotalPrice.setText("" + value);
            }
        });
        mPhone.setText("139****3405");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.ok:
                selectPayType();
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
                    ToastUtils.shortToast(mDialog.getContext(), "支付宝");
                    ActManager.toPayResult(SubmitOrderOfServiceActivity.this, 0);
                    break;
                case R.id.yinlian:
                    ToastUtils.shortToast(mDialog.getContext(), "银联");
                    ActManager.toPayResult(SubmitOrderOfServiceActivity.this, 0);
                    break;
                case R.id.weixin:
                    ToastUtils.shortToast(mDialog.getContext(), "微信支付");
                    ActManager.toPayResult(SubmitOrderOfServiceActivity.this, 0);
                    break;
            }
        }
    };
}
