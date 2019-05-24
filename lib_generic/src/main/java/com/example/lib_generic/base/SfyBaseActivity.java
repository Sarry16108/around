package com.example.lib_generic.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_generic.utils.DeviceUtils;
import com.example.lib_generic.utils.LogUtils;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：activity通用基类
 **/

public class SfyBaseActivity extends FragmentActivity {
    protected final String TAG = getClass().getSimpleName();
    protected PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG);

        ARouter.getInstance().inject(this);
        initCommon();
    }

    /**
     * 通用初始化
     */
    protected void initCommon() {
        if (DeviceUtils.getScreenHeight() == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            DeviceUtils.setDisplay(displayMetrics);
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    protected void initWakeLock() {
        /**
         * 保持屏幕常亮、非常亮
         */
        PowerManager powerManager = (PowerManager) SfyBaseApplication.getContext().getSystemService(POWER_SERVICE);
        if (null != powerManager) {
            mWakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");
            mWakeLock.setReferenceCounted(false);
        }
    }

    protected void keepScreenWake() {
        if (null != mWakeLock) {
            LogUtils.e(TAG);
            mWakeLock.acquire();
        }
    }


    protected void cancelScreenWake() {
        if (null != mWakeLock) {
            LogUtils.e(TAG);
            mWakeLock.release();
            mWakeLock = null;
        }
    }

}
