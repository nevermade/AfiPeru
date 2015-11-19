package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.syncmodel.SyncNews;
import com.example.dp2.afiperu.ui.adapter.FavoriteNewsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.NewsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class FavoriteNewsFragment extends BaseFragment {

    FavoriteNewsAdapter adapter;

    public FavoriteNewsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.news;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView newsList = (ListView)rootView.findViewById(R.id.news_list);
        adapter = new FavoriteNewsAdapter(getContext(), this, new ArrayList<SyncNews>());
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_news_list));
    }

    public void updateFavorites(){
        if(adapter != null) {
            List<SyncNews> news = SyncNews.find(SyncNews.class, "is_favorite = ?", "1");
            adapter.update(news);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    @Override
    public void onSearch(String query){
        if (query.contentEquals("") ){
            List<SyncNews> all = SyncNews.find(SyncNews.class, "is_favorite = ?", "1");
            adapter.update(all);
        }else {
            List<SyncNews> filter = SyncNews.find(SyncNews.class, "is_favorite = ? and (title like ? or content like ?)", "1",
                    "%" + query + "%", "%" + query + "%");
            adapter.update(filter);
        }
    }

    @Override
    public void onCloseSearch(){
        List<SyncNews> all = SyncNews.find(SyncNews.class, "is_favorite = ?", "1");
        adapter.update(all);
    }
}
