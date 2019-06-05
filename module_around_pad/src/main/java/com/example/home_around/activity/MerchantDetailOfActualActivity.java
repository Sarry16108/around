package com.example.home_around.activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.adapter.FragmentViewPagerAdapter;
import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.fragments.GoodsEvaluationFragment;
import com.example.home_around.fragments.GoodsSelectFragment;
import com.example.home_around.utils.ActManager;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

/**
 * 实物类商家介绍
 */
public class MerchantDetailOfActualActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView mBack;
    private ImageView   mImgBg;
    private ImageView   mLogo;
    private TextView mTitle;
    private TextView    mExtInfo;
    private ImageView   mUnfold;
    private ViewPager mContent;
    private TextView    mTypeGoods;
    private TextView    mTypeEvaluation;
    private String      mText;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_merchant_detail_actual;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mImgBg = findViewById(R.id.img_bg);
        mLogo = findViewById(R.id.logo);
        mTitle = findViewById(R.id.title);
        mExtInfo = findViewById(R.id.ext_info);
        mUnfold = findViewById(R.id.unfold);
        mContent = findViewById(R.id.content);
        mTypeGoods = findViewById(R.id.type_goods);
        mTypeEvaluation = findViewById(R.id.type_evaluation);

        mTypeGoods.setOnClickListener(this);
        mTypeEvaluation.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mUnfold.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(fm);
        adapter.addFragment(new GoodsSelectFragment());
        adapter.addFragment(new GoodsEvaluationFragment());

        mContent.setAdapter(adapter);
        mContent.setCurrentItem(0);
        mTypeEvaluation.setBackgroundResource(0);
        mTypeGoods.setSelected(true);
        mContent.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                mTypeGoods.setSelected(0 == position);
                mTypeEvaluation.setSelected(1 == position);
            }
        });


        initData();
    }

    private void initData() {
        GlideUtils.showImage(mImgBg, "http://192.168.1.23/resource-file/2019-06-04/dianpuye/1/Bitmap.png");
        GlideUtils.showCenterCrop(mLogo, "http://192.168.1.23/resource-file/2019-06-04/dianpuye/1/Bitmap Copy.png");
        mTitle.setText("星巴克旗舰店（虹梅路店）");
        mText = "商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。商家介绍：目前，星巴克已经在中国150多个城市开设了超过3,600家门店，拥有近50,000名星巴克伙伴。这一独特优势使我们能够在每一天，通过每一家星巴克门店，践行我们的承诺。";
        mExtInfo.setText(mText);
        unfoldExtInfo(false);


    }

    /**
     *
     * @param unfold    是否展开
     */
    private void unfoldExtInfo(boolean unfold) {
        mUnfold.setSelected(unfold);
        if (!unfold) {
            mExtInfo.setMaxLines(3);
            mUnfold.setRotation(0);
        } else {
            mExtInfo.setMaxLines(20);
            mUnfold.setRotation(180);
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.type_goods:
                mContent.setCurrentItem(0);
                break;
            case R.id.type_evaluation:
                mContent.setCurrentItem(1);
                break;
            case R.id.unfold:
                unfoldExtInfo(!mUnfold.isSelected());
                break;
        }
    }
}
