package com.example.miskaassignment.jetpack.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.miskaassignment.jetpack.model.Countries;
import com.example.miskaassignment.jetpack.model.Country;
import com.example.miskaassignment.jetpack.repo.CountriesRepo;
import com.example.miskaassignment.jetpack.repo.CountryRepo;

import java.util.List;

public class CountryViewModel extends ViewModel {
    private CountryRepo countryRepo;
    private MutableLiveData<List<Country>> mutableLiveData;
    private CountriesRepo countriesRepo=new CountriesRepo();
    public void init(){
        if(mutableLiveData!=null)
            return;
        countryRepo = CountryRepo.getInstance();
        mutableLiveData = countryRepo.getCountries();
    }
    public MutableLiveData<List<Country>> getCountries(){
        return mutableLiveData;
    }

    public void insert(List<Countries> countries){
        countriesRepo.insert(countries);
    }
    public LiveData<List<Countries>> getCountriesData(){
        return countriesRepo.getCountriesData();
    }
    public void deleteAllData(){
        countriesRepo.deleteAllData();
    }
}
