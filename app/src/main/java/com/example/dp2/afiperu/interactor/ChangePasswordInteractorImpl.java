package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.presenter.ChangePasswordPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.rest.model.SuccessBody;
import com.example.dp2.afiperu.util.NetworkManager;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by Nevermade on 12/10/2015.
 */
public class ChangePasswordInteractorImpl implements ChangePasswordInteractor {

    AfiApiServiceEndPoints service;

    public ChangePasswordInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    @Override
    public void changePassword(Context context, String currentPw, String newPw, final ChangePasswordPresenter presenter) {
        if(NetworkManager.isNetworkConnected(context)) {
            Call<SuccessBody> call = service.changePassword(currentPw, newPw);
            call.enqueue(new Callback<SuccessBody>() {
                @Override
                public void onResponse(retrofit.Response<SuccessBody> response, Retrofit retrofit) {
                    if (response.body() != null && response.body().getError() == null) {
                        presenter.onPasswordChangedSuccess();
                    } else {
                        presenter.onPasswordChangedError(response.body().getError());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    presenter.onPasswordChangedFailure();
                }
            });
        }else{
            presenter.onNoInternet(context);
        }
    }


    /*
    @Override
    public ArrayList<Blog> getAllBlogs() {
        Calendar calendar= new GregorianCalendar();
        ArrayList<Blog> blogs= new ArrayList<>();
        calendar=new GregorianCalendar(2015,8,22,14,25);
        blogs.add(new Blog("Titulo 1","Daekef Abarca",calendar.getTime().getTime(),false));
        calendar=new GregorianCalendar(2015,7,24,48,27);
        blogs.add(new Blog("Titulo 2","Fernando Banda",calendar.getTime().getTime(),true));
        calendar=new GregorianCalendar(2015,6,15,9,45);
        blogs.add(new Blog("Titulo 3","Luis Barcena",calendar.getTime().getTime(),false));
        Collections.sort(blogs);
        return blogs;
    }*/
}
