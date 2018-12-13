package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class Event
{
    @Id
    private int eventno;
    private int type;
    private int roomno;
    private int employno;
    private Timestamp starttime;
    private Timestamp endtime;
    private String comment;

    public int getEventno() {
        return eventno;
    }

    public void setEventno(int evento) {
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

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
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
        return "Event{" +
                "eventno=" + eventno +
                ", type=" + type +
                ", roomno=" + roomno +
                ", employno=" + employno +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
