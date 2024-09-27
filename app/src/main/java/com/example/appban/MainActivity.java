package com.example.appban;

import android.os.Bundle;

import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridViewCountries;
    private CountryAdapter adapter;
    private CountryApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewCountries = findViewById(R.id.gridViewCountries);
        adapter = new CountryAdapter(this);
        gridViewCountries.setAdapter(adapter);

        setupRetrofit();
        loadCountries();

        gridViewCountries.setOnItemClickListener((parent, view, position, id) -> {
            Country country = adapter.getItem(position);
            showCountryDetails(country);
        });
    }

    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.geognos.com/api/en/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(CountryApiService.class);
    }

    private void loadCountries() {
        Call<CountryResponse> call = apiService.getCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Country> countries = response.body().getCountries();
                    adapter.setCountries(countries);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                // Manejar el error
            }
        });
    }

    private void showCountryDetails(Country country) {
        CountryDetailDialog dialog = new CountryDetailDialog(this, country);
        dialog.show();
    }
}
