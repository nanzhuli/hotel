package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity@Table(name = "orderroom")
public class orderroom {
    @Id
    private int orno;
    private int orderno;
    private int rino;
    private String brand;

    public int getOrno() {
        return orno;
    }

    public void setOrno(int orno) {
        this.orno = orno;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public int getRino() {
        return rino;
    }

    public void setRino(int rino) {
        this.rino = rino;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "orderroom{" +
                "orno=" + orno +
                ", orderno=" + orderno +
                ", rino=" + rino +
                ", brand='" + brand + '\'' +
                '}';
    }
}
