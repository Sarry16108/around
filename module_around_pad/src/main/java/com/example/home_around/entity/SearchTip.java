package com.example.home_around.entity;

public class SearchTip {

    public static class SearchTipItem {
        private String tip;
        private int     type = 1;

        public SearchTipItem(String tip, int type) {
            this.tip = tip;
            this.type = type;
        }

        public String getTip() {
            return tip;
        }

        public int getType() {
            return type;
        }
    }
}
