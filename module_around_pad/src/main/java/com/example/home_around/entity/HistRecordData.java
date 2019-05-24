package com.example.home_around.entity;

import java.util.List;

public class HistRecordData extends SearchBaseItemData  {
    private String item;

    public HistRecordData(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    @Override
    public int getType() {
        return TYPE_HIS_RECORD;
    }
}
