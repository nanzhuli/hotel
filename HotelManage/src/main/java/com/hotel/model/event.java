package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class event {
    @Id
    private int eventno;
    private int type;
    private int roomno;
    private int employno;
    private Timestamp starttime;
    private Timestamp endtime;
    private String comment;

    public int getEvento() {
        return eventno;
    }

    public void setEvento(int evento) {
        this.eventno = evento;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public int getEmployno() {
        return employno;
    }

    public void setEmployno(int employno) {
        this.employno = employno;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtiome() {
        return endtime;
    }

    public void setEndtiome(Timestamp endtime) {
        this.endtime = endtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "event{" +
                "evento=" + eventno +
                ", type=" + type +
                ", roomno=" + roomno +
                ", employno=" + employno +
                ", starttime=" + starttime +
                ", endtiome=" + endtime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
