package com.example.miskaassignment.jetpack.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miskaassignment.jetpack.model.Countries;
import com.example.miskaassignment.jetpack.room.RoomDB;

import java.util.List;

public class CountriesRepo  {
    private RoomDB roomDB = RoomDB.getInstance();
    public void insert(final List<Countries> countries){


        new Thread(){
            @Override
            public void run() {
                super.run();
                roomDB.getCountryDao().insertData(countries);
            }
        }.start();

    }
    public LiveData<List<Countries>> getCountriesData(){
        return roomDB.getCountryDao().getCountriesData();
    }
    public void deleteAllData(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                roomDB.getCountryDao().deleteAllData();
            }
        }.start();

    }
}
