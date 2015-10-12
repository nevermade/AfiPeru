package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.adapter.AttendanceAdapter;
import com.example.dp2.afiperu.domain.Attendance;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class AttendanceFragment extends BaseFragment {

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
        ArrayList<Attendance> volunteers = (ArrayList<Attendance>)args.getSerializable(ATTENDANCE_ARG);
        AttendanceAdapter adapter = new AttendanceAdapter(getContext(), this, volunteers);

        ListView newsList = (ListView)rootView.findViewById(R.id.attendance_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_attendance_list));
    }

}
