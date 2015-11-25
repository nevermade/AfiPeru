package com.afiperu.module;

import com.afiperu.interactor.AttendanceInteractor;
import com.afiperu.interactor.AttendanceInteractorImpl;
import com.afiperu.interactor.ChangePasswordInteractor;
import com.afiperu.interactor.ChangePasswordInteractorImpl;
import com.afiperu.interactor.CommentInteractor;
import com.afiperu.interactor.CommentInteractorImpl;
import com.afiperu.interactor.CommentKidInteractor;
import com.afiperu.interactor.CommentKidInteractorImpl;
import com.afiperu.interactor.DocumentInteractor;
import com.afiperu.interactor.DocumentInteractorImpl;
import com.afiperu.interactor.KidInteractor;
import com.afiperu.interactor.KidInteractorImpl;
import com.afiperu.interactor.LoginInteractor;
import com.afiperu.interactor.LoginInteractorImpl;
import com.afiperu.interactor.MainActivityInteractor;
import com.afiperu.interactor.MainActivityInteractorImpl;
import com.afiperu.interactor.NewsInteractor;
import com.afiperu.interactor.NewsInteractorImpl;
import com.afiperu.interactor.PaymentDepositInteractor;
import com.afiperu.interactor.PaymentDepositInteractorImpl;
import com.afiperu.interactor.PaymentListInteractor;
import com.afiperu.interactor.PaymentListInteractorImpl;
import com.afiperu.interactor.PointsOfReunionInteractor;
import com.afiperu.interactor.PointsOfReunionInteractorImpl;
import com.afiperu.interactor.SessionInteractor;
import com.afiperu.interactor.SessionInteractorImpl;
import com.afiperu.interactor.SettingsInteractor;
import com.afiperu.interactor.SettingsInteractorImpl;
import com.afiperu.interactor.UploadPhotoInteractor;
import com.afiperu.interactor.UploadPhotoInteractorImpl;
import com.afiperu.interactor.UserInteractor;
import com.afiperu.interactor.UserInteractorImpl;
import com.afiperu.rest.AfiApiServiceEndPoints;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
/* Se necesita un interactor por cada presenter */
@Module
public class InteractorModule {
    @Provides
    public ChangePasswordInteractor provideChangePasswordInteractor(AfiApiServiceEndPoints service){
        return new ChangePasswordInteractorImpl(service);
    }
    @Provides
    public PaymentDepositInteractor providePaymentDepositInteractor(AfiApiServiceEndPoints service){
        return new PaymentDepositInteractorImpl(service);
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

    @Provides
    public UploadPhotoInteractor provideUploadPhotoInteractor(AfiApiServiceEndPoints service){
        return new UploadPhotoInteractorImpl(service);
    }

    @Provides
    public NewsInteractor provideNewsInteractor(AfiApiServiceEndPoints service){
        return new NewsInteractorImpl(service);
    }

    @Provides
    public SettingsInteractor provideSettingsInteractor(AfiApiServiceEndPoints service){
        return new SettingsInteractorImpl(service);
    }
}
