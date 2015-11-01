package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.KidPresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.syncmodel.SyncKid;
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
public class KidInteractorImpl implements KidInteractor {
    AfiApiServiceEndPoints service;
    Call<Kid> call;
    NetworkManager networkManager = new NetworkManager();
    public KidInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }


    @Override
    public ArrayList<Kid> getAllKids(final KidPresenter presenter, Context context) {
/*


        ArrayList<SyncKid> us = new ArrayList<>();
        SyncKid u = new SyncKid();
        u.setGender(0);
        u.setAge(15);
        u.setNames("Gilberto");
        us.add(u);
        presenter.onUsersFound(us);*/


        if (networkManager.isNetworkConnected(context)) { // Si tengo conexion a internet
            //ArrayList<User> users2 = null;
            System.out.println("Estoy aca 91");
            Call<List<Kid>> call = service.getAllKids();
            call.enqueue(new Callback<List<Kid>>() {
                @Override
                public void onResponse(Response<List<Kid>> response, Retrofit retrofit) {
                    ArrayList<Kid> kids = (ArrayList<Kid>) response.body();

                    if (kids != null) {
                        SyncKid.deleteAll(SyncKid.class);
                        for (Kid kid : kids) {
                            SyncKid su = new SyncKid();
                            su.setNames(kid.getNames());
                            su.setLastName(kid.getLastName());
                            su.setAge(kid.getAge());
                            su.setGender(kid.getGender());
                            su.save();
                        }

                        List<SyncKid> lista = SyncKid.listAll(SyncKid.class);
                        ArrayList<SyncKid> listav = new ArrayList<>();

                        for (SyncKid kid : lista) listav.add(kid);
                        Collections.sort(listav);
                        presenter.onUsersFound(listav);
                        //System.out.println(users.get(0).getName());


                    } else System.out.println("NULL KIDS");
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        }else{// Si no tengo conexion

            List<SyncKid> lista = SyncKid.listAll(SyncKid.class);
            ArrayList<SyncKid> listav = new ArrayList<>();

            for (SyncKid kid : lista) listav.add(kid);
            Collections.sort(listav);
            presenter.onUsersFound(listav);

        }

        return null;
    }
}
