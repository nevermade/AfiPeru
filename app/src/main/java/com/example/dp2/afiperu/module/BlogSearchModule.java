package com.example.dp2.afiperu.module;

import android.app.Fragment;
import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.presenter.BlogSearchPresenter;
import com.example.dp2.afiperu.ui.adapter.BlogSearchAdapter;
import com.example.dp2.afiperu.ui.viewmodel.BlogSearchView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
@Module
public class BlogSearchModule {
    private BlogSearchView view;

    public BlogSearchModule(BlogSearchView view) {
        this.view = view;
    }

    @Provides
    public BlogSearchView provideView(){
        return view;
    }

    @Provides
    public BlogSearchPresenter providePresenter(BlogSearchView view, BlogSearchInteractor interactor){
        return new BlogSearchPresenter(view, interactor);
    }

    @Provides
    public BlogSearchAdapter provideAdapter(Context context, BlogSearchView view){
        return new BlogSearchAdapter(((BaseFragment)view).getActivity(), (BaseFragment)view, new ArrayList<Blog>());
    }

}
