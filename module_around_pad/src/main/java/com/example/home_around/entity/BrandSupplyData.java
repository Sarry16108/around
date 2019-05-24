package com.example.home_around.entity;

public class BrandSupplyData extends HomeBaseItemData {
    private int id;
    private String imgUrl;

    public BrandSupplyData(String imgUrl, int spanCount) {
        this.imgUrl = imgUrl;
        this.spanCount = spanCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return TYPE_BRAND_SUPPLY;
    }
}
