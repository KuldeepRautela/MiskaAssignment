package com.example.miskaassignment.ui.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.miskaassignment.R;
import com.example.miskaassignment.jetpack.CountryApi;
import com.example.miskaassignment.jetpack.model.Countries;
import com.example.miskaassignment.jetpack.model.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryVH> {
    private List<Countries> countries;
    private Activity activity;
    public CountryAdapter(List<Countries> countries, Activity activity){
        this.countries=countries;
        this.activity=activity;
    }
    @NonNull
    @Override
    public CountryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryVH holder, int position) {
        holder.bindData(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryVH extends RecyclerView.ViewHolder{
    private TextView countryname,capital,region,subregion,population,langs,borders;
    private ImageView countryFlag;
        public CountryVH(@NonNull View itemView) {
            super(itemView);
            countryname = itemView.findViewById(R.id.id_country);
            capital= itemView.findViewById(R.id.id_capital);
            region = itemView.findViewById(R.id.id_region);
            subregion = itemView.findViewById(R.id.id_subregion);
            countryFlag = itemView.findViewById(R.id.id_imageview_flag);
            population = itemView.findViewById(R.id.id_population);
            borders = itemView.findViewById(R.id.id_borders);
            langs = itemView.findViewById(R.id.id_lang);
        }
        public void bindData(Countries country){
            countryname.setText("Country : "+country.getName());
            capital.setText("Capital : "+country.getCapital());
            region.setText("Region : "+country.getRegion());
            subregion.setText("Sub Region : "+country.getSubregion());
            SvgLoader.pluck()
                    .with(activity)
                    .load(country.getFlag(), countryFlag);
            population.setText("Population : "+country.getPopulation());
            borders.setText("Borders : "+country.getBorders());
            langs.setText("Languages : "+country.getLanguages());
        }
    }
}
