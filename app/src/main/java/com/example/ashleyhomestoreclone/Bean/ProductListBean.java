package com.example.ashleyhomestoreclone.Bean;

public class ProductListBean {
    int img202;
    String name202;
    double price202, disPrice202;

    public ProductListBean(int img202, String name202, double price202, double disPrice202) {
        this.img202 = img202;
        this.name202 = name202;
        this.price202 = price202;
        this.disPrice202 = disPrice202;
    }

    public int getImg() {
        return img202;
    }

    public void setImg(int img) {
        this.img202 = img;
    }

    public String getName() {
        return name202;
    }

    public void setName(String name) {
        this.name202 = name;
    }

    public double getPrice() {
        return price202;
    }

    public void setPrice(double price) {
        this.price202 = price;
    }

    public double getDisPrice() {
        return disPrice202;
    }

    public void setDisPrice(double disPrice) {
        this.disPrice202 = disPrice;
    }
}
