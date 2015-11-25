package com.afiperu.module;

import com.afiperu.interactor.PaymentDepositInteractor;
import com.afiperu.presenter.PaymentDepositPresenter;
import com.afiperu.ui.viewmodel.PaymentDepositView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 19/10/2015.
 */
@Module
public class PaymentDepositModule {
    PaymentDepositView view;

    public PaymentDepositModule(PaymentDepositView view) {
        this.view = view;
    }
    @Provides
    public PaymentDepositView providePaymentDepositView(){
        return view;
    }
    @Provides
    public PaymentDepositPresenter providePaymentDepositPresenter(PaymentDepositView view, PaymentDepositInteractor interactor){
        return new PaymentDepositPresenter(view,interactor);
    }


}
