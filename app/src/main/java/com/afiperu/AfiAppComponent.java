package com.afiperu;

import android.content.Context;

import com.afiperu.interactor.AttendanceInteractor;
import com.afiperu.interactor.ChangePasswordInteractor;
import com.afiperu.interactor.CommentInteractor;
import com.afiperu.interactor.CommentKidInteractor;
import com.afiperu.interactor.DocumentInteractor;
import com.afiperu.interactor.KidInteractor;
import com.afiperu.interactor.LoginInteractor;
import com.afiperu.interactor.MainActivityInteractor;
import com.afiperu.interactor.NewsInteractor;
import com.afiperu.interactor.PaymentDepositInteractor;
import com.afiperu.interactor.PaymentListInteractor;
import com.afiperu.interactor.PointsOfReunionInteractor;
import com.afiperu.interactor.SessionInteractor;
import com.afiperu.interactor.SettingsInteractor;
import com.afiperu.interactor.UploadPhotoInteractor;
import com.afiperu.interactor.UserInteractor;
import com.afiperu.module.InteractorModule;
import com.afiperu.rest.AfiApiServiceEndPoints;

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
        ChangePasswordInteractor getChangePasswordInteractor();
        PaymentDepositInteractor getPaymentDepositInteractor();
        PaymentListInteractor getPaymentListInteractor();
        SessionInteractor getSessionInteractor();
        PointsOfReunionInteractor getPointsOfReunionInteractor();
        LoginInteractor getLoginInteractor();
        UserInteractor getUserInteractor();
        KidInteractor getKidInteractor();
        MainActivityInteractor getMainActivityInteractor();
        DocumentInteractor getDocumentInteractor();
        AttendanceInteractor getAttendanceInteractor();
        CommentKidInteractor getCommentKidInteractor();
        CommentInteractor getCommentInteractor();
        UploadPhotoInteractor getUploadPhotoInteractor();
        NewsInteractor getNewsInteractor();
        SettingsInteractor getSettingsInteractor();
}
