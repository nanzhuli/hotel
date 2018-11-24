package com.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "standard")
public class standard {
    @Id
    @GeneratedValue
    @Column(name = "stdno")
    private int stdno;

    @Column(name = "stdname")
    private String stdname;

    @Column(name = "roomarea")
    private int roomerea;

    @Column(name = "bedno")
    private int bedno;

    @Column(name = "equip1")
    private String equip1;

    @Column(name = "equip2")
    private String equip2;

    public int getStdno() {
        return stdno;
    }

    public String getStdname() {
        return stdname;
    }

    public int getRoomerea() {
        return roomerea;
    }

    public int getBedno() {
        return bedno;
    }

    public String getEquip1() {
        return equip1;
    }

    public String getEquip2() {
        return equip2;
    }

    @Override
    public String toString() {
        return "standard{" +
                "stdno=" + stdno +
                ", stdname='" + stdname + '\'' +
                ", roomerea=" + roomerea +
                ", bedno=" + bedno +
                ", equip1='" + equip1 + '\'' +
                ", equip2='" + equip2 + '\'' +
                '}';
    }
}
