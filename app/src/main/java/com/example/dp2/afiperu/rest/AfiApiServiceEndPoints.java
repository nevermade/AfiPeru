package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.rest.restModel.SessionResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AfiApiServiceEndPoints {

    @GET("/api/v1/sessions")
    Call<List<SessionResponse>> getAllSessions();

}
