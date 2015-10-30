package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.interactor.BlogSearchInteractorImpl;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractorImpl;
import com.example.dp2.afiperu.interactor.DownloadedUserInteractor;
import com.example.dp2.afiperu.interactor.DownloadedUserInteractorImpl;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.interactor.LoginInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractor;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentListInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.interactor.PeriodReportInteractor;
import com.example.dp2.afiperu.interactor.PeriodReportInteractorImpl;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractor;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractorImpl;
import com.example.dp2.afiperu.interactor.SessionInteractor;
import com.example.dp2.afiperu.interactor.SessionInteractorImpl;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
/* Se necesita un interactor por cada presenter */
@Module
public class InteractorModule {
    @Provides
    public BlogSearchInteractor provideSearchInteractor(){
        return new BlogSearchInteractorImpl();
    }
    @Provides
    public ChangePasswordInteractor provideChangePasswordInteractor(AfiApiServiceEndPoints service){
        return new ChangePasswordInteractorImpl(service);
    }
    @Provides
    public PaymentDepositInteractor providePaymentDepositInteractor(){
        return new PaymentDepositInteractorImpl();
    }

    @Provides
    public PaymentListInteractor providePaymentListInteractor(){return new PaymentListInteractorImpl();}

    @Provides
    public PeriodReportInteractor providePeriodReportInteractor(){return new PeriodReportInteractorImpl();}

    @Provides
    public DownloadedUserInteractor provideDownloadedUserInteractor(){return new DownloadedUserInteractorImpl();}

    @Provides
    public SessionInteractor provideSessionInteractor(AfiApiServiceEndPoints service){
        return new SessionInteractorImpl(service);
    }

    @Provides
    public PointsOfReunionInteractor providePointsOfReunionInteractor(AfiApiServiceEndPoints service){
        return new PointsOfReunionInteractorImpl(service);
    }

    @Provides
    public LoginInteractor provideLoginInteractor(AfiApiServiceEndPoints service){
        return new LoginInteractorImpl(service);
    }
}
