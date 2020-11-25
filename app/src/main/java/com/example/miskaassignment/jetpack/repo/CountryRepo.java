package com.example.miskaassignment.jetpack.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.miskaassignment.jetpack.CountryApi;
import com.example.miskaassignment.jetpack.RetrofitService;
import com.example.miskaassignment.jetpack.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepo {
    private static CountryRepo countryRepo;
    private MutableLiveData<List<Country>> mutableLiveData;
    private CountryApi countryApi;

    private CountryRepo(){
         countryApi = RetrofitService.createApi();
    }
    public static CountryRepo getInstance(){
        if(countryRepo==null)
            countryRepo = new CountryRepo();
        return countryRepo;
    }

    public MutableLiveData<List<Country>> getCountries(){
        mutableLiveData = new MutableLiveData<>();
          countryApi.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                   Log.e("error ",t.getLocalizedMessage());
            }
        });
        return mutableLiveData;
    }
}
