package com.example.appban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends BaseAdapter {
    private Context context;
    private List<Country> countries;

    public CountryAdapter(Context context) {
        this.context = context;
        this.countries = new ArrayList<>();
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Country getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_country, parent, false);
            holder = new ViewHolder();
            holder.flagImage = convertView.findViewById(R.id.imageViewFlag);
            holder.countryName = convertView.findViewById(R.id.textViewCountryName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Country country = getItem(position);
        holder.countryName.setText(country.getName());

        String flagUrl = "http://www.geognos.com/api/en/countries/flag/" + country.getAlpha2Code() + ".png";
        Glide.with(context).load(flagUrl).into(holder.flagImage);

        return convertView;
    }

    private static class ViewHolder {
        ImageView flagImage;
        TextView countryName;
    }
}