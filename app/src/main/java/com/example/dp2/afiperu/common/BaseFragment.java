package com.example.dp2.afiperu.common;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dp2.afiperu.AfiApp;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.ui.activity.DetailActivity;

/**
 * Created by Fernando on 25/09/2015.
 */
public abstract class BaseFragment extends Fragment {

    public static final String FRAGMENT_ID_ARG = "fragment_id_arg";

    private int fragmentId = -1;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(getLayout(), container, false);
        fragmentId = getArguments().getInt(FRAGMENT_ID_ARG);
        injectDependencies();
        prepareView(rootView, getArguments(), savedInstanceState);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //injectDependencies();
    }

    public int getFragmentId(){
        if(fragmentId == -1){
            throw new ExceptionInInitializerError("Los BaseFragment necesitan recibir como argumento una constante DetailActivity.FRAGMENT_ como int (pasar con la llave BaseFragment.FRAGMENT_ID_ARG).");
        }
        return fragmentId;
    }

    public abstract int getLayout();

    public  void prepareView(View rootView, Bundle args, Bundle savedInstanceState){

    }

    public void onSearch(String query){

    }

    public boolean tryBack(){
        return true;
    }

    public final void downloadFile(String URL, String fileName, boolean overwrite){
        ((DetailActivity)getContext()).setDownloadFile(URL, fileName, overwrite);
    }

    public final void downloadDocument(SyncDocument document, String URL){
        ((DetailActivity)getContext()).setDownloadDocument(document, URL);
    }

    public final void setImage(ImageView v, String URL, String fileName){
        ((DetailActivity)getContext()).setImage(v, URL, fileName);
    }

    public final void addFragmentToStack(Fragment fragment, int fragmentId){
        DetailActivity activity = ((DetailActivity) getContext());
        activity.addFragment(fragment, activity.getTitle(fragmentId), activity.getMenu(fragmentId));
    }

    public final void showPopup(DialogFragment dialog, String tag){
        DetailActivity activity = ((DetailActivity) getContext());
        dialog.show(activity.getSupportFragmentManager(), tag);
    }
    private void injectDependencies() {
        setUpComponent(AfiApp.getApp(getActivity()).getComponent());
    }

    public abstract void setUpComponent(AfiAppComponent appComponent);

    protected abstract BasePresenter getPresenter();
/*
    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }*/

}