package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roomid")
public class roomid {
    @Id
    private int rino;
    private int roomno;
    private String name;
    private String id;

    public int getRino() {
        return rino;
    }

    public void setRino(int rino) {
        this.rino = rino;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
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

    @Override
    public String toString() {
        return "roomid{" +
                "rino=" + rino +
                ", roomno=" + roomno +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
