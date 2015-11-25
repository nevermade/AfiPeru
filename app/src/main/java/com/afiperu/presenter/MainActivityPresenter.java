package com.afiperu.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.afiperu.R;
import com.afiperu.common.BasePresenter;
import com.afiperu.domain.User;
import com.afiperu.interactor.AttendanceInteractor;
import com.afiperu.interactor.CommentKidInteractor;
import com.afiperu.interactor.MainActivityInteractor;
import com.afiperu.interactor.SettingsInteractor;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.ui.viewmodel.MainActivityView;
import com.afiperu.util.AppEnum;
import com.afiperu.util.Constants;

import java.util.List;

/**
 * Created by DABARCA on 03/11/2015.
 */
public class MainActivityPresenter extends BasePresenter {
    MainActivityView view;
    MainActivityInteractor interactor;
    CommentKidInteractor commentInteractor;
    AttendanceInteractor attendanceInteractor;
    SettingsInteractor settingsInteractor;

    public MainActivityPresenter(MainActivityView view, MainActivityInteractor interactor, CommentKidInteractor commentInteractor,
                                 AttendanceInteractor attendanceInteractor, SettingsInteractor settingsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.commentInteractor = commentInteractor;
        this.attendanceInteractor = attendanceInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    public MainActivityView getView() {
        return view;
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public void apply(int idPeriod){
        interactor.applyForPeriod(idPeriod,this);
    }

    public void onApplied(int idResponse, String message){
        if(idResponse== AppEnum.ResponseStatus.SUCCESS.ordinal()){
            view.displayApplySuccessMessage();
            view.removeApplyOption();
        }else {
            if(message == null){
                message = "Hubo un error al postular";
            }
            view.displayApplyFailureMessage(message);
        }
    }

    public void validateUser(Context context, String username, String password){
        interactor.validateUser(context, username, password, this);
    }

    public void onUserValidateSuccess(User user, String username, String password){
        Constants.loggedUser = user;
        Constants.loggedUser.setUsername(username);
        Constants.loggedUser.setPassword(password);
        view.saveUserToSharedPreferences();
        view.synchronize();
        //view.selectItem(DetailActivity.FRAGMENT_NOTICIAS);
    }

    public void onUserValidateFailure(Context context){
        view.logOff();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.cant_verify_user).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onUserCantValidate(){
        view.loadUserFromSharedPreferences();
        //view.selectItem(DetailActivity.FRAGMENT_NOTICIAS);
    }

    public void makeComment(Context context, SyncAttendanceChild child, SyncComment comment){
        commentInteractor.makeComment(context, child, comment);
    }

    public void editAttendance(Context context, int sessionId, List<SyncAttendanceVolunteer> attendanceVolunteers){
        attendanceInteractor.editAttendance(context, null, sessionId, attendanceVolunteers);
    }

    public void setGCMToken(Context context, String GCMToken){
        interactor.setGCMToken(context, GCMToken);
    }

    public void clearGCMToken(Context context){
        interactor.clearGCMToken(context);
    }

    public void getCurrecyRate(Context context){
        interactor.getCurrencyRate(context);
    }

    public void setPushSettings(Context context, User user){
        settingsInteractor.setPushSettings(context, user);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
