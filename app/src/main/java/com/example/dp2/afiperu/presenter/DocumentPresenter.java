package com.example.dp2.afiperu.presenter;

import android.content.Context;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.DocumentInteractor;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.ui.viewmodel.DocumentView;

import java.util.ArrayList;
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

    public void getAllDocuments(Context context){
        interactor.getDocuments(this, context);
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
