package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.adapter.BlogsAdapter;
import com.example.dp2.afiperu.domain.Blogs;

import java.util.ArrayList;


public class BlogsFragment extends BaseFragment {
    public static final String BLOG_ARG = "blog_arg";
    private boolean[] isFavorite;

    public BlogsFragment(){
        super();
    }
    @Override

    public int getLayout(){
        return R.layout.blogs;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<Blogs> blogs = (ArrayList<Blogs>)args.getSerializable(BLOG_ARG);
        BlogsAdapter adapter = new BlogsAdapter(getContext(), this, blogs);

        ListView blogsList = (ListView)rootView.findViewById(R.id.blogs_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));

        isFavorite = new boolean[blogs.size()];
        for(int i=0; i<isFavorite.length; i++){
            isFavorite[i] = blogs.get(i).isFavorite();
        }
    }

    public boolean toggleFavorite(int position){
        isFavorite[position] = !isFavorite[position];
        return isFavorite[position];
    }


}
