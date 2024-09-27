package com.example.appban;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApiService {
    @GET("countries/info/all.json")
    Call<CountryResponse> getCountries();
}

