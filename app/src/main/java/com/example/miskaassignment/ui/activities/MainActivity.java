package com.example.miskaassignment.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.miskaassignment.R;
import com.example.miskaassignment.jetpack.model.Countries;
import com.example.miskaassignment.jetpack.model.Country;
import com.example.miskaassignment.jetpack.model.Language;
import com.example.miskaassignment.jetpack.viewmodels.CountryViewModel;
import com.example.miskaassignment.ui.adapters.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static Context context;
    private CountryViewModel countryViewModel;
    private List<Countries> countryList = new ArrayList<>();
    private CountryAdapter countryAdapter;
    private boolean isOffline;
    private boolean isDataStored=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        recyclerView = findViewById(R.id.id_recyclerview);
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.init();
        countryViewModel.getCountriesData().observe(this, new Observer<List<Countries>>() {
            @Override
            public void onChanged(List<Countries> countries) {
                if (countries.size() == 0) {
                    showToast("There is no offline data");
                    getDataFromInternet();
                } else {
                    isOffline = true;
                    Log.e("room ", countries.get(0).getCapital());
                    countryList.addAll(countries);
                    countryAdapter.notifyDataSetChanged();
                }
            }
        });

        setupRecyclerView();
    }

    private void getDataFromInternet() {
        Log.e("app","internet");
        countryViewModel.getCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                makeCountriesList(countries);
                countryAdapter.notifyDataSetChanged();
                isOffline = false;
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void makeCountriesList(List<Country> countries) {
        for (Country country : countries) {
            String borders = "";
            for (String border : country.getBorders())
                borders += border + " ";
            String langs = "";
            for (Language language : country.getLanguages())
                langs += language.getName() + " ";
            countryList.add(new Countries(country.getName(), country.getCapital(), country.getFlag(), country.getRegion(), country.getSubregion(), country.getPopulation(), borders, langs));
        }
        if(!isDataStored)
        {
            isDataStored=true;
            countryViewModel.insert(countryList);
        }
    }

    private void setupRecyclerView() {
        if (countryAdapter == null) {
            countryAdapter = new CountryAdapter(countryList, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(countryAdapter);
        } else {
            countryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        new AlertDialog.Builder(this)
                .setTitle("Are you sure you want to delete all the offline data")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        countryViewModel.deleteAllData();
                        if (isOffline) {
                            countryList.clear();
                            countryAdapter.notifyDataSetChanged();
                        }
                        showToast("All data deleted");
                    }
                })
                .setNegativeButton("No", null)
                .create().show();

        return super.onOptionsItemSelected(item);
    }

//    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//           if(isNetworkConnected())
//            getDataFromInternet();
//        }
//    };
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChangeReceiver, intentFilter);
//    }
//    private boolean isNetworkConnected() {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
//    }


    }

