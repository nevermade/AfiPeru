package com.example.dp2.afiperu.presenter;

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
}
