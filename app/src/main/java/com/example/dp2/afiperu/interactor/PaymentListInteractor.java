package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.PaymentListPresenter;

/**
 * Created by Nevermade on 17/10/2015.
 */
public interface PaymentListInteractor {
    public void getAllPayments(PaymentListPresenter userPresenter, Context context);
}
