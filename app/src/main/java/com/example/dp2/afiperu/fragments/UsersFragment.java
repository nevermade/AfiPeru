package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.BlogsAdapter;
import com.example.dp2.afiperu.lists.BlogsItem;
import com.example.dp2.afiperu.lists.UsersAdapter;
import com.example.dp2.afiperu.lists.UsersItem;

import java.util.ArrayList;


public class UsersFragment extends BaseFragment {
    public static final String USER_ARG = "user_arg";


    public UsersFragment() {
        super();
    }

    @Override

    public int getLayout() {
        return R.layout.users;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        ArrayList<UsersItem> users = (ArrayList<UsersItem>) args.getSerializable(USER_ARG);
        UsersAdapter adapter = new UsersAdapter(getContext(), this, users);

        ListView blogsList = (ListView) rootView.findViewById(R.id.users_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_users_list));


    }




}