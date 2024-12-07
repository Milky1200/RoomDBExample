package com.example.roomdbexample;

import androidx.room.TypeConverter;

import java.util.Date;

public class Convertors {

    @TypeConverter
    public long dateToLong(Date date){
        return date != null ? date.getTime() : 0L;
    }

    @TypeConverter
    public Date longToDate(long lng){
        return lng == 0L ? null : new Date(lng);
    }
}
