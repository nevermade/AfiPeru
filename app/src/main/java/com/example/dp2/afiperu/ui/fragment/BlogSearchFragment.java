package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerBlogSearchComponent;
import com.example.dp2.afiperu.module.BlogSearchModule;
import com.example.dp2.afiperu.presenter.BlogSearchPresenter;
import com.example.dp2.afiperu.ui.adapter.BlogSearchAdapter;
import com.example.dp2.afiperu.ui.adapter.BlogsAdapter;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.ui.viewmodel.BlogSearchView;

import java.util.ArrayList;

import javax.inject.Inject;


public class BlogSearchFragment extends BaseFragment implements BlogSearchView {
    public static final String BLOG_ARG = "blog_arg";

    @Inject
    BlogSearchAdapter blogSearchAdapter;
    @Inject
    BlogSearchPresenter blogSearchPresenter;



    private boolean[] isFavorite;

    ListView blogsList;

    public BlogSearchFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.blogs;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        blogSearchPresenter.getAllArtists();
        //BlogsAdapter adapter = new BlogsAdapter(getContext(), this, blogs);

        blogsList = (ListView)rootView.findViewById(R.id.blogs_list);
        blogsList.setAdapter(blogSearchAdapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));

        isFavorite = new boolean[blogSearchAdapter.getCount()];
        for(int i=0; i<isFavorite.length; i++){
            isFavorite[i] = blogSearchAdapter.getItem(i).isFavorite();
        }
    }


    @Override
    protected BasePresenter getPresenter() {
        return blogSearchPresenter;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerBlogSearchComponent.builder()
                .afiAppComponent(appComponent)
                .blogSearchModule(new BlogSearchModule(this))
                .build()
                .inject(this);
    }

    public boolean toggleFavorite(int position){
        isFavorite[position] = !isFavorite[position];
        return isFavorite[position];
    }


    @Override
    public void setupList() {
        //blogsList.setAdapter(blogSearchAdapter);
    }

    @Override
    public void setupAdapter() {

    }

    @Override
    public void displayFoundBlogs(ArrayList<Blog> blogs) {


        blogSearchAdapter.updateBlogs(blogs);

    }

    @Override
    public void displayEmptyList() {

    }
}
