package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.LoginInteractor;
import com.example.dp2.afiperu.interactor.UserInteractor;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.adapter.UsersAdapter;
import com.example.dp2.afiperu.ui.viewmodel.LoginView;
import com.example.dp2.afiperu.ui.viewmodel.UserView;

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
