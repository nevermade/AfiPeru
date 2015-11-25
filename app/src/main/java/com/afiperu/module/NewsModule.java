package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.NewsInteractor;
import com.afiperu.presenter.NewsPresenter;
import com.afiperu.syncmodel.SyncNews;
import com.afiperu.ui.adapter.NewsAdapter;
import com.afiperu.ui.viewmodel.NewsView;

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
