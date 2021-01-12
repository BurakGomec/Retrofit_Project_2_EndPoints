package com.burakgomec.getapiretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.burakgomec.getapiretrofit.Adapters.MoviesListRecyclerAdapter;
import com.burakgomec.getapiretrofit.Models.MoviesModel.MoviesModel;
import com.burakgomec.getapiretrofit.Services.MovieApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {
    Retrofit retrofit;
    ArrayList<MoviesModel> moviesList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.recyclerViewMoviesList);
        Gson gson = new GsonBuilder().setLenient().create();
        String urlBaseMovieApi = "https://api.androidhive.info/";
        retrofit = new Retrofit.Builder().baseUrl(urlBaseMovieApi).
                addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        pullMoviesList();
    }
    private void pullMoviesList(){
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<List<MoviesModel>> moviesModelCall = movieApi.getData();
        moviesModelCall.enqueue(new Callback<List<MoviesModel>>() {
            @Override
            public void onResponse(Call<List<MoviesModel>> call, Response<List<MoviesModel>> response) {
                if(response.isSuccessful() && response.body() != null){
                    System.out.println(response.body());
                    moviesList = (ArrayList<MoviesModel>) response.body();
                    for(MoviesModel moviesModel : moviesList){
                        System.out.println(moviesModel.releaseYear);
                    }
                    fillRecyclerAdapter();
                }
            }
            @Override
            public void onFailure(Call<List<MoviesModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fillRecyclerAdapter(){
        MoviesListRecyclerAdapter teamsListRecyclerAdapter = new MoviesListRecyclerAdapter(moviesList,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(teamsListRecyclerAdapter);

    }
}