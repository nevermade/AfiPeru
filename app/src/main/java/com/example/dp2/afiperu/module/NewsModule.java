package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.interactor.NewsInteractor;
import com.example.dp2.afiperu.presenter.NewsPresenter;
import com.example.dp2.afiperu.syncmodel.SyncNews;
import com.example.dp2.afiperu.ui.adapter.NewsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.NewsView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class NewsModule {
    private NewsView view;

    public NewsModule(NewsView view) {
        this.view = view;
    }

    @Provides
    public NewsView provideView(){
        return view;
    }

    @Provides
    public NewsPresenter providePresenter(NewsView view, NewsInteractor interactor){
        return new NewsPresenter(interactor,view);
    }

    @Provides
    public NewsAdapter provideAdapter(Context context, NewsView view) {
        return new NewsAdapter(((BaseFragment)view).getActivity(), (BaseFragment) view, new ArrayList<SyncNews>());
    }
}
