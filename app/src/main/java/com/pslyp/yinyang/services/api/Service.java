package com.pslyp.yinyang.services.api;

import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.models.Response;
import com.pslyp.yinyang.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @FormUrlEncoded
    @POST("register.php")
    Call<Response> signUp(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("picture") String picture
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Response> signIn(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("getmenu-non.php")
    Call<List<Menu>> getMenu();

    @FormUrlEncoded
    @POST("update_favorite.php")
    Call<Response> updateFavorite(
            @Field("id") String id,
            @Field("favorite") String favorite
    );

    @FormUrlEncoded
    @POST("get_profile.php")
    Call<User> getProfile(
            @Field("email") String email
    );
}
