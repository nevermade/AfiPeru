package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.ui.adapter.ViewCommentsAdapter;

import java.util.ArrayList;

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
        ArrayList<SyncAttendanceChild> kids = (ArrayList<SyncAttendanceChild>)args.getSerializable(KIDS_ARG);
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
