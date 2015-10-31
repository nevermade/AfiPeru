package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerUserComponent;
import com.example.dp2.afiperu.module.UserModule;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.adapter.UsersAdapter;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.ui.viewmodel.UserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class UsersFragment extends BaseFragment implements UserView {
    public static final String USER_ARG = "user_arg";
    @Inject
    UserPresenter presenter;
    @Inject
    UsersAdapter adapter;

    public UsersFragment() {
        super();
    }

    @Override

    public int getLayout() {
        return R.layout.users;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        /*
        ArrayList<User> users = (ArrayList<User>) args.getSerializable(USER_ARG);
        UsersAdapter adapter = new UsersAdapter(getContext(), this, users);
        System.out.println("Estoy aca");
        */
        ListView blogsList = (ListView) rootView.findViewById(R.id.users_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_users_list));
        presenter.getAllUsers(getContext());
        System.out.println("Estoy aca2");
        //ArrayList<User> u2 = presenter.getAllUsers();
        //Toast
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerUserComponent.builder()
                .afiAppComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void showUsers(ArrayList<SyncUser> users) {
        System.out.println("Estoy aca 6");
        adapter.updateUsers(users);
    /*
        UsersAdapter adapter = new UsersAdapter(getContext(), this, users);

        ListView blogsList = (ListView) rootView.findViewById(R.id.users_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_users_list));
        */
    }

}