package com.example.dp2.afiperu.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractor;
import com.example.dp2.afiperu.ui.viewmodel.PaymentDepositView;

/**
 * Created by DABARCA on 19/10/2015.
 */
public class PaymentDepositPresenter extends BasePresenter {

    PaymentDepositInteractor interactor;
    PaymentDepositView view;

    public PaymentDepositPresenter(PaymentDepositView view,PaymentDepositInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void registerBankPayment(Context context, int feeId, String voucherCode, long date){
        interactor.registerBankPayment(context, this, feeId, voucherCode, date);
    }

    public void onPaymentSuccess(){
        view.onPaymentSuccess();
    }

    public void onPaymentFailure(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.payment_failure).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
