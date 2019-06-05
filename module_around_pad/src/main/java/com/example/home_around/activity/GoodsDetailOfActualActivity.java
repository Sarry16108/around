package com.example.home_around.activity;

import android.support.v4.app.FragmentManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.adapter.FragmentViewPagerAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.fragments.GoodsEvaluationFragment;
import com.example.home_around.fragments.GoodsSelectFragment;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

/**
 * 实物类商品信息详情
 */
public class GoodsDetailOfActualActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private ImageView   mImgBg;
    private TextView    mTitle;
    private TextView    mExtInfo;
    private TextView    mPrice;
    private TextView    mAdd;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_goods_detail_actual;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mImgBg = findViewById(R.id.img_bg);
        mTitle = findViewById(R.id.title);
        mExtInfo = findViewById(R.id.ext_info);
        mPrice = findViewById(R.id.price);
        mAdd = findViewById(R.id.add);

        mBack.setOnClickListener(this);
        mAdd.setOnClickListener(this);

        initData();
    }

    private void initData() {
        GlideUtils.showImage(mImgBg, "http://192.168.1.23/resource-file/2019-06-04/dianpuye/3/Bitmap.png");
        mTitle.setText("星巴克旗舰店（虹梅路店）");
        mExtInfo.setText("商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。");
        RelativeSizeSpan spanBig = new RelativeSizeSpan(1.5f);
        SpannableStringBuilder builder = new SpannableStringBuilder().
                append("¥").append("109.00", spanBig, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mPrice.setText(builder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.add:
                ToastUtils.shortToast(this, "加入购物车");
                break;
        }
    }
}
