package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.FavoriteNewsAdapter;
import com.example.dp2.afiperu.lists.NewsItem;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class FavoriteNewsFragment extends BaseFragment{

    public static final String FAVORITE_NEWS_ARG = "favorite_news_arg";

    public FavoriteNewsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.news;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<NewsItem> news = (ArrayList<NewsItem>)args.getSerializable(FAVORITE_NEWS_ARG);
        FavoriteNewsAdapter adapter = new FavoriteNewsAdapter(getContext(), this, news);

        ListView newsList = (ListView)rootView.findViewById(R.id.news_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_news_list));
    }

}
