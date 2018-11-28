package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class room {
    @Id
    private int roomno;
    private int type;
    private int price;
    private int ifwindow;
    private String comment;

//    @ManyToOne(targetEntity = standard.class)
//    @JoinColumn(name = "type")
//    private standard s;


    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIfwindow() {
        return ifwindow;
    }

    public void setIfwindow(int ifwindow) {
        this.ifwindow = ifwindow;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "room{" +
                "roomno=" + roomno +
                ", type=" + type +
                ", price=" + price +
                ", ifwindow=" + ifwindow +
                ", comment='" + comment + '\'' +
                '}';
    }
}
