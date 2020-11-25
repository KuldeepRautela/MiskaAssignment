package com.example.miskaassignment.jetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miskaassignment.jetpack.model.Countries;

import java.util.List;


@Dao
public interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(List<Countries> countries);

    @Query("Select * from Countries")
    LiveData<List<Countries>> getCountriesData();
    @Query("Delete from Countries")
    void deleteAllData();
}
