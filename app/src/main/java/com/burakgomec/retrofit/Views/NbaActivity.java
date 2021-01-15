package com.burakgomec.retrofit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.burakgomec.retrofit.Adapters.TeamsListRecyclerAdapter;
import com.burakgomec.retrofit.Models.BasketballModels.Datum;
import com.burakgomec.retrofit.Models.BasketballModels.TeamModel;
import com.burakgomec.retrofit.Models.Client.RetrofitClient;
import com.burakgomec.retrofit.R;
import com.burakgomec.retrofit.Services.NbaApi;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.DisposableLambdaObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NbaActivity extends AppCompatActivity {


    Retrofit retrofit;
    RecyclerView recyclerView;
    List<Datum> data;
    CompositeDisposable compositeDisposable;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba);
        recyclerView = findViewById(R.id.recyclerViewNba);
        progressBar = findViewById(R.id.progressBarNba);

        retrofit = RetrofitClient.getInstanceNba(this);
        compositeDisposable = new CompositeDisposable();
        try {
            pullTeamsList();
        }
        catch (Exception x){
            Toast.makeText(this,x.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void pullTeamsList() {
        NbaApi nbaApi = retrofit.create(NbaApi.class);

        /*
        compositeDisposable.add(nbaApi.getData(""," free-nba.p.rapidapi.com")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillTeamsList));
         */

        compositeDisposable.add(nbaApi.getData("","free-nba.p.rapidapi.com")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<TeamModel>() {
            @Override
            public void onNext(@NonNull TeamModel teamModel) {
                fillTeamsList(teamModel);
                progressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onComplete() {
                // Do somethings
            }
        }));

    }

    private void fillTeamsList(TeamModel teamModel) {
        data = teamModel.data;
        fillRecyclerView();
    }

    private void fillRecyclerView(){
        TeamsListRecyclerAdapter recyclerViewAdapter = new TeamsListRecyclerAdapter(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
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

}