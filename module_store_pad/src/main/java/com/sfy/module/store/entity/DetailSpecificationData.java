package com.sfy.module.store.entity;

import java.util.List;

public class DetailSpecificationData {
    private String name;
    private String tip;
    private List<String> items;

    public static class Item {
        private String tag;

        public Item(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }

    public DetailSpecificationData(String name, String tip, List<String> items) {
        this.name = name;
        this.tip = tip;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getTip() {
        return tip;
    }

    public List<String> getItems() {
        return items;
    }
}
