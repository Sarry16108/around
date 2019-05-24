package com.example.home_around.entity;

import java.util.List;

public class ShoppingCartData {
    private List<ShoppingCartItem> items;

    public static class ShoppingCartItem extends ShoppingBaseItemData {
        private int     id;
        private String title;
        private String size;
        private String color;
        private float  price;
        private int    count;
        private String imgUrl;
        private int    storage;

        public ShoppingCartItem(int id, String title, String size, String color, float price, int count, int storage, String imgUrl) {
            this.id = id;
            this.title = title;
            this.size = size;
            this.color = color;
            this.price = price;
            this.count = count;
            this.storage = storage;
            this.imgUrl = imgUrl;
        }


        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getSize() {
            return size;
        }

        public String getColor() {
            return color;
        }

        public float getPrice() {
            return price;
        }

        public int getCount() {
            return count;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public int getStorage() {
            return storage;
        }

        @Override
        public int getType() {
            return TYPE_DATA;
        }
    }

}
