package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.syncmodel.SyncPayment;
import com.example.dp2.afiperu.ui.viewmodel.PaymentListView;
import com.example.dp2.afiperu.util.Constants;

import java.util.List;

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

    public void verifyPaymentOnServer(String paymendId, String paymentClient){
        Constants.PROGRESS.setMessage("Verificando pago");
        interactor.verifyPayment(paymendId,paymentClient,this);
    }

    public void onPaymentSuccess(){
        view.displayPaymentSuccess();
    }

    public void onPaymentFailure(){
        view.displayPaymentFailure();
    }

    public void onPaymentError(){
        view.displayPaymentError();
    }

    public void getAllPayments(Context context){
        interactor.getAllPayments(this, context);
    }

    public void onPaymentsFound(List<SyncPayment> payments){
        view.displayPayments(payments);
    }

    public void onPaymentsNotFound(){
        view.displayNoPaymentsFound();
    }

}
