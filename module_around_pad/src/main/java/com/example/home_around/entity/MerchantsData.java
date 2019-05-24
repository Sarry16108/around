package com.example.home_around.entity;

public class MerchantsData {
    public static class MerchantsItemData {
        private int id;
        private String imgUrl;
        private String title;
        private String extInfo;
        private String price;

        public MerchantsItemData(String imgUrl, String title, String extInfo, String price) {
            this.imgUrl = imgUrl;
            this.title = title;
            this.extInfo = extInfo;
            this.price = price;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getExtInfo() {
            return extInfo;
        }

        public String getPrice() {
            return price;
        }

        public int getId() {
            return id;
        }
    }

}
