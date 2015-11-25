package com.afiperu.ui.viewmodel;

import com.afiperu.syncmodel.SyncNews;

import java.util.List;

/**
 * Created by Fernando on 17/11/2015.
 */
public interface NewsView {

    void displayNews(List<SyncNews> news);
    void displayErrorOrFailure();
}
