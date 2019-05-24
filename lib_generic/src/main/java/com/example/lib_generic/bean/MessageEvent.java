package com.example.lib_generic.bean;

/**
 * author ：杨会军
 * created at ：2019/4/18
 * description ：EventBus消息体以及消息类型
 **/

public class MessageEvent {
    public interface MsgType {
        int MT_PUSH_TEST = 0;    //推送测试
        int MT_PUSH_LOGIN = 1;    //推送登录
        int MT_PUSH_CMD = 2;    //推送指令集

        int MT_PAGE = 100;    //页面消息类

        //指定页面接收消息，页面内部的事件在所在页定义子消息类别。
        int MT_PAGE_CHAT_PAD = 201;     //ChatPadActivity
        int MT_PAGE_CHAT_PHONE = 202;     //ChatPhoneActivity

        //音视频通话消息
    }


    public int type;        //主分类
    public int subType;     //次级分类
    public Object data;     //发送的数据体
    public Object[] datas;     //发送的数据体


    public MessageEvent(int type) {
        this.type = type;
    }

    public MessageEvent(int type, Object data) {
        this.type = type;
        this.data = data;
    }


    public MessageEvent(int type, int subType) {
        this.type = type;
        this.subType = subType;
    }

    public MessageEvent(int type, int subType, Object data) {
        this.type = type;
        this.data = data;
        this.subType = subType;
    }


    public MessageEvent(int type, int subType, Object[] datas) {
        this.type = type;
        this.datas = datas;
        this.subType = subType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object[] getDatas() {
        return datas;
    }

    public void setDatas(Object[] datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "type=" + type +
                ", subType=" + subType +
                ", data=" + data +
                '}';
    }
}
