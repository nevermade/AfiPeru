package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.syncmodel.SyncUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nevermade on 12/10/2015.
 */
public class UserInteractorImpl implements UserInteractor {
    AfiApiServiceEndPoints service;
    //public UserInteractorImpl(){}

    public UserInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }


    @Override
    public ArrayList<User> getAllUsers(final UserPresenter presenter) {
        System.out.println("Estoy aca 4");
        /*
        ArrayList<User> us = new ArrayList<>();
        User u = new User("1",1,"Luis","Barcena","navarro","Miembro de AFI",4.0,false);
        us.add(u);
        presenter.onUsersFound(us);
        */
        ArrayList<User> users2 =null;
        Call<List<User>> call = service.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                ArrayList<User> users=(ArrayList<User>)response.body();

                if(users!=null) {
                    SyncUser.deleteAll(SyncUser.class);
                    for (User user : users){
                        SyncUser su = new SyncUser();
                        su.setName(user.getName());
                        su.setLastName(user.getLastName());
                        su.setSecondLastName(user.getSecondLastName());
                        su.setNickName(user.getNickName());
                        su.setProfile(user.getProfiles().get(0).getName());
                        su.save();
                    }

                    List<SyncUser> lista = SyncUser.listAll(SyncUser.class);
                    ArrayList<SyncUser> listav=new ArrayList<>();

                    for (SyncUser user : lista) listav.add(user);

                    presenter.onUsersFound(listav);
                    System.out.println(users.get(0).getName());


                }
                else System.out.println("NULL");
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });



        return null;
    }

    @Override
    public LocationsBody getLocations(final UserPresenter presenter) {
        Call<LocationsBody> locations = service.getLocations();
        locations.enqueue(new Callback<LocationsBody>() {
            @Override
            public void onResponse(Response<LocationsBody> response, Retrofit retrofit) {
                presenter.onLocationsFound(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return null;
    }
}
