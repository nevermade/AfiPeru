package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.util.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class AfiApiServiceAdapter {
    private static Retrofit API_ADAPTER;

    public static Retrofit getInstance(){
        if(API_ADAPTER==null){
            API_ADAPTER =new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return API_ADAPTER;
    }

}
