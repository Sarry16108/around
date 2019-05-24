package com.example.lib_generic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.lib_generic.base.SfyBaseApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：sharepreference操作类
 **/

public class SharePrefUtils {
    private static String mShareName = "sfy";


    static class Init {
        private static SharedPreferences  mShared = SfyBaseApplication.getContext().getSharedPreferences(mShareName, Context.MODE_PRIVATE);
    }


    public static void putString(String key, String value) {
        if (null == key || null == value) {
            return;
        }

        Init.mShared.edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        return Init.mShared.getString(key, "");
    }


    public static void putLong(String key, long value) {
        Init.mShared.edit().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return Init.mShared.getLong(key, 0);
    }


    public static void putBoolean(String key, Boolean value) {
        Init.mShared.edit().putBoolean(key, value).commit();
    }

    public static void remove(String key) {
        Init.mShared.edit().remove(key).commit();
    }

    public static Boolean getBoolean(String key) {
        return Init.mShared.getBoolean(key, false);
    }

    public static <T> T getObject(String key, Class<T> type) {
        return GsonUtils.castJsonObject(getString(key), type);
    }

    public static void putObject(String key, Object obj) {
        putString(key, GsonUtils.castObjectJson(obj));
    }

    public static <T> Set<T> getObjectSet(String key, Class<T> type) {
        return GsonUtils.castJsonObjSet(getString(key), type);
    }

    public static void addObjList(String key, Object obj) {
        List list = getObjList(key, obj.getClass());
        list.add(obj);
        putObject(key, list);
    }

    public static <T> List<T> getObjList(String key, Class<T> type) {
        String value = getString(key);
        if (TextUtils.isEmpty(value)) {
            return new ArrayList<T>();
        }

        return GsonUtils.castJsonObjList(value, type);
    }
}
