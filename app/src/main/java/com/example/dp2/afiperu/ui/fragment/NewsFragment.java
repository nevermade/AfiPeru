package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.ui.adapter.NewsAdapter;
import com.example.dp2.afiperu.domain.News;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class NewsFragment extends BaseFragment {

    public static final String NEWS_ARG = "news_arg";

    private boolean[] isFavorite;

    public NewsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.news;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<News> news = (ArrayList<News>)args.getSerializable(NEWS_ARG);
        NewsAdapter adapter = new NewsAdapter(getContext(), this, news);

        ListView newsList = (ListView)rootView.findViewById(R.id.news_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_news_list));

        isFavorite = new boolean[news.size()];
        for(int i=0; i<isFavorite.length; i++){
            isFavorite[i] = news.get(i).isFavorite();
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    public boolean toggleFavorite(int position){
        isFavorite[position] = !isFavorite[position];
        return isFavorite[position];
    }

}
