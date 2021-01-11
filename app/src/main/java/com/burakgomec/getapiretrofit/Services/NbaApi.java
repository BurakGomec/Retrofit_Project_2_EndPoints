package com.burakgomec.getapiretrofit.Services;

import com.burakgomec.getapiretrofit.Models.BasketballModels.TeamModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NbaApi {

    @GET("teams?page=0")
    Call<TeamModel> getData(@Header("x-rapidapi-key") String apiKey, @Header("x-rapidapi-host") String host);
    //Header field required
}
