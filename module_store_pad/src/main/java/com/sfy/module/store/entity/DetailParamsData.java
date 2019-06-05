package com.sfy.module.store.entity;

public class DetailParamsData {
    private String tag;
    private String value;

    public DetailParamsData(String tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }
}
