package com.soulsoft.globalrestobar.services;

import com.soulsoft.globalrestobar.model.getcaptain.GetCaptainResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface GlobalRestoBarServices {

    @POST("SYS_CAPTAINLIST")
    Call<GetCaptainResponse> getAllCaptain();

    /*@FormUrlEncoded
    @POST("authentication.php")
    Call<LoginResponse> login(@FieldMap Map<String, String> params);

    @GET("getAllTest.php")
    Call<GetAllTestResponse> getAllTest();*/
}
