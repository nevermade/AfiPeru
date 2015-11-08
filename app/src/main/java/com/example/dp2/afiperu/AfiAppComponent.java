package com.example.dp2.afiperu;

import android.content.Context;

import com.example.dp2.afiperu.interactor.AttendanceInteractor;
import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.interactor.CommentInteractor;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
import com.example.dp2.afiperu.interactor.DocumentInteractor;
import com.example.dp2.afiperu.interactor.KidInteractor;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.interactor.MainActivityInteractor;
import com.example.dp2.afiperu.interactor.PaymentDepositInteractor;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.interactor.PeriodReportInteractor;
import com.example.dp2.afiperu.interactor.PointsOfReunionInteractor;
import com.example.dp2.afiperu.interactor.SessionInteractor;
import com.example.dp2.afiperu.interactor.UserInteractor;
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
}
