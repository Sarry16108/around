package com.example.home_around.entity;

public abstract class ShoppingBaseItemData {
    public static final int TYPE_DIVIDER_TAG = 0;        //组标签
    public static final int TYPE_DATA = 1;        //数据

    public abstract int getType();
}
