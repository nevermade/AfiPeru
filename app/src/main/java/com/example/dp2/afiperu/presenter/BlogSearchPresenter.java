package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.ui.viewmodel.BlogSearchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by Nevermade on 12/10/2015.
 */
public class BlogSearchPresenter extends BasePresenter{
    BlogSearchView view;
    BlogSearchInteractor interactor;

    public BlogSearchPresenter(BlogSearchView view, BlogSearchInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void onStart() {
        view.setupAdapter();
        view.setupList();
    }

    @Override
    public void onStop() {

    }

    public void getAllArtists(){

        view.displayFoundBlogs(interactor.getAllBlogs());
    }
}
