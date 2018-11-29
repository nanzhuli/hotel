package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
public class order {
    @Id
    private int orderno;
    private int roomcount;
    private int price;
    private String name;
    private String id;
    private String phone;
    private Timestamp starttime;
    private Timestamp endtime;
    private String ismenber;
    private String isenter;

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public int getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(int roomcount) {
        this.roomcount = roomcount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public String getIsmenber() {
        return ismenber;
    }

    public void setIsmenber(String ismenber) {
        this.ismenber = ismenber;
    }

    public String getIsenter() {
        return isenter;
    }

    public void setIsenter(String isenter) {
        this.isenter = isenter;
    }

    @Override
    public String toString() {
        return "order{" +
                "orderno=" + orderno +
                ", roomcount=" + roomcount +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", ismenber='" + ismenber + '\'' +
                ", isenter='" + isenter + '\'' +
                '}';
    }
}
