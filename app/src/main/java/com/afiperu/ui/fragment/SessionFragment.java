package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerSessionComponent;
import com.afiperu.module.SessionModule;
import com.afiperu.presenter.SessionPresenter;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.ui.activity.DetailActivity;
import com.afiperu.ui.adapter.SessionAdapter;
import com.afiperu.ui.viewmodel.SessionView;
import com.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class SessionFragment extends BaseFragment implements SessionView{

    @Inject
    SessionPresenter presenter;
    @Inject
    SessionAdapter adapter;

    public SessionFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.sessions;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView newsList = (ListView)rootView.findViewById(R.id.sessions_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_sessions_list));
        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }
        presenter.getAllSessions(getContext());
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
    public void displaySessions(List<SyncSession> sessions) {
        adapter.update(sessions);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void displaySessionsCalendar(List<SyncSession> sessions) {
        //CalendarFragment calendarFragment = new CalendarFragment();
        CalendarCustomFragment calendarFragment = new CalendarCustomFragment();
        Bundle args = new Bundle();

        args.putSerializable(CalendarFragment.EVENTS_ARG, (ArrayList)sessions);
        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_CALENDARIO);
        calendarFragment.setArguments(args);
        addFragmentToStack(calendarFragment, DetailActivity.FRAGMENT_CALENDARIO);
    }

    @Override
    public void displaySessionsErrorOrFailure() {
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }


    public void sessionsCalendar(){
        presenter.getAllSessionsCalendar(getContext());
    }
}
