package com.burakgomec.retrofit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.burakgomec.retrofit.Adapters.MoviesListRecyclerAdapter;
import com.burakgomec.retrofit.Models.Client.RetrofitClient;
import com.burakgomec.retrofit.Models.MoviesModel.MoviesModel;
import com.burakgomec.retrofit.R;
import com.burakgomec.retrofit.Services.MovieApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MoviesActivity extends AppCompatActivity {

    Retrofit retrofit;
    ArrayList<MoviesModel> moviesList;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.recyclerViewMoviesList);
        progressBar = findViewById(R.id.progressBarMovies);

        retrofit = RetrofitClient.getInstanceMovies(this); //Singleton

        try {
            pullMoviesList();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void fillMoviesList(List<MoviesModel> moviesModelList){
        moviesList = new ArrayList<>(moviesModelList);
        fillRecyclerAdapter();
    }

    private void pullMoviesList(){
        MovieApi movieApi = retrofit.create(MovieApi.class);
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(movieApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<MoviesModel>>() {
                    @Override
                    public void onNext(@NonNull List<MoviesModel> moviesModels) {

                        fillMoviesList(moviesModels);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onComplete() {
                        // Do somethings
                    }
                }));

        /* Used Call
        Call<List<MoviesModel>> moviesModelCall = movieApi.getData();
        moviesModelCall.enqueue(new Callback<List<MoviesModel>>() {
            @Override
            public void onResponse(Call<List<MoviesModel>> call, Response<List<MoviesModel>> response) {
                if(response.isSuccessful() && response.body() != null){
                    moviesList = (ArrayList<MoviesModel>) response.body();
                    fillRecyclerAdapter();
                }
            }
            @Override
            public void onFailure(Call<List<MoviesModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
         */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    private void fillRecyclerAdapter(){
        MoviesListRecyclerAdapter teamsListRecyclerAdapter = new MoviesListRecyclerAdapter(moviesList,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(teamsListRecyclerAdapter);
    }
}