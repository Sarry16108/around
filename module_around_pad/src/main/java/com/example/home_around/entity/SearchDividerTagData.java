package com.example.home_around.entity;

public class SearchDividerTagData extends SearchBaseItemData {
    private String tag;

    public SearchDividerTagData(String tag) {
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
