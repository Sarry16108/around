package com.example.lib_generic.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_generic.WrappedPackageContext;
import com.example.lib_generic.bean.ConstValue;
import com.example.lib_generic.utils.DeviceUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.utils.SharePrefUtils;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：application通用基类
 **/

public class SfyBaseApplication extends Application {

    public static Context mContext;
    //防止初始化多次
    private static boolean  mIsCreated = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

//        if(com.example.lib_generic.BuildConfig.isPadModule){
//            try {
//                Log.d("SfyBaseTag","---1---"+mContext.getPackageName());
//                Context context = mContext.createPackageContext("com.sfy.smarthome",Context.CONTEXT_IGNORE_SECURITY);
//                //mContext=context;
//                mContext = WrappedPackageContext.createApplicationContext(mContext,context);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        Log.d("SfyBaseTag","---2---"+mContext.getPackageName());

 //       Context var2 = mContext.getApplicationContext();
//        Log.d("SfyBaseTag","---3---"+var2.getPackageName());
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        if (!mIsCreated) {
            mIsCreated = true;
            createOnce();
        }

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
