package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Blogs;
import com.example.dp2.afiperu.ui.adapter.FavoriteBlogAdapter;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class FavoriteBlogFragment extends BaseFragment {

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
        ArrayList<Blogs> blogs = (ArrayList<Blogs>)args.getSerializable(FAVORITE_BLOG_ARG);
        FavoriteBlogAdapter adapter = new FavoriteBlogAdapter(getContext(), this, blogs);

        ListView newsList = (ListView)rootView.findViewById(R.id.blogs_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));
    }

}
