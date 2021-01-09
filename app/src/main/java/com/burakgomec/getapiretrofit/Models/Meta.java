package com.burakgomec.getapiretrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("current_page")
    @Expose
    public Integer currentPage;
    @SerializedName("next_page")
    @Expose
    public Object nextPage;
    @SerializedName("per_page")
    @Expose
    public Integer perPage;
    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
}
