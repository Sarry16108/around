package com.example.home_around.entity;

public class SearchResultData {


    public static class SearchResultItem extends SearchBaseItemData{
        private String id;
        private String imgUrl;
        private String title;
        private String extInfo;

        public SearchResultItem(String id, String imgUrl, String title, String extInfo) {
            this.id = id;
            this.imgUrl = imgUrl;
            this.title = title;
            this.extInfo = extInfo;
        }

        public String getId() {
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

        @Override
        public int getType() {
            return TYPE_SEARCH_RESULT;
        }
    }
}
