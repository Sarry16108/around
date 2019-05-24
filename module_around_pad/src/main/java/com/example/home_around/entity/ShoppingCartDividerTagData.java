package com.example.home_around.entity;

public class ShoppingCartDividerTagData extends ShoppingBaseItemData {
    private String tag;

    public ShoppingCartDividerTagData(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public int getType() {
        return TYPE_DIVIDER_TAG;
    }
}
