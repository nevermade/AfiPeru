package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.rest.restModel.LoginResponse;
import com.example.dp2.afiperu.rest.restModel.SessionResponse;
import com.squareup.okhttp.Response;

import java.util.List;

import retrofit.Call;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AfiApiServiceEndPoints {

    @GET("sessions")
    Call<List<SessionResponse>> getAllSessions();

    @FormUrlEncoded
    @POST("sign_in")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @PUT("change_password")
    Call<Response> changePassword(@Field("current_password")String current_password,@Field("new_password")String new_password);

}
