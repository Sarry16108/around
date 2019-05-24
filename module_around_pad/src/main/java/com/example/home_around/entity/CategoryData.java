package com.example.home_around.entity;

public class CategoryData extends HomeBaseItemData {
    private int id;
    private String imgUrl;
    private String title;

    public CategoryData(String imgUrl, String title, int spanCount) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.spanCount = spanCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return TYPE_CATEGORY;
    }

}
