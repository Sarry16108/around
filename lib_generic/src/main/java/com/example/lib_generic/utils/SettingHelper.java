package com.example.lib_generic.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class SettingHelper {

    public final static String SWITCH_KEY_CHILD = "sfy_child_pattern_switch";

    public final static String SWITCH_KEY_PROTECT = "sfy_protect_pattern_switch";

    public final static String SWITCH_KEY_SPEECH = "sfy_voice_switch";

    public final static String SWITCH_KEY_CAMERA = "sfy_camera_switch";

    public final static String SWITCH_KEY_LED = "sfy_led_switch";


    private static List<WeakReference<SettingChangeListener>> mListener = new ArrayList<>();

    @TargetApi(17)
    public static void register(Context context){
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SWITCH_KEY_CHILD), true,mContentObserver);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SWITCH_KEY_PROTECT), true,mContentObserver);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SWITCH_KEY_SPEECH), true,mContentObserver);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SWITCH_KEY_CAMERA), true,mContentObserver);
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SWITCH_KEY_LED), true,mContentObserver);
    }

    public static void unregister(Context context){
        mListener.clear();
        context.getContentResolver().unregisterContentObserver(mContentObserver);
    }

    public static int  getValue(Context context,String key,int defaultValue){
        int value = Settings.Global.getInt(context.getContentResolver(),key, defaultValue);
        return value;
    }

    public static void addListener(SettingChangeListener listener){
        for(int i=0;i<mListener.size();i++){
            if(listener.equals(mListener.get(i).get())){
                return;
            }
        }
        mListener.add(new WeakReference<>(listener));
    }

    public static void removeListener(SettingChangeListener listener){
        int index = -1;
        for(int i=0;i<mListener.size();i++){
            if(listener.equals(mListener.get(i).get())){
                index=i;
            }
        }
        if(index>=0){
            mListener.remove(index);
        }
    }

    public interface  SettingChangeListener{
         String getKey();
         void onChange();
    }

    private static ContentObserver mContentObserver = new ContentObserver(new Handler()){

        @TargetApi(17)
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            for(int i=0;i<mListener.size();i++){
                if(mListener.get(i).get()!=null && Settings.Global.getUriFor(mListener.get(i).get().getKey()).equals(uri)){
                    mListener.get(i).get().onChange();
                }
            }
        }
    };



}
