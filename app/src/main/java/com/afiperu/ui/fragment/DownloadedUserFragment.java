package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerDownloadedUserComponent;
import com.afiperu.module.DownloadedUserModule;
import com.afiperu.syncmodel.SyncDocumentUser;
import com.afiperu.ui.adapter.DownloadedUserAdapter;
import com.afiperu.ui.viewmodel.DownloadedUserView;

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

        ArrayList<SyncDocumentUser> users = (ArrayList<SyncDocumentUser>)args.getSerializable(LIST_ARG);
        displayDownloadedUsers(users);
    }

    @Override
    public void displayDownloadedUsers(ArrayList<SyncDocumentUser> users) {
        adapter.update(users);
    }
}
