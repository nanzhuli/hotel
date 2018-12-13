package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderroom")
public class OrderRoom
{
    @Id
    private int orno;
    private int orderno;
    private int roomno;
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

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "OrderRoom{" +
                "orno=" + orno +
                ", orderno=" + orderno +
                ", roomno=" + roomno +
                ", brand='" + brand + '\'' +
                '}';
    }
}
