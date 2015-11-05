package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.presenter.DocumentPresenter;

/**
 * Created by DABARCA on 21/10/2015.
 */
public interface DocumentInteractor {
    void getDocuments(DocumentPresenter presenter);
    void recordVisualization(Integer documentId);
}
