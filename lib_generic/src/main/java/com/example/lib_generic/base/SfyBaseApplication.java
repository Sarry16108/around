package com.example.lib_generic.base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.example.lib_generic.bean.ConstValue;
import com.example.lib_generic.utils.DeviceUtils;
import com.example.lib_generic.utils.SharePrefUtils;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：application通用基类
 **/

public class SfyBaseApplication extends Application {

    public static Context mContext;
    //防止初始化多次

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

//        ARouter.openLog();
//        ARouter.openDebug();
//        ARouter.init(this);

        if (!isCrated()) {
            createOnce();
        }

    }

    //application id检查看是否启动过
    private boolean isCrated() {
        return true;
    }
    protected void createOnce() {



        if (TextUtils.isEmpty(SharePrefUtils.getString(ConstValue.PHONE_UNIQUE_MARK))) {
            String phoneMark = DeviceUtils.getOnlyNum(mContext);
            SharePrefUtils.putString(ConstValue.PHONE_UNIQUE_MARK, phoneMark);
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
