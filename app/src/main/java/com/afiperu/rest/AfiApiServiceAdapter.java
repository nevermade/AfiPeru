package com.afiperu.rest;

import android.util.Log;

import com.afiperu.util.Constants;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class AfiApiServiceAdapter {
    private static Retrofit API_ADAPTER;

    public static Retrofit getInstance(){
        if(API_ADAPTER==null){
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", Constants.TOKEN).build();
                    Log.d("Token", Constants.TOKEN);
                    return chain.proceed(newRequest);
                }
            };

// Add the interceptor to OkHttpClient
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(interceptor);

            API_ADAPTER =new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return API_ADAPTER;
    }

}
