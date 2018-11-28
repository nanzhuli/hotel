package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employ")
public class employ {
    @Id
    private int employno;
    private String employname;
    private int employsex;
    private int employage;
    private int employposition;
    private String employauthority;
    private int employpaymentpermonth;
    private int employworktime;

    public int getEmployno() {
        return employno;
    }

    public void setEmployno(int employno) {
        this.employno = employno;
    }

    public String getEmployname() {
        return employname;
    }

    public void setEmployname(String employname) {
        this.employname = employname;
    }

    public int getEmploysex() {
        return employsex;
    }

    public void setEmploysex(int employsex) {
        this.employsex = employsex;
    }

    public int getEmployage() {
        return employage;
    }

    public void setEmployage(int employage) {
        this.employage = employage;
    }

    public int getEmployposition() {
        return employposition;
    }

    public void setEmployposition(int employposition) {
        this.employposition = employposition;
    }

    public String getEmployauthority() {
        return employauthority;
    }

    public void setEmployauthority(String employauthority) {
        this.employauthority = employauthority;
    }

    public int getEmploypaymentpermonth() {
        return employpaymentpermonth;
    }

    public void setEmploypaymentpermonth(int employpaymentpermonth) {
        this.employpaymentpermonth = employpaymentpermonth;
    }

    public int getEmployworktime() {
        return employworktime;
    }

    public void setEmployworktime(int employworktime) {
        this.employworktime = employworktime;
    }

    @Override
    public String toString() {
        return "employ{" +
                "employno=" + employno +
                ", employname='" + employname + '\'' +
                ", employsex=" + employsex +
                ", employage=" + employage +
                ", employposition=" + employposition +
                ", employauthority='" + employauthority + '\'' +
                ", employpaymentpermonth=" + employpaymentpermonth +
                ", employworktime=" + employworktime +
                '}';
    }
}
