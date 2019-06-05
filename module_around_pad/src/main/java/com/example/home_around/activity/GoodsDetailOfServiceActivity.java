package com.example.home_around.activity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.flowlayout.FlowLayout;
import com.example.home_around.widget.flowlayout.TagAdapter;
import com.example.home_around.widget.flowlayout.TagFlowLayout;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务类商品信息详情
 */
public class GoodsDetailOfServiceActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBg;
    private ImageView   mBack;
    private TextView    mTitle;
    private TextView    mExtInfo;
    private TagFlowLayout   mTags;
    private LinearLayout    mAddressBar;
    private TextView    mAddress;
    private LinearLayout    mDiscountBar;
    private TextView    mDiscount;
    private LinearLayout    mAnnounceBar;
    private TextView    mAnnounce;
    private LinearLayout    mNoteBar;
    private TextView    mNote;
    private TextView    mPrice;
    private TextView    mPrePrice;
    private TextView    mSettlement;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_goods_detail_service;
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
        mDiscount = findViewById(R.id.discount);
        mAnnounceBar = findViewById(R.id.announce_bar);
        mAnnounce = findViewById(R.id.announce);
        mNoteBar = findViewById(R.id.note_bar);
        mNote = findViewById(R.id.note);
        mPrice = findViewById(R.id.price);
        mPrePrice = findViewById(R.id.pre_price);
        mSettlement = findViewById(R.id.settlement);


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
        mDiscount.setText("优惠详情");
        mAnnounce.setText("公告说明");
        mNote.setText("购买须知");

        List<String> tags = new ArrayList<>(5);
        tags.add("随时退");
        tags.add("过期自动退");

        TagAdapter mAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(GoodsDetailOfServiceActivity.this);
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
                break;
            case R.id.note_bar:
                break;
            case R.id.announce_bar:
                break;
            case R.id.settlement:
                ActManager.toSubmitOrderOfService(this, 0);
                break;
        }
    }
}
