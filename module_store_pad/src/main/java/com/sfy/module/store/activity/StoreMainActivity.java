package com.sfy.module.store.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.GlideUtils;
import com.sfy.module.store.R;
import com.sfy.module.store.adapter.HomeCategoryAdapter;
import com.sfy.module.store.adapter.HomeRecommendAdapter;
import com.sfy.module.store.entity.HomeCategoryData;
import com.sfy.module.store.entity.HomeRecommendData;
import com.sfy.module.store.utils.ActManager;
import com.sfy.module.store.widget.OnSimpleItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class StoreMainActivity extends SfyBaseActivity implements View.OnClickListener {

    private ImageView   mUser;
    private LinearLayout mRecommend;
    private LinearLayout   mCategory;
    private LinearLayout   mExplore;
    private LinearLayout   mCart;
    private Banner mBanner;
    private RecyclerView   mHGrid;
    private RecyclerView   mVGrid;


    private HomeCategoryAdapter mCategoryAdapter;
    private HomeRecommendAdapter mRecommendAdapter;


    @Override
    protected void initCommon() {
        super.initCommon();
        setContentView(R.layout.activity_main);

        mUser = findViewById(R.id.user);
        mRecommend = findViewById(R.id.recommend);
        mCategory = findViewById(R.id.category);
        mExplore = findViewById(R.id.explore);
        mCart = findViewById(R.id.cart);
        mBanner = findViewById(R.id.ad_banner);
        mHGrid = findViewById(R.id.rec_category);
        mVGrid = findViewById(R.id.rec_grid);


        mUser.setOnClickListener(this);
        mRecommend.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mExplore.setOnClickListener(this);
        mCart.setOnClickListener(this);


        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object obj, ImageView imageView) {
                GlideUtils.showImage(imageView, (String) obj);
            }
        });
        mBanner.isAutoPlay(true);
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ActManager.toWeb(StoreMainActivity.this, "http://www.baidu.com");
            }
        });

        initData();
    }

    private void initData() {
        List<String> homeAds = new ArrayList<>(5);
        homeAds.add("http://192.168.1.23/resource-file/2019-05-29/01.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-05-29/02.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-05-29/03.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-05-29/04.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-05-29/05.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-05-29/06.png");

        mBanner.update(homeAds);

        List<HomeCategoryData> list = new ArrayList<>(5);
        list.add(new HomeCategoryData("http://192.168.1.23/resource-file/2019-05-29/BANNER-1.png"));
        list.add(new HomeCategoryData("http://192.168.1.23/resource-file/2019-05-29/BANNER-2.png"));
        list.add(new HomeCategoryData("http://192.168.1.23/resource-file/2019-05-29/BANNER-3.png"));
        list.add(new HomeCategoryData("http://192.168.1.23/resource-file/2019-05-29/BANNER-1.png"));
        list.add(new HomeCategoryData("http://192.168.1.23/resource-file/2019-05-29/BANNER-2.png"));
        list.add(new HomeCategoryData("http://192.168.1.23/resource-file/2019-05-29/BANNER-3.png"));
        mCategoryAdapter = new HomeCategoryAdapter(this);

        mHGrid.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mHGrid.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setItems(list);

        List<HomeRecommendData> list2 = new ArrayList<>(5);
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-1.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-2.png", "名字是啥名字是啥名字是啥名字是啥名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-3.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-4.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-5.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-6.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-7.png", "名字是啥名字是啥名字是啥名字是啥名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-8.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-9.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-10.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-1.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-2.png", "名字是啥名字是啥名字是啥名字是啥名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-3.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-4.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-5.png", "名字是啥", "¥1599.00"));
        list2.add(new HomeRecommendData("http://192.168.1.23/resource-file/2019-05-29/sp-6.png", "名字是啥", "¥1599.00"));

        mRecommendAdapter = new HomeRecommendAdapter(this);
        mVGrid.setLayoutManager(new GridLayoutManager(this, 4));
        mVGrid.setAdapter(mRecommendAdapter);
        mRecommendAdapter.setItems(list2);
        mRecommendAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                super.OnClick(view, pos);
                ActManager.toDetail(StoreMainActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
