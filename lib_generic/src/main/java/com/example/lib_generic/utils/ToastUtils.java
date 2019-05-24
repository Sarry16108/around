package com.example.lib_generic.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * author ：杨会军
 * created at ：2019/4/16
 * description ：系统Toast提示显示类
 **/

public class ToastUtils {

    public static void shortToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }


    public static void longToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }

}
