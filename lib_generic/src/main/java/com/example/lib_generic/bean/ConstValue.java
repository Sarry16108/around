package com.example.lib_generic.bean;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：使用常量类
 **/

public interface ConstValue {

    /******************************************************************
     *                                    pad与phone共用常量
     ******************************************************************/
    //存储的sn码、device id
    public String DEVICE_SN ="pad_device_id";
    //设备手机号
    public String PHONE_NUM = "phone";




    /******************************************************************
     *                                    pad使用常量
     ******************************************************************/
    //服务器返回的证书数据
    public String PAD_CERT = "temp_cert";
    public String PAD_TOKEN = "TOKEN";
    //通话记录
    public String PAD_CONVERSATION = "conversation";
    //搜索记录
    public String PAD_SEARCH_HIS_RECORD = "hist_record";



    /******************************************************************
     *                                    phone使用常量
     ******************************************************************/
    //设备唯一标志
    public String PHONE_UNIQUE_MARK ="phone_unique_mark";
    //与当前用户绑定的设备的id
    public String USER_RELA_ID = "phone_user_rela_id";
}
