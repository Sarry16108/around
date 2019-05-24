package com.example.home_around.entity;

public class HeaderData extends HomeBaseItemData {
    private String imgUrl;
    private String webUrl;
    private String tips;    //提示文字

    public HeaderData(String imgUrl, String webUrl, String tips, int spanCount) {
        this.imgUrl = imgUrl;
        this.tips = tips;
        this.webUrl = webUrl;
        this.spanCount = spanCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTips() {
        return tips;
    }

    public String getWebUrl() {
        return webUrl;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

}
