package com.burakgomec.getapiretrofit.Services;

import com.burakgomec.getapiretrofit.Models.MoviesModel.MoviesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("json/movies.json")
    Call<List<MoviesModel>> getData();
    //Header field not required

}
