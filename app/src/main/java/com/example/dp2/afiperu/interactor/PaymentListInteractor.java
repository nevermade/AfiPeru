package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Nevermade on 17/10/2015.
 */
public interface PaymentListInteractor {
    public ArrayList<Payment> getAllPayments(PaymentListPresenter userPresenter, Context context) throws ParseException;
}
