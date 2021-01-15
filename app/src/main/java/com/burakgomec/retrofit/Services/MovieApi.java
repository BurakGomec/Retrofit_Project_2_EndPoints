package com.burakgomec.retrofit.Services;

import com.burakgomec.retrofit.Models.MoviesModel.MoviesModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("json/movies.json")
    Observable<List<MoviesModel>> getData(); //RxJava

    //Call<List<MoviesModel>> getData();  //Call
    //Header field not required

}
