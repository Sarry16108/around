package com.example.home_around.entity;

public class AddressInfoData {
    private int     id;
    private String name;
    private String phone;
    private String address;

    public AddressInfoData(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }
}
