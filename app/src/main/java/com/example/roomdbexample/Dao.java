package com.example.roomdbexample;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@androidx.room.Dao
public interface Dao {
    @Insert
    public void insertContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    @Query("Select * from contactTable")
    List<Contact> fetchAll();
}
