package com.example.home_around.entity;

public abstract class SearchBaseItemData {
    public static final int TYPE_HIS_RECORD = 0;        //历史记录
    public static final int TYPE_HOT_SEARCH = 1;        //热搜
    public static final int TYPE_SEARCH_RESULT = 2;      //搜索结果
    public static final int TYPE_DIVIDER_TAG = 3;  //分割处标签文字
    public static final int TYPE_FOOTER = 4;            //底部

    public abstract int getType();
}
