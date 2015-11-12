package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.AttendanceInteractor;
import com.example.dp2.afiperu.interactor.AttendanceInteractorImpl;
import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.interactor.BlogSearchInteractorImpl;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractorImpl;
import com.example.dp2.afiperu.interactor.CommentInteractor;
import com.example.dp2.afiperu.interactor.CommentInteractorImpl;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
import com.example.dp2.afiperu.interactor.CommentKidInteractorImpl;
import com.example.dp2.afiperu.interactor.DocumentInteractor;
import com.example.dp2.afiperu.interactor.DocumentInteractorImpl;
import com.example.dp2.afiperu.interactor.KidInteractor;
import com.example.dp2.afiperu.interactor.KidInteractorImpl;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.interactor.LoginInteractorImpl;
import com.example.dp2.afiperu.interactor.MainActivityInteractor;
import com.example.dp2.afiperu.interactor.MainActivityInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractor;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.interactor.PaymentListInteractorImpl;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractor;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractorImpl;
import com.example.dp2.afiperu.interactor.SessionInteractor;
import com.example.dp2.afiperu.interactor.SessionInteractorImpl;
import com.example.dp2.afiperu.interactor.UserInteractor;
import com.example.dp2.afiperu.interactor.UserInteractorImpl;
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
    public PaymentListInteractor providePaymentListInteractor(AfiApiServiceEndPoints service){return new PaymentListInteractorImpl(service);}

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


    @Provides
    public UserInteractor provideUserInteractor(AfiApiServiceEndPoints service){
        return new UserInteractorImpl(service);
    }
    @Provides
    public KidInteractor provideKidInteractor(AfiApiServiceEndPoints service){
        return new KidInteractorImpl(service);
    }

    @Provides
    public MainActivityInteractor provideMainActivityInteractor(AfiApiServiceEndPoints service){
        return new MainActivityInteractorImpl(service);
    }

    @Provides
    public DocumentInteractor provideDocumentInteractor(AfiApiServiceEndPoints service){
        return new DocumentInteractorImpl(service);
    }

    @Provides
    public AttendanceInteractor provideAttendanceInteractor(AfiApiServiceEndPoints service){
        return new AttendanceInteractorImpl(service);
    }

    @Provides
    public CommentKidInteractor provideCommentKidInteractor(AfiApiServiceEndPoints service){
        return new CommentKidInteractorImpl(service);
    }

    @Provides
    public CommentInteractor provideCommentInteractor(AfiApiServiceEndPoints service){
        return new CommentInteractorImpl(service);
    }
}
