package com.sfy.module.store.utils;

import android.content.Intent;

import com.example.lib_generic.base.SfyBaseActivity;
import com.sfy.module.store.activity.BaseWebActivity;
import com.sfy.module.store.activity.ConfirmOrderActivity;
import com.sfy.module.store.activity.DetailActivity;
import com.sfy.module.store.activity.StoreMainActivity;

/**
 * 页面管理类
 */
public class ActManager {



    /***************************************************************************
     *                                      activity跳转
     *
     ***************************************************************************/
    /**
     *  首页
     */
    public static void toMain(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, StoreMainActivity.class);
        activity.startActivity(intent);
    }

    /**
     *  首页
     */
    public static void toWeb(SfyBaseActivity activity, String url) {
        Intent intent = new Intent(activity, BaseWebActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    public static void toDetail(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, DetailActivity.class);
        activity.startActivity(intent);
    }

    public static void toConfirmOrder(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        activity.startActivity(intent);
    }
}
