package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerDocumentComponent;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.module.DocumentModule;
import com.example.dp2.afiperu.presenter.DocumentPresenter;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.ui.adapter.DocumentsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.DocumentView;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class DocumentsFragment extends BaseFragment implements DocumentView {

    @Inject
    DocumentPresenter presenter;
    @Inject
    DocumentsAdapter adapter;

    public static final String DOCUMENTS_ARG = "documents_arg";

    public DocumentsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.documents;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView docsList = (ListView)rootView.findViewById(R.id.docs_list);
        docsList.setAdapter(adapter);
        docsList.setEmptyView(rootView.findViewById(R.id.empty_docs_list));

        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        ArrayList<SyncDocument> documents = (ArrayList<SyncDocument>)args.getSerializable(DOCUMENTS_ARG);
        if(documents != null){
            adapter.update(documents);
            rootView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
        } else {
            presenter.getAllDocuments(getContext());
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerDocumentComponent.builder()
                .afiAppComponent(appComponent)
                .documentModule(new DocumentModule(this))
                .build()
                .inject(this);
    }

    public void recordVisualization(Integer document){
        presenter.recordVisualization(getContext(), document);
    }

    @Override
    public void displayDocuments(List<SyncDocument> documents) {
        adapter.update(documents);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(){
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }
}
