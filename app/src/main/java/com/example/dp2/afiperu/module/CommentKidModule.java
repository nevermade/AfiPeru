package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.interactor.CommentKidInteractor;
import com.example.dp2.afiperu.presenter.CommentKidPresenter;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.ui.adapter.CommentKidAdapter;
import com.example.dp2.afiperu.ui.viewmodel.CommentKidView;
import com.example.dp2.afiperu.ui.viewmodel.DocumentView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class CommentKidModule {
    private CommentKidView view;

    public CommentKidModule(CommentKidView view) {
        this.view = view;
    }

    @Provides
    public CommentKidView provideView(){
        return view;
    }

    @Provides
    public CommentKidPresenter providePresenter(CommentKidView view, CommentKidInteractor interactor){
        return new CommentKidPresenter(interactor,view);
    }

    @Provides
    public CommentKidAdapter provideAdapter(Context context, CommentKidView view) {
        return new CommentKidAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<AttendanceChild>());
    }
}
