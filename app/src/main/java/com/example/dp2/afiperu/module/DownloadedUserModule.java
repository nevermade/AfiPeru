package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.interactor.DownloadedUserInteractor;
import com.example.dp2.afiperu.presenter.DownloadedUserPresenter;
import com.example.dp2.afiperu.ui.adapter.DownloadedUserAdapter;
import com.example.dp2.afiperu.ui.viewmodel.DownloadedUserView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 20/10/2015.
 */
@Module
public class DownloadedUserModule {
    private DownloadedUserView view;

    public DownloadedUserModule(DownloadedUserView view) {
        this.view = view;
    }

    @Provides
    public DownloadedUserView provideView(){
        return view;
    }

    @Provides
    public DownloadedUserPresenter providePresenter(DownloadedUserView view, DownloadedUserInteractor interactor){
        return new DownloadedUserPresenter(interactor, view);
    }

    @Provides
    public DownloadedUserAdapter provideAdapter(Context context, DownloadedUserView view){
        return new DownloadedUserAdapter(context, (BaseFragment)view, new ArrayList<User>());
    }
}
