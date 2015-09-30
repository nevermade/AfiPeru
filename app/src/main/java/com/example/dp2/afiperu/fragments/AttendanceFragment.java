package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.AttendanceAdapter;
import com.example.dp2.afiperu.lists.AttendanceItem;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class AttendanceFragment extends BaseFragment{

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
        ArrayList<AttendanceItem> volunteers = (ArrayList<AttendanceItem>)args.getSerializable(ATTENDANCE_ARG);
        AttendanceAdapter adapter = new AttendanceAdapter(getContext(), this, volunteers);

        ListView newsList = (ListView)rootView.findViewById(R.id.attendance_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_attendance_list));
    }

}
