package com.pslyp.yinyang.services.api;

import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.models.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @FormUrlEncoded
    @POST("login.php")
    Call<Response> signIn(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("getmenu-non.php")
    Call<List<Menu>> getMenu();
}
