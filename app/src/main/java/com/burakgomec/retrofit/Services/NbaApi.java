package com.burakgomec.retrofit.Services;

import com.burakgomec.retrofit.Models.BasketballModels.TeamModel;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NbaApi {

    @GET("teams?page=0")
    Observable<TeamModel> getData(@Header("x-rapidapi-key") String apiKey, @Header("x-rapidapi-host") String host);


    //Call<TeamModel> getData(@Header("x-rapidapi-key") String apiKey, @Header("x-rapidapi-host") String host);
    //Header field required
}
