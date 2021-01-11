package com.burakgomec.getapiretrofit.Models.BasketballModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("abbreviation")
    @Expose
    public String abbreviation;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("conference")
    @Expose
    public String conference;
    @SerializedName("division")
    @Expose
    public String division;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("name")
    @Expose
    public String name;
}
