package com.burakgomec.getapiretrofit.Models.BasketballModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamModel {
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("meta")
    @Expose
    public Meta meta;
}
