package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.KidInteractor;
import com.afiperu.presenter.KidPresenter;
import com.afiperu.syncmodel.SyncKid;
import com.afiperu.ui.adapter.PeopleKidsAdapter;
import com.afiperu.ui.viewmodel.KidView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 23/10/2015.
 */
@Module
public class KidModule {
    private KidView view;

    public KidModule(KidView view) {
        this.view = view;
    }

    @Provides
    public KidView provideView(){
        return view;
    }

    @Provides
    public KidPresenter providePresenter(KidView view, KidInteractor interactor){
        return new KidPresenter(view,interactor);
    }

    @Provides
    public PeopleKidsAdapter provideAdapter(Context context, KidView view) {
        return new PeopleKidsAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncKid>());
    }
}
