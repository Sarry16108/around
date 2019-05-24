package com.example.home_around.entity;

public abstract class HomeBaseItemData {
    public static final int TYPE_HEADER = 0;        //头
    public static final int TYPE_CATEGORY = 1;      //小分类
    public static final int TYPE_BRAND_SUPPLY = 2;  //品牌直供
    public static final int TYPE_RECOMMEND = 3;    //推荐数据
    public static final int TYPE_DIVIDER_TAG = 4;  //分割处标签文字
    public static final int TYPE_FOOTER = 5;        //底部

    int spanCount = 1;          //默认值

    public abstract int getType();

    public void setSpanCount(int count) {
        this.spanCount = count;
    }
    public int getSpanCount() {
        return this.spanCount;
    }
}
