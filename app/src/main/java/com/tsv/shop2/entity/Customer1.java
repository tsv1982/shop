package com.tsv.shop2.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer1 {
    private String nameCastomer;
    private String IdBayTow;
    private String IdShowTow;
    private String dateBay;
    @PrimaryKey
    private int IdCus;

    public String getDateBay() {
        return dateBay;
    }

    public void setDateBay(String dateBay) {
        this.dateBay = dateBay;
    }

    public String getNameCastomer() {
        return nameCastomer;
    }

    public void setNameCastomer(String nameCastomer) {
        this.nameCastomer = nameCastomer;
    }

    public String getIdBayTow() {
        return IdBayTow;
    }

    public void setIdBayTow(String idBayTow) {
        IdBayTow = idBayTow;
    }

    public String getIdShowTow() {
        return IdShowTow;
    }

    public void setIdShowTow(String idShowTow) {
        IdShowTow = idShowTow;
    }

    public int getIdCus() {
        return IdCus;
    }

    public void setIdCus(int idCus) {
        IdCus = idCus;
    }
}
