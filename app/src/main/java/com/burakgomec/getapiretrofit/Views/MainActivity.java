package com.burakgomec.getapiretrofit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.burakgomec.getapiretrofit.Adapters.RecyclerViewAdapter;
import com.burakgomec.getapiretrofit.Models.Datum;
import com.burakgomec.getapiretrofit.Models.TeamModel;
import com.burakgomec.getapiretrofit.R;
import com.burakgomec.getapiretrofit.Services.NbaApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Datum> data;
    Retrofit retrofit;
    RecyclerView recyclerView;
    //https://free-nba.p.rapidapi.com/teams?page=0
    //Get Request Basketball Teams

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setLenient().create();
        String urlBaseNbaApi = "https://free-nba.p.rapidapi.com/";
        retrofit= new Retrofit.Builder().baseUrl(urlBaseNbaApi)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        pullData();
    }

    private void pullData(){
        NbaApi nbaApi = retrofit.create(NbaApi.class);
        Call<TeamModel> teamModelCall = nbaApi
                .getData("","");
        //https://rapidapi.com/theapiguy/api/free-nba?endpoint=apiendpoint_3621fed2-e280-4bdf-9dd8-6a090b0ad7ac
        //Free apiKey&&hostKey
        teamModelCall.enqueue(new Callback<TeamModel>() {
            @Override
            public void onResponse(Call<TeamModel> call, Response<TeamModel> response) {
                   if(response.isSuccessful() && response.body() != null){
                       data = response.body().data;
                       fillRecyclerView();
                   }
            }
            @Override
            public void onFailure(Call<TeamModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void fillRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}