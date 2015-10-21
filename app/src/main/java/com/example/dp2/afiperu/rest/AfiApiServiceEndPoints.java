package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.domain.Session;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AfiApiServiceEndPoints {

    @GET("/sessions")
    Call<List<Session>> groupList();

}
