package com.example.roomdbexample;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "contactTable")
public class Contact {
    @PrimaryKey
    int id;
    String name;
    String email;

    Date createdDate;

    int isActive;

    public Contact(int id, String name, String email, Date currentDate,int active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdDate=currentDate;
        this.isActive=active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
