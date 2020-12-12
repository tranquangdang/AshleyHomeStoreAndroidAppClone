package com.example.ashleyhomestoreclone.Bean;

public class ProductDetailsBean {
    String name;
    Double price, discountPrice;
    int img;

    public ProductDetailsBean(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getImg() {
        return img;
    }
}
