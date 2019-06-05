package com.sfy.module.store.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.AndroidUtils;
import com.example.lib_generic.utils.DeviceUtils;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.sfy.module.store.R;
import com.sfy.module.store.adapter.DetailAddressAdapter;
import com.sfy.module.store.adapter.DetailIntroductionPicsAdapter;
import com.sfy.module.store.adapter.DetailParamsAdapter;
import com.sfy.module.store.adapter.DetailPicsAdapter;
import com.sfy.module.store.adapter.DetailSpecificationAdapter;
import com.sfy.module.store.adapter.HomeCategoryAdapter;
import com.sfy.module.store.adapter.HomeRecommendAdapter;
import com.sfy.module.store.entity.DetailParamsData;
import com.sfy.module.store.entity.DetailSpecificationData;
import com.sfy.module.store.entity.HomeCategoryData;
import com.sfy.module.store.entity.HomeRecommendData;
import com.sfy.module.store.utils.ActManager;
import com.sfy.module.store.widget.UpDownRefreshView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class DetailActivity extends SfyBaseActivity implements View.OnClickListener {


    private ImageView mBack;
    private ImageView mMore;
    private TextView mIntroduction;
    private TextView mDetail;
    private TextView mEvaluation;
    private UpDownRefreshView mIntroductionView;
    private RelativeLayout mDetailView;
    private ScrollView  mEvaluationView;
    private RecyclerView mGoodsPics;
    private TextView mTitle;
    private TextView mPrice;
    private ImageView mSpecificationSel;
    private ImageView mAddressSel;
    private ImageView mShopImg;
    private TextView mShopTitle;
    private TextView mSubscriber;
    private TextView mCount;
    private TextView mGoodsStar;
    private TextView mServiceStar;
    private TextView mDeliveryStar;

    private LinearLayout mCustomerService;
    private LinearLayout mExplore;
    private LinearLayout mCart;
    private TextView mAddCart;
    private TextView mBuyImmediate;
    private TextView mPullToSee;
    private TextView    mAddressInfo;

    private TextView mDetailTagPics;
    private TextView mDetailTagParams;
    private RecyclerView mDetailPics;
    private RecyclerView mDetailParams;

    DetailPicsAdapter mDetailPicsAdapter;
    DetailIntroductionPicsAdapter mDetailIntroPicsAdapter;
    DetailParamsAdapter mDetailParamsAdapter;

    @Override
    protected void initCommon() {
        super.initCommon();
        setContentView(R.layout.activity_detail);

        mBack = findViewById(R.id.back);
        mIntroduction = findViewById(R.id.introduction);
        mDetail = findViewById(R.id.detail);
        mEvaluation = findViewById(R.id.evaluation);
        mMore = findViewById(R.id.more);
        mGoodsPics = findViewById(R.id.pics);
        mTitle = findViewById(R.id.title);
        mPrice = findViewById(R.id.price);
        mShopImg = findViewById(R.id.shop_img);
        mShopTitle = findViewById(R.id.shop_title);
        mSubscriber = findViewById(R.id.subscriber);
        mCount = findViewById(R.id.count);
        mGoodsStar = findViewById(R.id.goods_star);
        mServiceStar = findViewById(R.id.service_star);
        mDeliveryStar = findViewById(R.id.delivery_star);
        mCustomerService = findViewById(R.id.customer_service);
        mExplore = findViewById(R.id.explore);
        mCart = findViewById(R.id.cart);
        mAddCart = findViewById(R.id.add_cart);
        mBuyImmediate = findViewById(R.id.buy_immediate);
        mIntroductionView = findViewById(R.id.introduction_view);
        mDetailView = findViewById(R.id.detail_view);
        mEvaluationView = findViewById(R.id.evaluation_view);
        mAddressInfo = findViewById(R.id.address_info);
        mDetailTagPics = findViewById(R.id.detail_tag_pics);
        mDetailTagParams = findViewById(R.id.detail_tag_params);
        mDetailPics = findViewById(R.id.detail_pics);
        mDetailParams = findViewById(R.id.detail_params);
        mPullToSee = findViewById(R.id.pull_to_see);

        mBack.setOnClickListener(this);
        mIntroduction.setOnClickListener(this);
        mDetail.setOnClickListener(this);
        mEvaluation.setOnClickListener(this);
        mMore.setOnClickListener(this);
        mDetailTagPics.setOnClickListener(this);
        mDetailTagParams.setOnClickListener(this);
        mBuyImmediate.setOnClickListener(this);
        mAddCart.setOnClickListener(this);
        findViewById(R.id.specification_select).setOnClickListener(this);
        findViewById(R.id.address_select).setOnClickListener(this);

        mIntroduction.setSelected(true);
        mDetailPicsAdapter = new DetailPicsAdapter(this);
        mGoodsPics.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mGoodsPics.setAdapter(mDetailPicsAdapter);

        mDetailIntroPicsAdapter = new DetailIntroductionPicsAdapter(this);
        mDetailPics.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDetailPics.setAdapter(mDetailIntroPicsAdapter);

        mDetailParamsAdapter = new DetailParamsAdapter(this);
        mDetailParams.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDetailParams.setAdapter(mDetailParamsAdapter);

        mIntroductionView.setBottomView(mPullToSee, new UpDownRefreshView.OnLoadingListener() {
            @Override
            public void onLoading() {
                changeView(1);
            }
        });

        initData();
        changeView(0);
    }
    float oldY = 0;


    private void initData() {
        List<String> pics = new ArrayList<>(5);
        pics.add("http://192.168.1.23/resource-file/2019-05-31/3.jpg");
        pics.add("http://192.168.1.23/resource-file/2019-05-31/4.jpg");
        pics.add("http://192.168.1.23/resource-file/2019-05-31/11.jpg");
        pics.add("http://192.168.1.23/resource-file/2019-05-31/12.jpg");
        pics.add("http://192.168.1.23/resource-file/2019-05-31/13.jpg");
        pics.add("http://192.168.1.23/resource-file/2019-05-31/16.jpg");
        pics.add("http://192.168.1.23/resource-file/2019-05-31/6.jpg");
        mDetailPicsAdapter.setItems(pics);

        List<String> pics2 = new ArrayList<>(5);
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/1.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/2.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/5.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/7.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/8.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/9.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/10.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/14.jpg");
        pics2.add("http://192.168.1.23/resource-file/2019-05-31/15.jpg");
        mDetailIntroPicsAdapter.setItems(pics2);

        List<DetailParamsData> datas = new ArrayList<>(5);
        datas.add(new DetailParamsData("净含量（ml）", "7920"));
        datas.add(new DetailParamsData("保质期", "18个月"));
        datas.add(new DetailParamsData("储存方法", "常温或冷藏，避免阳光直射。若出现少量矿物质结晶属正常现象，请放心饮用。"));
        datas.add(new DetailParamsData("生产许可证", "SC10622042103775"));
        datas.add(new DetailParamsData("储存方法", "避免阳光直射"));
        mDetailParamsAdapter.setItems(datas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.more:

                break;
            case R.id.introduction:
                changeView(0);
                break;
            case R.id.detail:
                changeView(1);
                break;
            case R.id.evaluation:
                changeView(2);
                break;
            case R.id.detail_tag_pics:
                changeDetailView(0);
                break;
            case R.id.detail_tag_params:
                changeDetailView(1);
                break;
            case R.id.buy_immediate:
                ActManager.toConfirmOrder(this);
                break;
            case R.id.add_cart:
                showSpecification();
                break;
            case R.id.specification_select:
                showSpecification();
                break;
            case R.id.address_select:
                showAddressList();
                break;

        }
    }

    /**
     * 标题栏切换
     * @param index
     */
    private void changeView(int index) {
        mIntroductionView.setVisibility(0 == index ? View.VISIBLE : View.INVISIBLE);
        mDetailView.setVisibility(1 == index ? View.VISIBLE : View.INVISIBLE);
        mEvaluationView.setVisibility(2 == index ? View.VISIBLE : View.INVISIBLE);

        mIntroduction.setSelected(0 == index);
        mDetail.setSelected(1 == index);
        mEvaluation.setSelected(2 == index);

        if (mDetail.isSelected()) {
            changeDetailView(0);
        } else {
            mDetailPics.scrollToPosition(0);
        }
    }

    /**
     * 详情卡片
     * @param index
     */
    private void changeDetailView(int index) {
        mDetailPics.setVisibility(0 == index ? View.VISIBLE : View.INVISIBLE);
        mDetailParams.setVisibility(1 == index ? View.VISIBLE : View.INVISIBLE);

        mDetailTagPics.setSelected(0 == index);
        mDetailTagParams.setSelected(1 == index);
    }

    /**
     * 选择地址
     */
    private void showAddressList() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_address_select, null);
        PopupWindow popupWindow = new PopupWindow(view, DeviceUtils.dip2px(this, 480), ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.AnimationRightInLeftOut);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(mMore);
        RecyclerView list = view.findViewById(R.id.address_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        DetailAddressAdapter adapter = new DetailAddressAdapter(this);
        list.setAdapter(adapter);
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
//                ToastUtils.shortToast(DetailActivity.this, "选择位置：" + adapter.getSelectItem());
                mAddressInfo.setText(adapter.getSelectedAddress());
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                AndroidUtils.getInstance().setWindowAlpha(DetailActivity.this, 1);
            }
        });

        AndroidUtils.getInstance().setWindowAlpha(DetailActivity.this, 0.5f);
        List<String> pics = new ArrayList<>(5);
        pics.add("上海市闵行区七宝镇宜山路1618弄新意诚A栋综合办公楼13楼1301-1310室");
        pics.add("上海市徐汇区宜山路1618弄新意诚A栋13楼1301-1310室");
        pics.add("上海市闵行区七宝镇宜山路1618弄新意诚A栋综合办公楼13楼1301-1310室");
        pics.add("上海市闵行区七宝镇宜山路1618弄新意诚A栋综合办公楼13楼1301");
        pics.add("家");
        adapter.setItems(pics);
    }

    /**
     * 选择规格
     */
    private void showSpecification() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_specification_select, null);
        PopupWindow popupWindow = new PopupWindow(view, DeviceUtils.dip2px(this, 480), ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.AnimationRightInLeftOut);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(mMore);

        RecyclerView list = view.findViewById(R.id.data_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        DetailSpecificationAdapter adapter = new DetailSpecificationAdapter(this);
        list.setAdapter(adapter);
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                AndroidUtils.getInstance().setWindowAlpha(DetailActivity.this, 1);
            }
        });
        AndroidUtils.getInstance().setWindowAlpha(DetailActivity.this, 0.5f);

        List<DetailSpecificationData> data = new ArrayList<>();
        List<String> items = new ArrayList<>(7);
        items.add("幻夜黑");
        items.add("魅海蓝");
        items.add("魅焰红");
        items.add("梦幻紫");
        items.add("幻影紫");
        data.add(new DetailSpecificationData("颜色", "", items));

        items = new ArrayList<>();
        items.add("全网通(6GB 32GB)");
        items.add("全网通(4GB 64GB)");
        items.add("全网通(4GB 128GB)");
        items.add("全网通(8GB 64GB)");
        items.add("幻影紫");
        data.add(new DetailSpecificationData("版本", "请选择版本", items));

        adapter.setItems(data);
    }
}
