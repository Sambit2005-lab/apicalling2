package com.example.apicalling2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiinterface {
    @GET("posts")
    Call<List<postpojo>> getposts();

}

