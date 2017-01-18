package com.lypgod.test.Entities;

import javax.persistence.*;

@Entity
public class Wife {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "husbandId")
    private Husband husband;

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
