package com.example.home_around.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.home_around.widget.flowlayout.TagFlowLayout;
import com.example.lib_generic.utils.GlideUtils;
import com.example.home_around.adapter.ContentAdapter;
import com.example.home_around.entity.DividerTagData;
import com.example.home_around.entity.HomeBaseItemData;
import com.example.home_around.entity.RecommendData;
import com.example.home_around.entity.BrandSupplyData;
import com.example.home_around.entity.CategoryData;
import com.example.home_around.utils.ActManager;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.widgets.SearchView;
import com.example.home_around.widget.HomeItemDecoration;
import com.example.home_around.widget.OnItemClickListener;
import com.example.module_around.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SfyBaseActivity implements View.OnClickListener, OnItemClickListener {

    private Banner mBanner;

    private ImageView   mBack;
    private SearchView  mSearchView;
    private ImageView   mShoppingCart;

    private RecyclerView        mContent;
    private ContentAdapter      mContentAdapter;
    private final int TOTAL_SPAN = 12;

    @Override
    protected void initCommon() {
        super.initCommon();
        setContentView(R.layout.activity_main);

        mBanner = findViewById(R.id.ad_banner);
        mSearchView = findViewById(R.id.search_bar);
        mShoppingCart = findViewById(R.id.shopping_cart);
        mBack = findViewById(R.id.back);

        mContent = findViewById(R.id.content);

        mBack.setOnClickListener(this);
        mSearchView.setOnClickListener(this);
        mShoppingCart.setOnClickListener(this);


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
                ActManager.toWeb(MainActivity.this, "http://www.baidu.com");
            }
        });


        mContentAdapter = new ContentAdapter(this);
        mContentAdapter.setOnItemClickListener(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, TOTAL_SPAN);
        ((GridLayoutManager) layoutManager).setSpanSizeLookup(mContentAdapter.getSpanSizeLookup());
        mContent.setLayoutManager(layoutManager);
        mContent.setAdapter(mContentAdapter);

//        mContent.addItemDecoration(new HomeItemDecoration(TagFlowLayout.dip2px(this, 30), TagFlowLayout.dip2px(this, 30)));

        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.back:
                this.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.search_bar:
                ActManager.toSearch(this);
                break;
            case R.id.shopping_cart:
                ActManager.toShoppingCart(this);
                break;
        }
    }

    private void initData() {

        //todo：
        List<String> homeAds = new ArrayList<>(5);
        homeAds.add("http://192.168.1.23/resource-file/2019-06-04/banner/2.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-06-04/banner/3.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-06-04/banner/5.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-06-04/banner/Bitmap.png");
        homeAds.add("http://192.168.1.23/resource-file/2019-06-04/banner/tupian3.png");

        mBanner.update(homeAds);

        List<HomeBaseItemData> kindData = new ArrayList<>(6);
        kindData.add(new CategoryData("http://192.168.1.23/resource-file/2019-06-04/liugebankuai/chaishishengxian.png", "美食外卖", TOTAL_SPAN/6));
        kindData.add(new CategoryData("http://192.168.1.23/resource-file/2019-06-04/liugebankuai/jiajuzhuangxiu.png", "美食外卖", TOTAL_SPAN/6));
        kindData.add(new CategoryData("http://192.168.1.23/resource-file/2019-06-04/liugebankuai/meishiwaimai.png", "美食外卖", TOTAL_SPAN/6));
        kindData.add(new CategoryData("http://192.168.1.23/resource-file/2019-06-04/liugebankuai/shenghuofuwu.png", "美食外卖", TOTAL_SPAN/6));
        kindData.add(new CategoryData("http://192.168.1.23/resource-file/2019-06-04/liugebankuai/yiliaojiankang.png", "美食外卖", TOTAL_SPAN/6));
        kindData.add(new CategoryData("http://192.168.1.23/resource-file/2019-06-04/liugebankuai/youercaiyi.png", "美食外卖", TOTAL_SPAN/6));
        mContentAdapter.addItems(kindData);

        mContentAdapter.addItem(new DividerTagData("品牌商家直供", TOTAL_SPAN, 0));

        List<HomeBaseItemData> supplyTypeData = new ArrayList<>(6);
        supplyTypeData.add(new BrandSupplyData("http://192.168.1.23/resource-file/2019-06-04/shangjiazhigong/pic1.png", TOTAL_SPAN/2));
        supplyTypeData.add(new BrandSupplyData("http://192.168.1.23/resource-file/2019-06-04/shangjiazhigong/pic1 copy.png", TOTAL_SPAN/2));
        supplyTypeData.add(new BrandSupplyData("http://192.168.1.23/resource-file/2019-06-04/shangjiazhigong/pic1 copy 3.png", TOTAL_SPAN/2));
        supplyTypeData.add(new BrandSupplyData("http://192.168.1.23/resource-file/2019-06-04/shangjiazhigong/pic1 copy 2.png", TOTAL_SPAN/2));
        mContentAdapter.addItems(supplyTypeData);

        mContentAdapter.addItem(new DividerTagData("甄选推荐", TOTAL_SPAN, 1));

        List<HomeBaseItemData> recommendData = new ArrayList<>(6);
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 2.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 3.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 4.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 2.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 3.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 4.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 2.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 3.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 4.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai1 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 2.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 3.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 4.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));
        recommendData.add(new RecommendData("http://192.168.1.23/resource-file/2019-06-04/zhenxuantuijian/cai2 copy 5.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", TOTAL_SPAN/4));

        mContentAdapter.addItems(recommendData);
    }

    @Override
    public void OnClick(View view, int pos) {
        LogUtils.d("pos：" + pos);
        HomeBaseItemData itemData = mContentAdapter.getItem(pos);

        switch (itemData.getType()) {
            case HomeBaseItemData.TYPE_CATEGORY:
                CategoryData categoryData = (CategoryData) itemData;
                if (pos == 0) {
                    ActManager.toMerchants(this, categoryData.getId());
                } else if (pos == 1) {
                    ActManager.toSubmitOrderOfDelivery(this);
                } else if (pos == 2) {
                    ActManager.toMyOrder(this, 0);
                }
                break;
            case HomeBaseItemData.TYPE_BRAND_SUPPLY:
                BrandSupplyData brandSupplyData = (BrandSupplyData) itemData;
                ActManager.toMerchantDetailOfService(this, 0);
                break;
            case HomeBaseItemData.TYPE_RECOMMEND:
                RecommendData recommendData = (RecommendData) itemData;
                ActManager.toGoodsDetailOfAppoint(this, recommendData.getId());
                break;
        }
    }

    @Override
    public void OnClick(View preView, View view, int pos) {

    }
}
