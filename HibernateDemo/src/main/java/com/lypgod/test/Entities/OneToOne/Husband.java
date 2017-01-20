package com.lypgod.test.Entities.OneToOne;

import javax.persistence.*;

//@Entity
public class Husband {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne(mappedBy = "husband", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Wife wife;

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
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
