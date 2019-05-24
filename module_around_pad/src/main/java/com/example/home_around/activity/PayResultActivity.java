package com.example.home_around.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.base.BaseNormalActivity;
import com.example.home_around.utils.ActManager;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.module_around.R;

/**
 * 支付结果
 */
public class PayResultActivity extends BaseNormalActivity implements View.OnClickListener {

    private ImageView   mBack;
    private TextView    mExtInfo;
    private TextView    mHome;
    private TextView    mCheckOrder;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void initCommon() {
        super.initCommon();

        mBack = findViewById(R.id.back);
        mExtInfo = findViewById(R.id.ext_info);
        mHome = findViewById(R.id.home);
        mCheckOrder = findViewById(R.id.check_order);

        mBack.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mCheckOrder.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.home:
                ActManager.toMain(this);
                break;
            case R.id.check_order:
                ActManager.toOrderDetail(this, 0);
                break;
        }
    }
}
