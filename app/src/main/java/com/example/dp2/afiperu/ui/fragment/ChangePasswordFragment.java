package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerChangePasswordComponent;
import com.example.dp2.afiperu.module.ChangePasswordModule;
import com.example.dp2.afiperu.presenter.ChangePasswordPresenter;
import com.example.dp2.afiperu.ui.adapter.ChangePasswordAdapter;
import com.example.dp2.afiperu.ui.viewmodel.ChangePasswordView;

import javax.inject.Inject;


public class ChangePasswordFragment extends BaseFragment implements ChangePasswordView {
    public static final String BLOG_ARG = "blog_arg";

    @Inject
    ChangePasswordAdapter changePasswordAdapter;
    @Inject
    ChangePasswordPresenter changePasswordPresenter;



    private boolean[] isFavorite;

    ListView blogsList;

    public ChangePasswordFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.change_password;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        //blogSearchPresenter.getAllArtists();
        //BlogsAdapter adapter = new BlogsAdapter(getContext(), this, blogs);
/*
        blogsList = (ListView)rootView.findViewById(R.id.blogs_list);
        blogsList.setAdapter(blogSearchAdapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));

        isFavorite = new boolean[blogSearchAdapter.getCount()];
        for(int i=0; i<isFavorite.length; i++){
            isFavorite[i] = blogSearchAdapter.getItem(i).isFavorite();
        }
        */

    }


    @Override
    protected BasePresenter getPresenter() {
        return changePasswordPresenter;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerChangePasswordComponent.builder()
                .afiAppComponent(appComponent)
                .changePasswordModule(new ChangePasswordModule(this))
                .build()
                .inject(this);
    }

    public boolean toggleFavorite(int position){
        isFavorite[position] = !isFavorite[position];
        return isFavorite[position];
    }

/*
    @Override
    public void setupList() {
        //blogsList.setAdapter(blogSearchAdapter);
    }
*/

    @Override
    public void setupAdapter() {

    }
/*
    @Override
    public void displayFoundBlogs(ArrayList<Blog> blogs) {


        blogSearchAdapter.updateBlogs(blogs);

    }

    @Override
    public void displayEmptyList() {

    }
    */
}
