package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerSessionComponent;
import com.example.dp2.afiperu.module.SessionModule;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.ui.adapter.SessionAdapter;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.ui.viewmodel.SessionView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class SessionFragment extends BaseFragment implements SessionView{

    @Inject
    SessionPresenter presenter;
    @Inject
    SessionAdapter adapter;

    //public static final String SESSION_ARG = "session_arg";

    public SessionFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.sessions;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        //ArrayList<SessionResponse> sessions = (ArrayList<SessionResponse>)args.getSerializable(SESSION_ARG);

        ListView newsList = (ListView)rootView.findViewById(R.id.sessions_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_sessions_list));

        presenter.getAllSessions();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerSessionComponent.builder()
                .afiAppComponent(appComponent)
                .sessionModule(new SessionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void displaySessions(ArrayList<Session> sessions) {
        adapter.updateSessions(sessions);
    }
}
