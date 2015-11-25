package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.UserInteractor;
import com.afiperu.presenter.UserPresenter;
import com.afiperu.syncmodel.SyncUser;
import com.afiperu.ui.adapter.UsersAdapter;
import com.afiperu.ui.viewmodel.UserView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 23/10/2015.
 */
@Module
public class UserModule {
    private UserView view;

    public UserModule(UserView view) {
        this.view = view;
    }

    @Provides
    public UserView provideView(){
        return view;
    }

    @Provides
    public UserPresenter providePresenter(UserView view, UserInteractor interactor){
        return new UserPresenter(view,interactor);
    }

    @Provides
    public UsersAdapter provideAdapter(Context context, UserView view) {
        return new UsersAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncUser>());
    }
}
