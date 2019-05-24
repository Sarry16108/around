package com.example.home_around.entity;

import java.util.List;

public class HotSearchData extends SearchBaseItemData {
    private List<String> list;

    public HotSearchData(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public int getType() {
        return TYPE_HOT_SEARCH;
    }
}
