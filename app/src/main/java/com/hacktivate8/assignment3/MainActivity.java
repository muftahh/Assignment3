package com.hacktivate8.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView tvtype;
    private TextView tvcasses;
    private TextView tvrecovered;
    private TextView tvdeaths;

    List<Covid> covidList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvtype = findViewById(R.id.tvtype);
        tvcasses = findViewById(R.id.tvcasses);
        tvrecovered = findViewById(R.id.tvrecovered);
        tvdeaths = findViewById(R.id.tvdeaths);

        getCovidInfo();
    }

    private void getCovidInfo(){
        Call<List<Covid>> callcovid = RetrofitClient.getInstance().covidApi().getCovidInfo();
        callcovid.enqueue(new Callback<List<Covid>>() {
            @Override
            public void onResponse(Call<List<Covid>> call, Response<List<Covid>> response) {
                covidList = response.body();

                tvtype.setText(covidList.get(0).getType());
                tvcasses.setText(covidList.get(0).getCasses());
                tvrecovered.setText(covidList.get(0).getRecovered());
                tvdeaths.setText(covidList.get(0).getDeaths());
            }

            @Override
            public void onFailure(Call<List<Covid>> call, Throwable t) {

            }
        });
    }
}