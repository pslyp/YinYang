package com.pslyp.yinyang.services.api;

import com.pslyp.yinyang.models.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @GET("getmenu-non.php")
    Call<List<Menu>> getMenu();
}
