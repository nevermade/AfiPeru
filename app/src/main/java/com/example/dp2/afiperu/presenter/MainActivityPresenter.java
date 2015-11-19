package com.example.dp2.afiperu.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.AttendanceInteractor;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
import com.example.dp2.afiperu.interactor.MainActivityInteractor;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.viewmodel.MainActivityView;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;

import java.util.List;

/**
 * Created by DABARCA on 03/11/2015.
 */
public class MainActivityPresenter extends BasePresenter {
    MainActivityView view;
    MainActivityInteractor interactor;
    CommentKidInteractor commentInteractor;
    AttendanceInteractor attendanceInteractor;

    public MainActivityPresenter(MainActivityView view, MainActivityInteractor interactor, CommentKidInteractor commentInteractor,
                                 AttendanceInteractor attendanceInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.commentInteractor = commentInteractor;
        this.attendanceInteractor = attendanceInteractor;
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

    public void onApplied(int idResponse){
        if(idResponse== AppEnum.ResponseStatus.SUCCESS.ordinal()){
            view.displayApplySuccessMessage();
            view.removeApplyOption();
        }else {
            view.displayApplyFailureMessage();
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

    public void clearGCMToken(Context context){
        interactor.clearGCMToken(context);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
