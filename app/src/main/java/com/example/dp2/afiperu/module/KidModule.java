package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.interactor.KidInteractor;
import com.example.dp2.afiperu.presenter.KidPresenter;
import com.example.dp2.afiperu.syncmodel.SyncKid;
import com.example.dp2.afiperu.ui.adapter.PeopleKidsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.KidView;

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
