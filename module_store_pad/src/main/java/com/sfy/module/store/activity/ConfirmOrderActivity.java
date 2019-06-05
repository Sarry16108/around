package com.sfy.module.store.activity;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.utils.ToastUtils;
import com.sfy.module.store.R;
import com.sfy.module.store.adapter.ConfirmOrderAdapter;
import com.sfy.module.store.entity.ConfirmOrderData;

import java.util.ArrayList;
import java.util.List;

/**
 * 提交订单
 */
public class ConfirmOrderActivity extends SfyBaseActivity implements View.OnClickListener {

    private ImageView       mBack;
    private RelativeLayout  mEditAddress;
    private TextView        mName;
    private TextView        mPhone;
    private TextView        mAddress;
    private RecyclerView    mDatas;
    private TextView        mBottomPrice;
    private TextView        mPay;
    private ConfirmOrderAdapter mAdapter;

    @Override
    protected void initCommon() {
        super.initCommon();
        setContentView(R.layout.activity_confirm_order);

        mBack = findViewById(R.id.back);
        mEditAddress = findViewById(R.id.edit_address);
        mName = findViewById(R.id.name);
        mPhone = findViewById(R.id.phone);
        mAddress = findViewById(R.id.address);
        mDatas = findViewById(R.id.datas);
        mBottomPrice = findViewById(R.id.bottom_price);
        mPay = findViewById(R.id.settlement);

        mBack.setOnClickListener(this);
        mEditAddress.setOnClickListener(this);
        mPay.setOnClickListener(this);
        mDatas.setFocusable(false);
        findViewById(R.id.address_more).setOnClickListener(this);

        mAdapter = new ConfirmOrderAdapter(this);
        mDatas.setLayoutManager(new LinearLayoutManager(this));
        mDatas.setAdapter(mAdapter);

        initData();
    }

    private void initData() {
        mName.setText("大梨");
        mPhone.setText("18018751849");
        mAddress.setText("上海市闵行区宜山路1618号新意城A栋1301-1310室");

        List<ConfirmOrderData> list = new ArrayList<>(3);
        ConfirmOrderData data = new ConfirmOrderData("http://192.168.1.23/resource-file/2019-05-29/sp-1.png", "华为 HUAWEI nova 4e 3200万立体美颜AI超广角三摄珍珠屏4GB+128GB幻夜黑全网通版双", "幻夜黑   4GB 64GB", "15", 2, false);
        data.setShop("华为官方旗舰店", "http://192.168.1.23/resource-file/2019-05-29/sp-2.png", "普通快递 免邮", true);
        list.add(data);
        data = new ConfirmOrderData("http://192.168.1.23/resource-file/2019-05-29/sp-3.png", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15", 1, true);
        data.setShop("华为官方旗舰店", "http://192.168.1.23/resource-file/2019-05-29/sp-4.png", "普通快递 免邮", false);
        list.add(data);
        data = new ConfirmOrderData("http://192.168.1.23/resource-file/2019-05-29/sp-5.png", "MRZZ水素水 日本原装进口富氢水高端矿泉水整箱饮用550ml*6袋/箱 官方正品", "补水", "15", 2, true);
        data.setShop("芬尼湾官方旗舰店", "http://192.168.1.23/resource-file/2019-05-29/sp-6.png", "普通快递 免邮", true);
        list.add(data);

        mAdapter.setItems(list);
        mBottomPrice.setText("¥" + mAdapter.getTotalPrice());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.settlement:
                ToastUtils.shortToast(this, "支付");
                break;
            case R.id.address_more:
                ToastUtils.shortToast(this, "地址修改");
                break;
        }
    }

}
