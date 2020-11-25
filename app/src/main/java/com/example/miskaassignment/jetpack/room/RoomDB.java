package com.example.miskaassignment.jetpack.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.miskaassignment.jetpack.dao.CountryDao;
import com.example.miskaassignment.jetpack.model.Countries;
import com.example.miskaassignment.ui.activities.MainActivity;

import static java.time.chrono.ThaiBuddhistChronology.INSTANCE;
@Database(entities = Countries.class,exportSchema = false,version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB roomDatabase;
    public static RoomDB getInstance(){
        if(roomDatabase==null)
            roomDatabase = Room.databaseBuilder(MainActivity.context,RoomDB.class,"COUNTRY_DB").fallbackToDestructiveMigration().build();
        return roomDatabase;
    }

    public abstract  CountryDao getCountryDao();
}
