package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.Kid;
import com.afiperu.presenter.KidPresenter;
import com.afiperu.rest.AfiApiServiceEndPoints;
import com.afiperu.syncmodel.SyncKid;
import com.afiperu.util.NetworkManager;

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
    public KidInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }


    @Override
    public void getAllKids(final KidPresenter presenter, Context context) {
        if (NetworkManager.isNetworkConnected(context)) { // Si tengo conexion a internet
            Call<List<Kid>> call = service.getAllKids();
            call.enqueue(new Callback<List<Kid>>() {
                @Override
                public void onResponse(Response<List<Kid>> response, Retrofit retrofit) {
                    List<Kid> kids = response.body();

                    if (kids != null) {
                        SyncKid.deleteAll(SyncKid.class, "attendance_child = 0");
                        for (Kid kid : kids) {
                            SyncKid su = SyncKid.fromKid(kid);
                            su.save();
                        }

                        List<SyncKid> lista = SyncKid.find(SyncKid.class, "attendance_child = 0");
                        Collections.sort(lista);
                        presenter.onUsersFound(lista);
                    }else{
                        presenter.onFailure();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onFailure();
                }
            });
        }else{// Si no tengo conexion
            List<SyncKid> lista = SyncKid.find(SyncKid.class, "attendance_child = 0");
            Collections.sort(lista);
            presenter.onUsersFound(lista);
        }
    }

    @Override
    public void queryKids(final KidPresenter presenter, Context context, final String query) {

            List<SyncKid> lista = SyncKid.find(SyncKid.class, "attendance_child = 0");

            List<SyncKid> listafinal = new ArrayList<SyncKid>();
            for (SyncKid item : lista){
                if (item.getNames().toLowerCase().contains(query)){
                    listafinal.add(item);
                }
            }


            Collections.sort(listafinal);
            presenter.onUsersFound(listafinal);

    }

    @Override
    public void queryAdvancedKid(Context context, KidPresenter presenter, String name, String edadini, String edadfin, String genero) {
        List<SyncKid> lista = SyncKid.find(SyncKid.class, "attendance_child = 0");
        Integer edadfrom=0;
        Integer edadto=5000;
        Integer gender = 2;
        try {
            edadfrom = Integer.parseInt(edadini);

        }catch (Exception e){

        }


        try {

            edadto = Integer.parseInt(edadfin);

        }catch (Exception e){

        }
        try {

            if (genero.contentEquals("Cualquiera"))
                gender = 2;
            else if (genero.contentEquals("Masculino"))
                gender = 0;
            else gender = 1;
        }catch (Exception e){

        }


        List<SyncKid> listafinal = new ArrayList<SyncKid>();
        for (SyncKid item : lista){
            String fullname = item.getNames();
            boolean bName = fullname.toLowerCase().contains(name.toLowerCase());
            boolean bEdadini;
            if (edadini.contentEquals("")) edadfrom=0;
            bEdadini= edadfrom <= item.getAge();

            boolean bEdadfin;
            if (edadfin.contentEquals(""))
                edadto=5000;
            bEdadfin= edadto >= item.getAge();


            boolean bGender;
            if (gender==2) bGender=true;
            else if (gender==item.getGender()) bGender=true;
            else bGender=false;

            //boolean
            if (bName&&bEdadini&&bEdadfin&&bGender){
                listafinal.add(item);
            }
        }


        Collections.sort(listafinal);
        presenter.onUsersFound(listafinal);
    }
}
