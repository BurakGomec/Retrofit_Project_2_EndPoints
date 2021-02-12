package com.burakgomec.retrofit.Models.Client;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //https://free-nba.p.rapidapi.com/teams?page=0
    //Get Request Nba Teams List

    //https://api.androidhive.info/json/movies.json
    //Get Request Movies List

    private static Retrofit retrofitNba;
    private static Retrofit retrofitMovies;
    private static final String baseUrlNbaTeams = "https://free-nba.p.rapidapi.com/";
    private static final String baseUrlMovieList = "https://api.androidhive.info/";


    public static Retrofit getInstanceNba(Context context){
        if(retrofitNba == null){
            try{
                retrofitNba = new Retrofit.Builder()
                        .baseUrl(baseUrlNbaTeams)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            catch (Exception e){
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
        return retrofitNba;
    }

    public static Retrofit getInstanceMovies(Context context){
        if(retrofitMovies == null){
            try {
                retrofitMovies = new Retrofit.Builder()
                        .baseUrl(baseUrlMovieList)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            catch (Exception e){
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
            }

        }
        return retrofitMovies;
    }
    private RetrofitClient(){ }
}
