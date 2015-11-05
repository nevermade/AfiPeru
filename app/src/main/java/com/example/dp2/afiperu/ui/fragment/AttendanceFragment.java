package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerAttendanceComponent;
import com.example.dp2.afiperu.module.AttendanceModule;
import com.example.dp2.afiperu.presenter.AttendancePresenter;
import com.example.dp2.afiperu.rest.model.AttendanceVolunteer;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.adapter.AttendanceAdapter;
import com.example.dp2.afiperu.domain.Attendance;
import com.example.dp2.afiperu.ui.viewmodel.AttendanceView;

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

    private boolean saved = true;

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
        ArrayList<AttendanceVolunteer> volunteers = (ArrayList<AttendanceVolunteer>)args.getSerializable(ATTENDANCE_ARG);
        AttendanceAdapter adapter = new AttendanceAdapter(getContext(), this, volunteers);

        ListView newsList = (ListView)rootView.findViewById(R.id.attendance_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_attendance_list));
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


    public void save(int sessionId, ArrayList<AttendanceVolunteer> av){

        presenter.editAttendance(sessionId, av);
    }

    public void trySave(int sessionId, ArrayList<AttendanceVolunteer> av){
        if(!saved){
            save(sessionId,av);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.save_not_needed).setNeutralButton(android.R.string.ok, null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean tryBack(){


        if(!saved){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        ((DetailActivity)getContext()).goBack();
                    }else if(which == DialogInterface.BUTTON_NEGATIVE){
                        ((DetailActivity)getContext()).goBack();
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.save_needed)
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
