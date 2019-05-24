package com.example.home_around.entity;

public class GroupData {
    public static class GroupItemData {
        private int id;
        private int type;   //group
        private String imgUrl;
        private String title;
        private String extInfo;
        private String price;

        public GroupItemData(int id, int type, String imgUrl, String title, String extInfo, String price) {
            this.id = id;
            this.type = type;
            this.imgUrl = imgUrl;
            this.title = title;
            this.extInfo = extInfo;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
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
