package com.example.lib_generic.utils;


import android.app.Activity;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：activity、fragment跳转管理类
 **/

public class RoutePadUtils {

    //通讯录页
    public static final String PAD_ACTIVITY_ADDRESS_LIST = "/audio_video_chat/activity/addresslist";
    //视频通话
    public static final String PAD_ACTIVITY_VIDEO_CONVERSATION = "/audio_video_chat/activity/conversation";
    //添加
    public static final String PAD_ACTIVITY_ADD_CONTACT = "/audio_video_chat/activity/addcontact";
    //通话详情
    public static final String PAD_ACTIVITY_CHAT_DETAIL = "/audio_video_chat/activity/chatdetail";
    //回家看看
    public static final String PAD_ACTIVITY_TIP_GO_HOME = "/audio_video_chat/activity/gohome";
    //静默控制
    public static final String PAD_ACTIVITY_TIP_ACCEPT_SILENT = "/audio_video_chat/activity/acceptsilent";
    //远程控制
    public static final String PAD_ACTIVITY_TIP_REMOTE_CONTROL = "/audio_video_chat/activity/remotecontrol";

    //绑定页面
    public static final String PAD_ACTIVITY_BIND = "/padlauncher/bind";
    //启动页
    public static final String PAD_ACTIVITY_LAUNCHER = "/padlauncher/launcher";


    /***************************************************************************
     *                                      pad 端activity跳转
     *
     ***************************************************************************/
    /**
     *    视频会话
     *    userId是手机号
     */
    public static void toVideoConversation(String userId, boolean isVideo, boolean isCall) {
        ARouter.getInstance().build(PAD_ACTIVITY_VIDEO_CONVERSATION)
                .withString("user_id", userId)
                .withBoolean("is_video", isVideo)
                .withBoolean("is_call", isCall)
                .navigation();
    }

    /**
     *    通讯录页
     *    userId是手机号
     */
    public static void toAddressList(int initPage) {
        ARouter.getInstance().build(PAD_ACTIVITY_ADDRESS_LIST).withInt("init_page", initPage).navigation();
    }


    //绑定页
    public static void toBind(boolean isShowQrCode) {
        ARouter.getInstance().build(PAD_ACTIVITY_BIND).withBoolean("showQrCode", isShowQrCode).navigation();
    }

    //启动页
    public static void toLauncher() {
        ARouter.getInstance().build(PAD_ACTIVITY_LAUNCHER).navigation();
    }

    //添加用户页
    public static void toAddContact() {
        ARouter.getInstance().build(PAD_ACTIVITY_ADD_CONTACT).navigation();
    }

    //添加用户页
    public static void toChatDetail(String id) {
        ARouter.getInstance().build(PAD_ACTIVITY_CHAT_DETAIL).withString("id", id).navigation();
    }


    //回家看看
    public static void toTipGoHome() {
        ARouter.getInstance().build(PAD_ACTIVITY_TIP_GO_HOME).navigation();
    }

    //添加用户页
    public static void toTipAcceptSilent() {
        ARouter.getInstance().build(PAD_ACTIVITY_TIP_ACCEPT_SILENT).navigation();
    }

    //添加用户页
    public static void toTipRemoteControl() {
        ARouter.getInstance().build(PAD_ACTIVITY_TIP_REMOTE_CONTROL).navigation();
    }

}
