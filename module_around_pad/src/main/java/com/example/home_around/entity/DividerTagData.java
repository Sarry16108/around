package com.example.home_around.entity;

public class DividerTagData extends HomeBaseItemData {
    private String tag;
    private int     position;   //所在位置：0，左，1：中，2：右

    public DividerTagData(String tag, int spanCount, int position) {
        this.tag = tag;
        this.position = position;
        this.spanCount = spanCount;
    }

    public String getTag() {
        return tag;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int getType() {
        return TYPE_DIVIDER_TAG;
    }
}
