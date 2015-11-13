package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerUserComponent;
import com.example.dp2.afiperu.module.UserModule;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.syncmodel.SyncSchoolAddress;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.adapter.UsersAdapter;
import com.example.dp2.afiperu.ui.viewmodel.UserView;
import com.example.dp2.afiperu.util.NetworkManager;

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
        ListView blogsList = (ListView) rootView.findViewById(R.id.users_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_users_list));
        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }
        presenter.getAllUsers(getContext());
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
    public void showUsers(List<SyncUser> users) {
        adapter.update(users);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    public void getLocations(){
        presenter.getLocations(getContext());
    }

    @Override
    public void showLocations(List<SyncSchoolAddress> locations){
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        ArrayList<MarkerInfo> markers = new ArrayList<>();
        for(SyncSchoolAddress address : locations){
            markers.add(new MarkerInfo(-1, address.getLatitude(), address.getLongitude(),
                    address.getType() == SyncSchoolAddress.TYPE_SCHOOL ? MarkerInfo.MARKER_KIND_INFO_SCHOOL : MarkerInfo.MARKER_KIND_INFO_VOLUNTEER,
                    address.getName()));
        }
        args.putSerializable(MapFragment.MARKERS_ARG, markers);
        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_MAPA);
        mapFragment.setArguments(args);
        addFragmentToStack(mapFragment, DetailActivity.FRAGMENT_MAPA);
    }

    @Override
    public void onLocationsFailure(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.no_locations).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void displayErrorOrFailure() {
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onSearch(String query){
        //ACÁ HAZ TU BÚSQUEDA SIMPLE
        if (query.contentEquals("") ){
            presenter.getAllUsers(getContext());
        }
            else
            presenter.queryUsers(getContext(),query);
        int a = 0;
    }


    @Override
    public void onCloseSearch(){
        //ACÁ HAZ TU BÚSQUEDA SIMPLE

            presenter.getAllUsers(getContext());

        int a = 0;
    }


    @Override
    public void onUserAdvancedSearch(String name, String nrodoc, String perfil){


        presenter.queryAdvancedUsers(getContext(),name,nrodoc,perfil);
    }

}