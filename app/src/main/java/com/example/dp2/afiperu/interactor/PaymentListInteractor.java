package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.PaymentListPresenter;

/**
 * Created by Nevermade on 17/10/2015.
 */
public interface PaymentListInteractor {
    void getAllPayments(PaymentListPresenter userPresenter, Context context);
    void verifyPayment(String paymentId,String paymentClient, PaymentListPresenter presenter);
}
