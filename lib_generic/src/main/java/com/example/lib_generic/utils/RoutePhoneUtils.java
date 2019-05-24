package com.example.lib_generic.utils;


import com.alibaba.android.arouter.launcher.ARouter;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：activity、fragment跳转管理类
 **/

public class RoutePhoneUtils {

    //通讯录页
    public static final String PHONE_ACTIVITY_ADDRESS_LIST = "/audio_video_chat/activity/phone_addresslist";
    //视频通话
    public static final String PHONE_ACTIVITY_VIDEO_CONVERSATION = "/audio_video_chat/activity/phone_onversation";


    /***************************************************************************
     *                                      pad 端activity跳转
     *
     ***************************************************************************/
    /**
     *    视频会话
     *    userId是手机号
     */
    public static void toVideoConversation(String userId) {
        ARouter.getInstance().build(PHONE_ACTIVITY_VIDEO_CONVERSATION).withString("user_id", userId).navigation();
    }

    /**
     *    通讯录页
     *    userId是手机号
     */
    public static void toAddressList() {
        ARouter.getInstance().build(PHONE_ACTIVITY_ADDRESS_LIST).navigation();
    }


}
