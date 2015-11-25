package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.Payment;
import com.afiperu.presenter.PaymentListPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.syncmodel.SyncPayment;
import com.afiperu.util.Constants;
import com.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nevermade on 17/10/2015.
 */
public class PaymentListInteractorImpl implements PaymentListInteractor {

    AfiApiServiceEndPoints service;

    public PaymentListInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getAllPayments(final PaymentListPresenter presenter, Context context) {
        if(NetworkManager.isNetworkConnected(context)) {
            Call<List<Payment>> call = service.getAllPaymentCalendar();
            call.enqueue(new Callback<List<Payment>>() {
                @Override
                public void onResponse(Response<List<Payment>> response, Retrofit retrofit) {
                    ArrayList<Payment> result = (ArrayList<Payment>) response.body();
                    if(result != null) {
                        SyncPayment.deleteAll(SyncPayment.class);
                        for(Payment payment : result){
                            SyncPayment pay = SyncPayment.fromPayment(payment);
                            pay.save();
                        }

                        List<SyncPayment> payments = SyncPayment.listAll(SyncPayment.class);
                        presenter.onPaymentsFound(payments);
                    }else{
                        presenter.onPaymentsNotFound();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    presenter.onPaymentsNotFound();
                }
            });
        }else{
            List<SyncPayment> payments = SyncPayment.listAll(SyncPayment.class);
            presenter.onPaymentsFound(payments);
        }
    }

    @Override
    public void verifyPayment(String paymentId, String paymentClient, final PaymentListPresenter presenter) {
        Call<Void> call= service.verifyPayment(Constants.PAYMENT_FEE_ID,paymentId,paymentClient);
        call.enqueue(new Callback<Void>(){

            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if(response.errorBody()==null)
                    presenter.onPaymentSuccess();
                else
                    presenter.onPaymentError();
            }

            @Override
            public void onFailure(Throwable t) {
                presenter.onPaymentFailure();
            }
        });
    }
}
