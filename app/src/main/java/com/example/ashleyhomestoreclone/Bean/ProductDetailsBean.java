package com.example.ashleyhomestoreclone.Bean;

public class ProductDetailsBean {
    String name202;
    Double price202, discountPrice202;
    int img202;

    public ProductDetailsBean(int img) {
        this.img202 = img;
    }

    public String getName() {
        return name202;
    }

    public void setName(String name) {
        this.name202 = name;
    }

    public Double getPrice() {
        return price202;
    }

    public void setPrice(Double price) {
        this.price202 = price;
    }

    public Double getDiscountPrice() {
        return discountPrice202;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice202 = discountPrice;
    }

    public int getImg() {
        return img202;
    }
}
