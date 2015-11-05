package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.NewPointOfReunion;
import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.domain.PaymentCalendar;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;
import retrofit.Response;

import java.util.List;

import retrofit.Call;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface AfiApiServiceEndPoints {
    /*** Document Interactor ***/
    @GET("documents")
    Call<List<Document>> getDocuments();

    @POST("documents/{id}/visualizations")
    Call<Void> recordVisualization(@Path("id") Integer documentId);

    /*** Session Interactor ***/
    @GET("sessions")
    Call<List<Session>> getAllSessions();

    /*** Users Interactor ***/
    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("payment_calendar")
    Call<List<Payment>> getAllPaymentCalendar();

    @GET("children")
    Call<List<Kid>> getAllKids();

    @GET("locations")
    Call<LocationsBody> getLocations();

    /*** Points of Reunion Interactor ***/

    @POST("meeting_points")
    Call<Void> editMeetingPoints(@Body MeetingPointsBody meetingPointsBody);
    /*** Login Interactor ***/
    @FormUrlEncoded
    @POST("sign_in")
    Call<User> login(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("recover_password")
    Call<Void> recoverPass(@Field("email")String email);

    /*** ChangePassword Interactor ***/
    @FormUrlEncoded
    @PUT("change_password")
    Call<Void> changePassword(@Field("current_password")String current_password,@Field("new_password")String new_password);

    /*** MainActivityInteractor ***/
    @FormUrlEncoded
    @PUT("reapply")
    Call<Void> applyForPeriod(@Field("period_id")int periodId);



}
