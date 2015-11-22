package com.example.dp2.afiperu.rest;

import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.News;
import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.rest.model.AttendanceBody;
import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.rest.model.MeetingPointsBody;
import com.example.dp2.afiperu.rest.model.SuccessBody;
import com.squareup.okhttp.RequestBody;

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
import retrofit.http.Path;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface  AfiApiServiceEndPoints {
    /*** Photo Interactor ***/
    @Multipart
    @POST("afiperudrupal/?q=photoupload")
    Call<SuccessBody> uploadPhoto(@Part("image\"; filename=\"1.jpg\"") RequestBody photo);

    /*** News Interactor ***/
    @GET("afiperudrupal/api/v1/articles")
    Call<List<News>> getAllNews();

    /*** Document Interactor ***/
    @GET("afiperularavel/public/api/v1/documents")
    Call<List<Document>> getDocuments();

    @GET("afiperularavel/public/api/v1/activity_reports")
    Call<List<Document>> getPeriodReports();

    @POST("afiperularavel/public/api/v1/documents/{id}/visualizations")
    Call<Void> recordVisualization(@Path("id") Integer documentId);

    /*** Session Interactor ***/
    @GET("afiperularavel/public/api/v1/sessions")
    Call<List<Session>> getAllSessions();

    /***Comment Interactor ***/
    @GET("afiperularavel/public/api/v1/children/{id}")
    Call<Kid> getKidAndComments(@Path("id") Integer kidId);

    /*** CommentKid Interactor ***/
    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/attendance_children/{id}/comments")
    Call<Void> makeComment(@Path("id") Integer kidId, @Field("message") String message, @Field("face") Integer face);

    /*** Users Interactor ***/
    @GET("afiperularavel/public/api/v1/users")
    Call<List<User>> getAllUsers();

    @GET("afiperularavel/public/api/v1/children")
    Call<List<Kid>> getAllKids();

    @GET("afiperularavel/public/api/v1/locations")
    Call<LocationsBody> getLocations();

    /*** Payment List Interactor ***/
    @GET("afiperularavel/public/api/v1/payment_calendar")
    Call<List<Payment>> getAllPaymentCalendar();

    /*** Payment Deposit Interactor ***/
    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/payment")
    Call<Void> registerBankPayment(@Field("fee_id") String feeId, @Field("voucher_code") String voucherCode,
                                   @Field("date") Double date, @Field("bank") String bank);


    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/verify_payment")
    Call<Void> verifyPayment(@Field("fee_id")int feeId,@Field("payment_id")String paymentId,@Field("payment_client") String paymentClient);

    @GET("http://download.finance.yahoo.com/d/quotes.csv?s=USDPEN=X&f=l1")
    Call<Double> checkCurrencyRate();
    /*** Points of Reunion Interactor ***/

    @POST("afiperularavel/public/api/v1/meeting_points")
    Call<Void> editMeetingPoints(@Body MeetingPointsBody meetingPointsBody);

    /* Attendance */
    @POST("afiperularavel/public/api/v1/roll_call")
    Call<Void> editAttendance(@Body AttendanceBody attendanceBody);

    /*** Login Interactor ***/
    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/sign_in")
    Call<User> login(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/recover_password")
    Call<Void> recoverPass(@Field("email")String email);
    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/set_gcm_token")
    Call<SuccessBody> setGCM(@Field("gcm_token")String token);
    @POST("afiperularavel/public/api/v1/clear_gcm_token")
    Call<SuccessBody> clearGCM();

    /*** ChangePassword Interactor ***/
    @FormUrlEncoded
    @PUT("afiperularavel/public/api/v1/change_password")
    Call<Void> changePassword(@Field("current_password")String current_password,@Field("new_password")String new_password);

    /*** MainActivityInteractor ***/
    @FormUrlEncoded
    @POST("afiperularavel/public/api/v1/reapply")
    Call<Void> applyForPeriod(@Field("period_id")int periodId);

}
