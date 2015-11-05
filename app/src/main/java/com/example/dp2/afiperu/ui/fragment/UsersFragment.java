package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
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
import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.rest.model.School;
import com.example.dp2.afiperu.rest.model.Volunteer;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.adapter.UsersAdapter;
import com.example.dp2.afiperu.ui.viewmodel.UserView;

import java.util.ArrayList;

import javax.inject.Inject;


public class UsersFragment extends BaseFragment implements UserView {
    public static final String USER_ARG = "user_arg";
    @Inject
    UserPresenter presenter;
    @Inject
    UsersAdapter adapter;
    View rootView;
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
        this.rootView=rootView;
        rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        ListView blogsList = (ListView) rootView.findViewById(R.id.users_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_users_list));
        presenter.getAllUsers(getContext());
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
        //System.out.println("Estoy aca 6");

        adapter.update(users);
        rootView.findViewById(R.id.progress_bar).setVisibility(View.GONE);

    /*
        UsersAdapter adapter = new UsersAdapter(getContext(), this, users);

        ListView blogsList = (ListView) rootView.findViewById(R.id.users_list);
        blogsList.setAdapter(adapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_users_list));
        */
    }


    public void getLocations(){
        presenter.getLocations();
    }

    @Override
    public void showLocations(LocationsBody locations){
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        ArrayList<MarkerInfo> markers = new ArrayList<>();
        /*markers.add(new MarkerInfo(-1, -12.0731492, -77.0819083, MarkerInfo.MARKER_KIND_INFO_SCHOOL, null));
        markers.add(new MarkerInfo(-1, -12.0767993, -77.0811531, MarkerInfo.MARKER_KIND_INFO_VOLUNTEER, "Luis"));
        markers.add(new MarkerInfo(-1, -12.0587955, -77.0815501, MarkerInfo.MARKER_KIND_INFO_SCHOOL, null));
        markers.add(new MarkerInfo(-1, -12.067451, -77.061305, MarkerInfo.MARKER_KIND_INFO_VOLUNTEER, "Luis"));*/
        for(School school : locations.getSchools()){
            markers.add(new MarkerInfo(-1, school.getLatitude(), school.getLongitude(), MarkerInfo.MARKER_KIND_INFO_SCHOOL, school.getName()));
        }
        for(Volunteer volunteer : locations.getVolunteers()){
            markers.add(new MarkerInfo(-1, volunteer.getLatitude(), volunteer.getLongitude(), MarkerInfo.MARKER_KIND_INFO_VOLUNTEER, volunteer.getNames() + " " + volunteer.getLastName()));
        }
        args.putSerializable(MapFragment.MARKERS_ARG, markers);
        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_MAPA);
        mapFragment.setArguments(args);
        addFragmentToStack(mapFragment, DetailActivity.FRAGMENT_MAPA);
    }

    @Override
    public void displayErrorOrFailure() {
        rootView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }

}