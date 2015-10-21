package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.DownloadedUserInteractor;
import com.example.dp2.afiperu.ui.viewmodel.DownloadedUserView;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class DownloadedUserPresenter extends BasePresenter {

    DownloadedUserView view;
    DownloadedUserInteractor interactor;

    public DownloadedUserPresenter(DownloadedUserInteractor interactor, DownloadedUserView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void getAllDownloadedUsers(){
        view.displayDownloadedUsers(interactor.getAllDownloadedUsers());
    }
}
