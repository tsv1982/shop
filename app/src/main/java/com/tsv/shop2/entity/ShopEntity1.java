package com.tsv.shop2.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ShopEntity1 {
    private String nameProduct;
    private String sizeProduct;
    private double priceProduct;
    private String imageProduct;
    private boolean chechTov;
    @PrimaryKey
    private int ID;


    public ShopEntity1() {
    }
@Ignore
    public ShopEntity1(String nameProduct, String sizeProduct, double priceProduct, String imageProduct, boolean chechTov, int ID) {
        this.nameProduct = nameProduct;
        this.sizeProduct = sizeProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.chechTov = chechTov;
        this.ID = ID;

    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public boolean getChechTov() {
        return chechTov;
    }

    public void setChechTov(boolean chechTov) {
        this.chechTov = chechTov;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
