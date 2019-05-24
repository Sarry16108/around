package com.example.lib_generic.utils;

import android.os.Build;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;

import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.base.SfyBaseApplication;

import static android.content.Context.POWER_SERVICE;

public class AndroidUtils {
    private static AndroidUtils mInstance;

    public static AndroidUtils getInstance() {
        if (null == mInstance) {
            synchronized (AndroidUtils.class) {
                if (null == mInstance) {
                    mInstance = new AndroidUtils();
                }

                return mInstance;
            }
        }

        return mInstance;
    }

    //隐藏底部操控栏
    public void hideBottomUIMenu(SfyBaseActivity activity) {
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
