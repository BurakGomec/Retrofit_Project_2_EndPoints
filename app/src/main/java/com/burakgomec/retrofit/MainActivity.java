package com.burakgomec.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.burakgomec.retrofit.Views.MoviesActivity;
import com.burakgomec.retrofit.Views.NbaActivity;

public class MainActivity extends AppCompatActivity {

    //https://free-nba.p.rapidapi.com/teams?page=0
    //Get Request Nba Teams List

    //https://api.androidhive.info/json/movies.json
    //Get Request Movies List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBasketballTeams(View view){
        Intent intentToNbaTeams = new Intent(this, NbaActivity.class);
        startActivity(intentToNbaTeams);
    }

    public void showMoviesList(View view){
        Intent intentToMoviesList = new Intent (this,MoviesActivity.class);
        startActivity(intentToMoviesList);
    }

}