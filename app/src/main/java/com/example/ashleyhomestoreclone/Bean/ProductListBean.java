package com.example.ashleyhomestoreclone.Bean;

public class ProductListBean {
    int img;
    String name;
    double price, disPrice;

    public ProductListBean(int img, String name, double price, double disPrice) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.disPrice = disPrice;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(double disPrice) {
        this.disPrice = disPrice;
    }
}
