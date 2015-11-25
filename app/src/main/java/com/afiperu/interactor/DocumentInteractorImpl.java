package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.Document;
import com.afiperu.domain.DocumentUser;
import com.afiperu.presenter.DocumentPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.syncmodel.SyncDocumentUser;
import com.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class DocumentInteractorImpl implements DocumentInteractor {
    AfiApiServiceEndPoints service;

    public DocumentInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getDocuments(final DocumentPresenter presenter, Context context, final Integer isReport) {
        if(NetworkManager.isNetworkConnected(context)) {
            Call<List<Document>> call;
            if(isReport == 0) {
                call = service.getDocuments();
            }else{
                call = service.getPeriodReports();
            }
            call.enqueue(new Callback<List<Document>>() {
                @Override
                public void onResponse(Response<List<Document>> response, Retrofit retrofit) {
                    ArrayList<Document> result = (ArrayList<Document>) response.body();

                    if (result != null) {
                        SyncDocument.deleteAll(SyncDocument.class, "is_report = ?", String.valueOf(isReport));
                        if(isReport == 0) {
                            SyncDocumentUser.deleteAll(SyncDocumentUser.class);
                        }
                        for(Document document : result){
                            SyncDocument doc = SyncDocument.fromDocument(document, isReport);
                            doc.save();
                            if(isReport == 0) {
                                for (DocumentUser user : document.getUsers()) {
                                    SyncDocumentUser docUser = SyncDocumentUser.fromDocumentUser(user);
                                    docUser.setDocument(doc);
                                    docUser.save();
                                }
                            }
                        }
                        List<SyncDocument> documents = SyncDocument.find(SyncDocument.class, "is_report = ?", String.valueOf(isReport));
                        Collections.sort(documents);
                        presenter.onDocumentFound(documents);
                    }else{
                        presenter.onFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onFailure();
                }
            });
        }else{
            List<SyncDocument> documents = SyncDocument.find(SyncDocument.class, "is_report = ?", String.valueOf(isReport));
            Collections.sort(documents);
            presenter.onDocumentFound(documents);
        }
     }

    @Override
    public void recordVisualization(Context context, final Integer documentId){
        if(NetworkManager.isNetworkConnected(context)) {
            Call<Void> call = service.recordVisualization(documentId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Response<Void> response, Retrofit retrofit) {
                    if(response.body() != null){
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        }else{
        }
    }
}
