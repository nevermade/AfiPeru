package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.DocumentPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface DocumentInteractor {
    void getDocuments(DocumentPresenter presenter, Context context);
    void recordVisualization(Integer documentId);
}
