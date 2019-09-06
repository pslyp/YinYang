package com.pslyp.yinyang.services.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient client;
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://pilot.cp.su.ac.th/usr/u07580570/YinYang/";

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if(client == null)
            client = new RetrofitClient();
        return client;
    }

    public Service api() {
        return retrofit.create(Service.class);
    }
}
