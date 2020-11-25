package com.example.miskaassignment.jetpack;


import com.example.miskaassignment.jetpack.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {
    @GET("asia")
    Call<List<Country>> getCountries();
}
