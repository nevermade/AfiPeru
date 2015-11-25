package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.NewsPresenter;

/**
 * Created by Fernando on 17/11/2015.
 */
public interface NewsInteractor {
    void getAllNews(Context context, final NewsPresenter presenter);
}
