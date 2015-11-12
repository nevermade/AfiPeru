package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.PaymentDepositPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.util.NetworkManager;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 19/10/2015.
 */
public class PaymentDepositInteractorImpl implements PaymentDepositInteractor {

    AfiApiServiceEndPoints service;

    public PaymentDepositInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void registerBankPayment(final Context context, final PaymentDepositPresenter presenter,
                                    int feeId, String voucherCode, long date){
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Void> call = service.registerBankPayment(String.valueOf(feeId), voucherCode, (double)(date/1000));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Response<Void> response, Retrofit retrofit) {
                    if(response.body() != null){
                        presenter.onPaymentSuccess();
                    }else{
                        presenter.onPaymentFailure(context);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onNoInternet(context);
                }
            });
        } else{
            presenter.onNoInternet(context);
        }
    }
}
