package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.presenter.ChangePasswordPresenter;
import com.example.dp2.afiperu.ui.adapter.ChangePasswordAdapter;
import com.example.dp2.afiperu.ui.viewmodel.ChangePasswordView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
@Module
public class ChangePasswordModule {
    private ChangePasswordView view;

    public ChangePasswordModule(ChangePasswordView view) {
        this.view = view;
    }

    @Provides
    public ChangePasswordView provideView(){
        return view;
    }

    @Provides
    public ChangePasswordPresenter providePresenter(ChangePasswordView view, ChangePasswordInteractor interactor){
        return new ChangePasswordPresenter(view, interactor);
}

    @Provides
    public ChangePasswordAdapter provideAdapter(Context context, ChangePasswordView view){
        return new ChangePasswordAdapter(((BaseFragment)view).getActivity(), (BaseFragment)view, new ArrayList<Blog>());
    }

}
