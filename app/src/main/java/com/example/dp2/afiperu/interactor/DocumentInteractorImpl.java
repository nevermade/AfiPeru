package com.example.dp2.afiperu.interactor;

import android.util.Log;

import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.presenter.DocumentPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;

import java.util.ArrayList;
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
    public void getDocuments(final DocumentPresenter presenter) {
        Call<List<Document>> call = service.getDocuments();
        call.enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Response<List<Document>> response, Retrofit retrofit) {
                ArrayList<Document> documents=(ArrayList<Document>)response.body();

                if(documents!=null)
                    presenter.onDocumentFound(documents);
            }

            @Override
            public void onFailure(Throwable t){
            }
        });
     }

    @Override
    public void recordVisualization(Integer documentId){
        Call<Void> call = service.recordVisualization(documentId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                Log.d("docs", "ok");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("docs", null, t);
            }
        });
    }
}
