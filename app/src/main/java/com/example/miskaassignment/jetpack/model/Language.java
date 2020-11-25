package com.example.miskaassignment.jetpack.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Language {
    @Ignore
    @PrimaryKey(autoGenerate = false)
    private String countryName ;

    private String name;
    private String nativename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativename() {
        return nativename;
    }

    public void setNativename(String nativename) {
        this.nativename = nativename;
    }
}
