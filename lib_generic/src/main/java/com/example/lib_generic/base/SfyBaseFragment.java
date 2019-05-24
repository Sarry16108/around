package com.example.lib_generic.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.lib_generic.utils.LogUtils;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：fragment类的通用基类
 **/

public class SfyBaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG);
    }
}
