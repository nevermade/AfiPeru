package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerDownloadedUserComponent;
import com.example.dp2.afiperu.domain.DocumentUser;
import com.example.dp2.afiperu.module.DownloadedUserModule;
import com.example.dp2.afiperu.ui.adapter.DownloadedUserAdapter;
import com.example.dp2.afiperu.ui.viewmodel.DownloadedUserView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class DownloadedUserFragment extends BaseFragment implements DownloadedUserView {
    @Inject
    DownloadedUserAdapter adapter;

    public static final String LIST_ARG = "list_arg";

    @Override
    public int getLayout() {
        return R.layout.downloaded_user_list;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerDownloadedUserComponent.builder()
                .afiAppComponent(appComponent)
                .downloadedUserModule(new DownloadedUserModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView list=(ListView)rootView.findViewById(R.id.downloaded_list);
        list.setAdapter(adapter);
        list.setEmptyView(rootView.findViewById(R.id.empty_download_list));

        ArrayList<DocumentUser> users = (ArrayList<DocumentUser>)args.getSerializable(LIST_ARG);
        displayDownloadedUsers(users);
    }

    @Override
    public void displayDownloadedUsers(ArrayList<DocumentUser> users) {
        adapter.update(users);
    }
}
