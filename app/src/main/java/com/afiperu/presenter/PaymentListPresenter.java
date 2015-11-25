package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.PaymentListInteractor;
import com.afiperu.syncmodel.SyncPayment;
import com.afiperu.ui.viewmodel.PaymentListView;
import com.afiperu.util.Constants;

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
