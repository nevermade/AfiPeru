package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.NewsPresenter;

/**
 * Created by Fernando on 17/11/2015.
 */
public interface NewsInteractor {
    void getAllNews(Context context, final NewsPresenter presenter);
}
