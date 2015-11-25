package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerDocumentComponent;
import com.afiperu.module.DocumentModule;
import com.afiperu.presenter.DocumentPresenter;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.ui.adapter.DocumentsAdapter;
import com.afiperu.ui.viewmodel.DocumentView;
import com.afiperu.util.NetworkManager;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class PeriodReportFragment extends BaseFragment implements DocumentView {
    @Inject
    DocumentPresenter presenter;
    @Inject
    DocumentsAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.documents;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerDocumentComponent.builder()
                .afiAppComponent(appComponent)
                .documentModule(new DocumentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView docsList = (ListView)rootView.findViewById(R.id.docs_list);
        docsList.setAdapter(adapter);
        docsList.setEmptyView(rootView.findViewById(R.id.empty_docs_list));

        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        presenter.getAllDocuments(getContext(), 1);
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
