package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.ui.adapter.ViewCommentsAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class ViewCommentsFragment extends BaseFragment {

    public static final String KIDS_ARG = "kids_arg";

    @Override
    public int getLayout(){
        return R.layout.kids2;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<AttendanceChild> kids = (ArrayList<AttendanceChild>)args.getSerializable(KIDS_ARG);
        ViewCommentsAdapter adapter = new ViewCommentsAdapter(getContext(), this, kids);

        ListView newsList = (ListView)rootView.findViewById(R.id.kids2_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty2_kids_list));
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }


}
