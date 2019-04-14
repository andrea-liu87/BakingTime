package com.andrea.com.bakingtime;

import com.andrea.com.bakingtime.Model.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiCallbackInterface {

    String DOWNLOAD_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    @GET()
    Call<Recipe[]> getData(@Url String url);
}
