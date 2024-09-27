package com.example.appban;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;

public class CountryDetailDialog extends Dialog {
    private Country country;

    public CountryDetailDialog(@NonNull Context context, Country country) {
        super(context);
        this.country = country;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_country_detail);

        ImageView flagImage = findViewById(R.id.imageViewDetailFlag);
        TextView nameText = findViewById(R.id.textViewDetailName);
        TextView capitalText = findViewById(R.id.textViewDetailCapital);
        TextView iso2Text = findViewById(R.id.textViewDetailISO2);
        TextView iso3Text = findViewById(R.id.textViewDetailISO3);
        TextView fipsText = findViewById(R.id.textViewDetailFIPS);
        TextView telPrefixText = findViewById(R.id.textViewDetailTelPrefix);

        String flagUrl = "http://www.geognos.com/api/en/countries/flag/" + country.getAlpha2Code() + ".png";
        Glide.with(getContext()).load(flagUrl).into(flagImage);

        nameText.setText("Name: " + country.getName());
        capitalText.setText("Capital: " + country.getCapital());
        iso2Text.setText("ISO 2: " + country.getIso2());
        iso3Text.setText("ISO 3: " + country.getIso3());
        fipsText.setText("FIPS: " + country.getFips());
        telPrefixText.setText("Tel Prefix: " + country.getTelPrefix());
    }
}