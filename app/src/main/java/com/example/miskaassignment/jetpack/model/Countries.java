package com.example.miskaassignment.jetpack.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Countries {
    @PrimaryKey
    @NonNull
    private String name ;
    private String capital;
    private String flag;
    private String region;
    private String subregion;
    private String population;
    private String borders;
    private String languages;

    @NonNull
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }

    public String getBorders() {
        return borders;
    }

    public String getLanguages() {
        return languages;
    }

    public Countries(@NonNull String name, String capital, String flag, String region, String subregion, String population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }
}
