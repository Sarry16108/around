package com.example.home_around.entity;

public class MerchantServiceData {
    public static class MerchantServiceItemData {
        private int id;
        private String imgUrl;
        private String title;
        private String extInfo;
        private String price;

        public MerchantServiceItemData(int id, String imgUrl, String title, String extInfo, String price) {
            this.id = id;
            this.imgUrl = imgUrl;
            this.title = title;
            this.extInfo = extInfo;
            this.price = price;
        }

        public int getId() {
            return id;
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
    }
}
