package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.syncmodel.SyncPayment;
import com.example.dp2.afiperu.util.NetworkManager;

import java.text.ParseException;
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
}
