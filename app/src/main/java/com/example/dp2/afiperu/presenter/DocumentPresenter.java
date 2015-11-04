package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.interactor.DocumentInteractor;
import com.example.dp2.afiperu.ui.viewmodel.DocumentView;

import java.util.ArrayList;

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

    public void getDocuments(){
        interactor.getDocuments(this);
    }

    public void onDocumentFound(ArrayList<Document> documents){
        view.displayDocuments(documents);
    }
}
