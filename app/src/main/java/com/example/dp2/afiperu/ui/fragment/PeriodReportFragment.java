package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerPeriodReportComponent;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.module.PeriodReportModule;
import com.example.dp2.afiperu.presenter.PeriodReportPresenter;
import com.example.dp2.afiperu.ui.adapter.PeriodReportAdapter;
import com.example.dp2.afiperu.ui.viewmodel.PeriodReportView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class PeriodReportFragment extends BaseFragment implements PeriodReportView {
    @Inject
    PeriodReportPresenter presenter;
    @Inject
    PeriodReportAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.documents;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerPeriodReportComponent.builder()
                .afiAppComponent(appComponent)
                .periodReportModule(new PeriodReportModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        /*blogSearchPresenter.getAllArtists();
        //BlogsAdapter adapter = new BlogsAdapter(getContext(), this, blogs);

        blogsList = (ListView)rootView.findViewById(R.id.blogs_list);
        blogsList.setAdapter(blogSearchAdapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));

        isFavorite = new boolean[blogSearchAdapter.getCount()];
        for(int i=0; i<isFavorite.length; i++){
            isFavorite[i] = blogSearchAdapter.getItem(i).isFavorite();
        }*/
        ListView list = (ListView)rootView.findViewById(R.id.docs_list);
        list.setAdapter(adapter);
        list.setEmptyView(rootView.findViewById(R.id.empty_docs_list));

        presenter.getAllDocuments();

    }

    @Override
    public void displayDocuments(ArrayList<Document> docs) {
        if(docs!=null){
            adapter.updateDocs(docs);
        }
    }
}
