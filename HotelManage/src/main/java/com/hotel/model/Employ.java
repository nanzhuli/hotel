package com.hotel.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employ")
public class Employ implements UserDetails{
    @Id
    private int employno;
    private String employname;
    private int employsex;
    private int employage;
    private int employposition;
    private int employauthority;
    private int employpaymentpermonth;
    private int employworktime;
    private String username;
    private String password;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println("auths = "+auths);
        return auths;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public int getEmployauthority() {
        return employauthority;
    }

    public void setEmployauthority(int employauthority) {
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
        return "Employ{" +
                "employno=" + employno +
                ", employname='" + employname + '\'' +
                ", employsex=" + employsex +
                ", employage=" + employage +
                ", employposition=" + employposition +
                ", employauthority=" + employauthority +
                ", employpaymentpermonth=" + employpaymentpermonth +
                ", employworktime=" + employworktime +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
