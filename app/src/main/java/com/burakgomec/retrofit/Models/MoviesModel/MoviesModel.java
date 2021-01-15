package com.burakgomec.retrofit.Models.MoviesModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesModel {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("rating")
    @Expose
    public Float rating;
    @SerializedName("releaseYear")
    @Expose
    public Integer releaseYear;
    @SerializedName("genre")
    @Expose
    public List<String> genre = null;

}