package com.example.appban;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryResponse {
    @SerializedName("Results")
    private Map<String, Country> countryMap;

    public List<Country> getCountries() {
        return new ArrayList<>(countryMap.values());
    }
}
