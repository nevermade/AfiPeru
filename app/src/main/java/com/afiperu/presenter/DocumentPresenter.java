package com.afiperu.presenter;

import android.content.Context;

import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.DocumentInteractor;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.ui.viewmodel.DocumentView;

import java.util.List;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class DocumentPresenter extends BasePresenter{

    DocumentInteractor interactor;
    DocumentView view;

    public DocumentPresenter(DocumentInteractor interactor, DocumentView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void getAllDocuments(Context context, Integer isReport){
        interactor.getDocuments(this, context, isReport);
    }

    public void onDocumentFound(List<SyncDocument> documents){
        view.displayDocuments(documents);
    }

    public void onFailure(){
        view.onFailure();
    }

    public void recordVisualization(Context context, Integer documentId){
        interactor.recordVisualization(context, documentId);
    }
}
