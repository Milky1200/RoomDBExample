package com.example.roomdbexample;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Contact.class,version = 2)
@TypeConverters(Convertors.class)
public  abstract class ContactDB extends RoomDatabase{
    public abstract Dao dao();
    private static volatile ContactDB db;
    public static final Migration migration1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter Table contactTable Add column isActive Integer Not Null Default 1");
        }
    };

    public static ContactDB getInstance(Context context){
        if(db==null){
            synchronized (ContactDB.class) {
                if(db==null) {
                    db = Room.databaseBuilder(context.getApplicationContext()
                                    , ContactDB.class, "ContactDB")
                            //.allowMainThreadQueries()
                            .addMigrations(db.migration1_2)
                            .build();
                }
            }
        }
        return db;
    }
}
