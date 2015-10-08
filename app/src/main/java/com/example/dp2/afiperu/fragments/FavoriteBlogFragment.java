package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.BlogsItem;
import com.example.dp2.afiperu.lists.FavoriteBlogAdapter;
import com.example.dp2.afiperu.lists.FavoriteNewsAdapter;
import com.example.dp2.afiperu.lists.NewsItem;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class FavoriteBlogFragment extends BaseFragment{

    public static final String FAVORITE_BLOG_ARG = "favorite_blog_arg";

    public FavoriteBlogFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.blogs;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<BlogsItem> blogs = (ArrayList<BlogsItem>)args.getSerializable(FAVORITE_BLOG_ARG);
        FavoriteBlogAdapter adapter = new FavoriteBlogAdapter(getContext(), this, blogs);

        ListView newsList = (ListView)rootView.findViewById(R.id.blogs_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));
    }

}
