package com.example.dp2.afiperu;

import android.content.Context;

import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.interactor.DownloadedUserInteractor;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractor;
import com.example.dp2.afiperu.interactor.PeriodReportInteractor;
import com.example.dp2.afiperu.interactor.SessionInteractor;
import com.example.dp2.afiperu.module.InteractorModule;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nevermade on 11/10/2015.
 */
@Singleton
@Component(
        modules = {
                AfiAppModule.class,
                InteractorModule.class
        }
)
public interface AfiAppComponent {
        /*Agregar los interactors aqui*/
        Context getContext();
        AfiApiServiceEndPoints getService();
        BlogSearchInteractor getBlogSearchInteractor();
        ChangePasswordInteractor getChangePasswordInteractor();
        PaymentDepositInteractor getPaymentDepositInteractor();
        PaymentListInteractor getPaymentListInteractor();
        PeriodReportInteractor getPeriodReportInteractor();
        DownloadedUserInteractor getDownloadedUserInteractor();
        SessionInteractor getSessionInteractor();
        LoginInteractor getLoginInteractor();
}
