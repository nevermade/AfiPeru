package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerNewsComponent;
import com.example.dp2.afiperu.module.NewsModule;
import com.example.dp2.afiperu.presenter.NewsPresenter;
import com.example.dp2.afiperu.syncmodel.SyncNews;
import com.example.dp2.afiperu.ui.adapter.NewsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.NewsView;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class NewsFragment extends BaseFragment implements NewsView {

    @Inject
    NewsAdapter adapter;
    @Inject
    NewsPresenter presenter;

    public NewsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.news;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView newsList = (ListView)rootView.findViewById(R.id.news_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_news_list));
        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }
        presenter.getAllNews(getContext());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerNewsComponent.builder()
                .afiAppComponent(appComponent)
                .newsModule(new NewsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void displayNews(List<SyncNews> news){
        adapter.update(news);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void displayErrorOrFailure(){
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }
    @Override
    public void onSearch(String query){
        if (query.contentEquals("") ){
            List<SyncNews> all = SyncNews.listAll(SyncNews.class);
            adapter.update(all);
        }else {
            List<SyncNews> filter = SyncNews.find(SyncNews.class, "title like ? or content like ?", "%" + query + "%", "%" + query + "%");
            adapter.update(filter);
        }
    }


    @Override
    public void onCloseSearch(){
        List<SyncNews> all = SyncNews.listAll(SyncNews.class);
        adapter.update(all);
    }
}
