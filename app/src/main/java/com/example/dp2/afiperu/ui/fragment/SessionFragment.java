package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.ui.adapter.SessionAdapter;
import com.example.dp2.afiperu.domain.Session;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class SessionFragment extends BaseFragment {

    public static final String SESSION_ARG = "session_arg";

    public SessionFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.sessions;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<Session> sessions = (ArrayList<Session>)args.getSerializable(SESSION_ARG);
        SessionAdapter adapter = new SessionAdapter(getContext(), this, sessions);

        ListView newsList = (ListView)rootView.findViewById(R.id.sessions_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_sessions_list));
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

}
