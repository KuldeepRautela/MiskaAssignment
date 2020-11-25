package com.example.miskaassignment.jetpack;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static final String baseUrl = "https://restcountries.eu/rest/v2/region/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static CountryApi createApi(){
        return  retrofit.create(CountryApi.class);
    }
}
