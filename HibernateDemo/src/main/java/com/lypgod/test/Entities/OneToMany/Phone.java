package com.lypgod.test.Entities.OneToMany;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Phone {
    @Id
    @GeneratedValue
    private int id;
    @NaturalId
    @Column(unique = true)
    private String phoneNumber;
    @ManyToOne
    private Person person;

    public Phone() {
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Phone phone = (Phone) obj;
        return Objects.equals(phoneNumber, phone.phoneNumber);
    }
}
