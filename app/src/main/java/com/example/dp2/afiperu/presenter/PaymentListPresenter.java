package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.ui.viewmodel.PaymentListView;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Nevermade on 17/10/2015.
 */
public class PaymentListPresenter extends BasePresenter {

    PaymentListView view;
    PaymentListInteractor interactor;

    public PaymentListPresenter(PaymentListView view, PaymentListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public ArrayList<Payment> getAllPayments(){
        ArrayList<Payment> payments=null;

        try {
            payments= interactor.getAllPayments();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(payments!=null)
            view.displayPayments(payments);

        return payments;
    }
}
