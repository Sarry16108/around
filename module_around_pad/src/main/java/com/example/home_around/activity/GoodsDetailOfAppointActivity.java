package com.example.home_around.activity;

import android.app.Dialog;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.adapter.WeekdaySelectorAdapter;
import com.example.home_around.adapter.WeekdaySelectorTimeAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.entity.WeekDayData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.flowlayout.FlowLayout;
import com.example.home_around.widget.flowlayout.TagAdapter;
import com.example.home_around.widget.flowlayout.TagFlowLayout;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 预定类商品信息详情
 */
public class GoodsDetailOfAppointActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView mBg;
    private ImageView   mBack;
    private TextView mTitle;
    private TextView    mExtInfo;
    private TagFlowLayout mTags;
    private LinearLayout mAddressBar;
    private TextView    mAddress;
    private LinearLayout    mDiscountBar;
    private LinearLayout    mAnnounceBar;
    private LinearLayout    mNoteBar;
    private TextView    mPrice;
    private TextView    mPrePrice;
    private TextView    mSettlement;
    private TextView    mSelectedDaytime;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_goods_detail_appoint;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBg = findViewById(R.id.img_bg);
        mBack = findViewById(R.id.back);
        mTitle = findViewById(R.id.title);
        mExtInfo = findViewById(R.id.ext_info);
        mTags = findViewById(R.id.tags);
        mAddressBar = findViewById(R.id.address_bar);
        mAddress = findViewById(R.id.address);
        mDiscountBar = findViewById(R.id.discount_bar);
        mAnnounceBar = findViewById(R.id.announce_bar);
        mNoteBar = findViewById(R.id.note_bar);
        mPrice = findViewById(R.id.price);
        mPrePrice = findViewById(R.id.pre_price);
        mSettlement = findViewById(R.id.settlement);
        mSelectedDaytime = findViewById(R.id.selected_daytime);


        mBack.setOnClickListener(this);
        mAddressBar.setOnClickListener(this);
        mDiscountBar.setOnClickListener(this);
        mNoteBar.setOnClickListener(this);
        mAnnounceBar.setOnClickListener(this);
        mSettlement.setOnClickListener(this);

        initData();
    }


    private void initData() {

        GlideUtils.showImage(mBg, "http://192.168.1.23/resource-file/2019-06-04/dianpuye/3/Bitmap.png");
        mTitle.setText("星巴克旗舰店（虹梅路店）");
        mExtInfo.setText("商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。");

        String price = "109.00";
        SpannableStringBuilder builder = new SpannableStringBuilder().
                append("¥").append(price, new RelativeSizeSpan(1.5f), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mPrice.setText(builder);

        String prePrice = "100.00";
        SpannableStringBuilder builder2 = new SpannableStringBuilder().
                append("¥").append(prePrice, new RelativeSizeSpan(1.5f), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mPrePrice.setText(builder2);
        mPrePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        mAddress.setText("地址：吴中路1588号（爱琴海购物公园购物中心7F-706）");


        List<String> tags = new ArrayList<>(5);
        tags.add("随时退");
        tags.add("过期自动退");

        TagAdapter mAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(GoodsDetailOfAppointActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_tag_view, mTags, false);
                tv.setText(s);
                return tv;

            }

        };
        mTags.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.address_bar:
                break;
            case R.id.discount_bar:
                selectDayAndTime();
                break;
            case R.id.note_bar:
                break;
            case R.id.announce_bar:
                break;
            case R.id.settlement:
                ActManager.toSubmitOrderOfAppoint(this, 0);
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
                mSelectedDaytime.setText(text);
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
}
