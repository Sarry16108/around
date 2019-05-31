package com.example.lib_generic.utils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DeviceUtils {

    private static String deviceID="";
    public  static String getDeviceId()
    {
        if(deviceID == null || deviceID .equals(""))
        {

        }
        return deviceID;
    }

    private static int mScreenWidth = 0;
    private static int mScreenHeight = 0;

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return  手机IMEI
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }


    /******
     * Pseudo-Unique ID
     */
    public static String getPU_ID() {


        try{
            String m_szDevIDShort = "35" + //we make this look like a valid IMEI

                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
//            LogEx.E("getPU_ID="+m_szDevIDShort);
            return  m_szDevIDShort;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static String getAndroid_ID(Context context){
        try {
            String m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//            LogEx.E("getAndroid_ID="+m_szAndroidID);
            return m_szAndroidID;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getMacAddress(Context context){
        try{
            WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
//            LogEx.E("getMacAddress="+m_szWLANMAC);
            return m_szWLANMAC;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getDeviceId(Context context){
        try{
            TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
            String szImei = TelephonyMgr.getDeviceId();
            return szImei;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static  String getOnlyNum(Context context){
        String m_szLongID = getDeviceId(context) + getPU_ID()
                + getAndroid_ID(context)+ getMacAddress(context);
// compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(),0,m_szLongID.length());
// get md5 bytes
        byte p_md5Data[] = m.digest();
// create a hex string
        String m_szUniqueID = new String();
        for (int i=0;i<p_md5Data.length;i++) {
            int b =  (0xFF & p_md5Data[i]);
// if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID+="0";
// add number to string
            m_szUniqueID+=Integer.toHexString(b);
        }   // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();
//        LogEx.E("getOnlyNum="+m_szUniqueID);
        return m_szUniqueID;

    }


    public static String UUID="";
    public static String getIMEIs(Context context){

//        if(UUID.equals(""))
//        {
            UUID = getOnlyNum(context);
//        }
        return UUID;

    }

    public static void setDisplay(DisplayMetrics metrics) {
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;

        LogUtils.d("screen width:" + mScreenWidth + "  height:" + mScreenHeight);
    }

    public static int getScreenWidth() {
        return mScreenWidth;
    }


    public static int getScreenHeight() {
        return mScreenHeight;
    }


    public static String getMac(){
        String macDefault = "00:08:69:03:01:03";//TEST_CODE
        String macStr =   DeviceUtils.getMacFromHardware();
        for(String mac : MACS){
            if(macStr.equals(mac)){
                return macStr;
            }
        }
        return macDefault;
    }

    public static boolean hasMacRegister(){
        String macStr =   DeviceUtils.getMacFromHardware();
        for(String mac : MACS){
            if(macStr.equals(mac)){
                return true;
            }
        }
        return false;
    }

    public static String getProductCode(){
        String productCode =  getDeviceSnByMac(getMac());
        return productCode;
    }
    static String[] MACS ={"00:08:69:03:01:01","00:08:69:03:01:02","00:08:69:03:01:03","00:08:69:03:01:05","00:08:69:03:01:06"};
    static String[] DEVICE_SN ={"0000134278275","0000152228667","0002069968219","0002070095259","0002070099914"};
    public static String getDeviceSnByMac(String macStr){
        if(macStr!=null){
            for(int i=0;i<MACS.length;i++){
                String mac=MACS[i];
                if(mac.equals(macStr)){
                    return DEVICE_SN[i];
                }
            }
        }
        return null;
    }



    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
