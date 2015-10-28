package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.domain.User;
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
    Call<List<Session>> getAllSessions();

    @FormUrlEncoded
    @POST("sign_in")
    Call<User> login(@Field("username") String username, @Field("password") String password);

    /*** Usar Response como respuesta cuando no nos interesa que devuelve el WS***/
    @FormUrlEncoded
    @PUT("change_password")
    Call<Response> changePassword(@Field("current_password")String current_password,@Field("new_password")String new_password);

}