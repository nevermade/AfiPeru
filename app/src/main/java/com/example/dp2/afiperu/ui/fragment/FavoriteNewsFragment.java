package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.adapter.FavoriteNewsAdapter;
import com.example.dp2.afiperu.domain.News;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class FavoriteNewsFragment extends BaseFragment {

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
        ArrayList<News> news = (ArrayList<News>)args.getSerializable(FAVORITE_NEWS_ARG);
        FavoriteNewsAdapter adapter = new FavoriteNewsAdapter(getContext(), this, news);

        ListView newsList = (ListView)rootView.findViewById(R.id.news_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_news_list));
    }

}
