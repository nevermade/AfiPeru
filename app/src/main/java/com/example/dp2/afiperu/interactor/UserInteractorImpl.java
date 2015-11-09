package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.LocationsBody;
import com.example.dp2.afiperu.rest.model.School;
import com.example.dp2.afiperu.rest.model.Volunteer;
import com.example.dp2.afiperu.syncmodel.SyncSchoolAddress;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.Collections;
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
    public UserInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void getAllUsers(Context context, final UserPresenter presenter) {
        if (NetworkManager.isNetworkConnected(context)) { // Si tengo conexion a internet
            Call<List<User>> call = service.getAllUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                    ArrayList<User> users = (ArrayList<User>) response.body();

                    if (users != null) {
                        SyncUser.deleteAll(SyncUser.class);
                        for (User user : users) {
                            SyncUser su = new SyncUser();
                            su.setName(user.getName());
                            su.setLastName(user.getLastName());
                            su.setSecondLastName(user.getSecondLastName());
                            su.setNickName(user.getNickName());
                            su.setProfile(user.getProfiles().get(0).getName());
                            su.save();
                        }

                        List<SyncUser> lista = SyncUser.listAll(SyncUser.class);
                        Collections.sort(lista);
                        presenter.onUsersFound(lista);
                    } else {
                        presenter.onUsersErrorOrFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onUsersErrorOrFailure();
                }
            });

        }else{// Si no tengo conexion
            List<SyncUser> lista = SyncUser.listAll(SyncUser.class);
            Collections.sort(lista);
            presenter.onUsersFound(lista);
        }
    }

    @Override
    public void getLocations(Context context, final UserPresenter presenter) {
        if(NetworkManager.isNetworkConnected(context)) {
            Call<LocationsBody> locations = service.getLocations();
            locations.enqueue(new Callback<LocationsBody>() {
                @Override
                public void onResponse(Response<LocationsBody> response, Retrofit retrofit) {
                    LocationsBody result = response.body();
                    if(result != null && !result.getSchools().isEmpty() && !result.getVolunteers().isEmpty()) {
                        SyncSchoolAddress.deleteAll(SyncSchoolAddress.class);
                        for(School school : result.getSchools()){
                            SyncSchoolAddress address = SyncSchoolAddress.fromSchool(school);
                            address.save();
                        }
                        for(Volunteer volunteer : result.getVolunteers()){
                            SyncSchoolAddress addess = SyncSchoolAddress.fromVolunteer(volunteer);
                            addess.save();
                        }

                        List<SyncSchoolAddress> addresses = SyncSchoolAddress.listAll(SyncSchoolAddress.class);
                        presenter.onLocationsFound(addresses);
                    }else{
                        presenter.onLocationsFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onLocationsFailure();
                }
            });
        }else{
            List<SyncSchoolAddress> addresses = SyncSchoolAddress.listAll(SyncSchoolAddress.class);
            presenter.onLocationsFound(addresses);
        }
    }
}
