package com.example.lib_generic.base;

import android.app.Service;

import com.example.lib_generic.utils.LogUtils;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：描述该类的作用
 **/

public abstract class SfyBaseService extends Service {
    protected final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d();
    }
}
