package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.SessionInteractor;
import com.afiperu.presenter.SessionPresenter;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.ui.adapter.SessionAdapter;
import com.afiperu.ui.viewmodel.SessionView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class SessionModule {
    private SessionView view;

    public SessionModule(SessionView view) {
        this.view = view;
    }

    @Provides
    public SessionView provideView(){
        return view;
    }

    @Provides
    public SessionPresenter providePresenter(SessionView view, SessionInteractor interactor){
        return new SessionPresenter(interactor,view);
    }

    @Provides
    public SessionAdapter provideAdapter(Context context, SessionView view) {
        return new SessionAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncSession>());
    }
}
