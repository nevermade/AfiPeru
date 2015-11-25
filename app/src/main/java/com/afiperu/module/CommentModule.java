package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.CommentInteractor;
import com.afiperu.presenter.CommentPresenter;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.ui.adapter.CommentAdapter;
import com.afiperu.ui.viewmodel.CommentView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class CommentModule {
    private CommentView view;

    public CommentModule(CommentView view) {
        this.view = view;
    }

    @Provides
    public CommentView provideView(){
        return view;
    }

    @Provides
    public CommentPresenter providePresenter(CommentView view, CommentInteractor interactor){
        return new CommentPresenter(interactor,view);
    }

    @Provides
    public CommentAdapter provideAdapter(Context context, CommentView view) {
        return new CommentAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncComment>());
    }
}
