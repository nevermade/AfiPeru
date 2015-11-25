package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.DocumentPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface DocumentInteractor {
    void getDocuments(DocumentPresenter presenter, Context context, Integer isReport);
    void recordVisualization(Context context, Integer documentId);
}
