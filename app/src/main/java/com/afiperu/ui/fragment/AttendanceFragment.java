package com.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerAttendanceComponent;
import com.afiperu.module.AttendanceModule;
import com.afiperu.presenter.AttendancePresenter;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.ui.activity.DetailActivity;
import com.afiperu.ui.adapter.AttendanceAdapter;
import com.afiperu.ui.viewmodel.AttendanceView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class AttendanceFragment extends BaseFragment implements AttendanceView {

    @Inject
    AttendancePresenter presenter;
    @Inject
    AttendanceAdapter adapter;

    private int sessionId;
    private boolean saved = true;
    private ArrayList<SyncAttendanceVolunteer> volunteers;

    public static final String SESSION_ID_ARG = "session_id_arg";
    public static final String ATTENDANCE_ARG = "attendance_arg";

    public AttendanceFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.attendance;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView newsList = (ListView)rootView.findViewById(R.id.attendance_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_attendance_list));
        sessionId = args.getInt(SESSION_ID_ARG);
        volunteers = (ArrayList<SyncAttendanceVolunteer>)args.getSerializable(ATTENDANCE_ARG);
        adapter.update(volunteers);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerAttendanceComponent.builder()
                .afiAppComponent(appComponent)
                .attendanceModule(new AttendanceModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void saveSuccessful() {
        Toast.makeText(getContext(), getResources().getString(R.string.save_success), Toast.LENGTH_SHORT).show();
        ((DetailActivity)getActivity()).goBack();
    }

    @Override
    public void saveFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.save_failed).setNeutralButton(android.R.string.ok, null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void save(){
        presenter.editAttendance(getContext(), sessionId, volunteers);
    }

    public void trySave(){
        if(!saved){
            save();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.save_not_needed).setNeutralButton(android.R.string.ok, null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void edited(){
        saved = false;
    }

    @Override
    public boolean tryBack(){
        if(!saved){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        save();
                    }else if(which == DialogInterface.BUTTON_NEGATIVE){
                        ((DetailActivity)getContext()).goBack();
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.save_needed_attendance)
                    .setNeutralButton(R.string.save_needed_cancel, dialogClickListener)
                    .setPositiveButton(R.string.save_needed_save, dialogClickListener)
                    .setNegativeButton(R.string.save_needed_exit, dialogClickListener);
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }else {
            return true;
        }
    }














}
