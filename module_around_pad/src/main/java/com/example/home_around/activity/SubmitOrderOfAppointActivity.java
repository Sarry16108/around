package com.example.home_around.activity;

import android.app.Dialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.adapter.WeekdaySelectorAdapter;
import com.example.home_around.adapter.WeekdaySelectorTimeAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.WeekDayData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.AmountView;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 提交订单
 */
public class SubmitOrderOfAppointActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private TextView    mGoodsTitle;
    private TextView    mPrice;
    private TextView    mAddress;
    private AmountView  mAmountView;
    private TextView    mDayTime;
    private TextView    mEditTime;
    private TextView    mPhone;
    private TextView    mMister;
    private TextView    mMiss;
    private EditText    mExtInfo;
    private TextView    mExtInfoSize;
    private TextView    mRefundTip;

    private TextView    mBottomPrice;
    private TextView    mCountText;
    private TextView    mOk;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_submit_order_appoint;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mGoodsTitle = findViewById(R.id.goods_title);
        mPrice = findViewById(R.id.price);
        mAddress = findViewById(R.id.address);
        mAmountView = findViewById(R.id.amount_view);
        mDayTime = findViewById(R.id.total_price);
        mEditTime = findViewById(R.id.edit_time);
        mPhone = findViewById(R.id.phone);
        mMister = findViewById(R.id.mister);
        mMiss = findViewById(R.id.miss);
        mExtInfo = findViewById(R.id.ext_info);
        mExtInfoSize = findViewById(R.id.ext_info_size);
        mRefundTip = findViewById(R.id.refund_tip);
        mOk = findViewById(R.id.settlement);
        mBottomPrice = findViewById(R.id.bottom_price);
        mCountText = findViewById(R.id.count_text);

        mBack.setOnClickListener(this);
        mEditTime.setOnClickListener(this);
        mMister.setOnClickListener(this);
        mMiss.setOnClickListener(this);
        mOk.setOnClickListener(this);

        initData();
    }

    private void initData() {
        mGoodsTitle.setText("单人舒适精细洁牙套餐");
        mMister.setSelected(true);
        mAddress.setText("科瓦齿科（金茂大厦店）");
        mDayTime.setText("4月10日 10:00");
        mPhone.setText("139****3405");
        mRefundTip.setText("4月10日（周三）10:00前可随时退，逾期需联系商家退款订单保留至4月10日（周三）10:00");

        int count = 1;
        final float price = 299.00f;
        mPrice.setText("¥" + price);
        mBottomPrice.setText("¥" + count * price);
        mCountText.setText("共" + count + "份");
        mAmountView.setGoodsStorage(100);
        mAmountView.setCount(1);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {

                mBottomPrice.setText("¥" + amount * price);
                mCountText.setText("共" + amount + "份");
            }
        });

        mExtInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mExtInfoSize.setText(s.toString().length() + "/20");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.edit_time:
                selectDayAndTime();
                break;
            case R.id.mister:
                mMister.setSelected(true);
                mMiss.setSelected(false);
                break;
            case R.id.miss:
                mMister.setSelected(false);
                mMiss.setSelected(true);
                break;
            case R.id.settlement:
                selectPayType();
                break;
        }
    }



    private void selectDayAndTime() {
        final Dialog dialog = new Dialog(this, R.style.AppNoActionBarWidthFull);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_next_week_selector);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity =Gravity.BOTTOM;
        window.setAttributes(layoutParams);

        List<WeekDayData> weekDays = getWeekdayDatas();
        final WeekdaySelectorAdapter weekdaySelectorAdapter = new WeekdaySelectorAdapter(this);
        weekdaySelectorAdapter.setItems(weekDays);

        List<String> times = getAppointedTimes();
        final WeekdaySelectorTimeAdapter weekdaySelectorTimeAdapter = new WeekdaySelectorTimeAdapter(this);
        weekdaySelectorTimeAdapter.setItems(times);

        RecyclerView daySelect = dialog.findViewById(R.id.day_select);
        RecyclerView timeSelect = dialog.findViewById(R.id.time_select);
        RecyclerView okBtn = dialog.findViewById(R.id.ok);

        GridLayoutManager dayManager = new GridLayoutManager(this, 7);
        daySelect.setLayoutManager(dayManager);
        daySelect.setAdapter(weekdaySelectorAdapter);

        GridLayoutManager timeManager = new GridLayoutManager(this, 8);
        timeSelect.setLayoutManager(timeManager);
        timeSelect.setAdapter(weekdaySelectorTimeAdapter);

        TextView confirm = dialog.findViewById(R.id.ok);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (weekdaySelectorAdapter.getSelectedData() == null || weekdaySelectorTimeAdapter.getSelectedTime() == null) {
                    return;
                }

                String text = "星期" + weekdaySelectorAdapter.getSelectedData().getWeekDay()  + " "
                        + weekdaySelectorAdapter.getSelectedData().getMonth() + "月" + weekdaySelectorAdapter.getSelectedData().getDay() + "日 "
                        + weekdaySelectorTimeAdapter.getSelectedTime();
                mDayTime.setText(text);
            }
        });

        dialog.show();
    }

    private List<WeekDayData> getWeekdayDatas() {
        List<WeekDayData> weekDayData = new ArrayList<>(7);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayStart = calendar.get(Calendar.DAY_OF_YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        weekDayData.add(new WeekDayData(week, String.valueOf(day), String.valueOf(month)));

        for (int i = 0; i < 7; ++i) {
            calendar.set(Calendar.DAY_OF_YEAR, ++dayStart);
            week = calendar.get(Calendar.DAY_OF_WEEK);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            weekDayData.add(new WeekDayData(week, String.valueOf(day), String.valueOf(month)));
        }

        return weekDayData;
    }

    private List<String> getAppointedTimes() {
        List<String> times = new ArrayList<>(8);
        times.add("10:00");
        times.add("11:00");
        times.add("12:00");
        times.add("13:00");
        times.add("14:00");
        times.add("15:00");
        times.add("16:00");
        times.add("17:00");
        return times;
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
                    ActManager.toPayResult(SubmitOrderOfAppointActivity.this, 0);
                    break;
                case R.id.yinlian:
                    ToastUtils.shortToast(mDialog.getContext(), "银联");
                    ActManager.toPayResult(SubmitOrderOfAppointActivity.this, 0);
                    break;
                case R.id.weixin:
                    ToastUtils.shortToast(mDialog.getContext(), "微信支付");
                    ActManager.toPayResult(SubmitOrderOfAppointActivity.this, 0);
                    break;
            }
        }
    };
}
