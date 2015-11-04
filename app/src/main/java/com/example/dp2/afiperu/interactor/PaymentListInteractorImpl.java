package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.rest.AfiApiServiceEndPoints;
import com.example.dp2.afiperu.syncmodel.SyncUser;
import com.example.dp2.afiperu.util.AppEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nevermade on 17/10/2015.
 */
public class PaymentListInteractorImpl implements PaymentListInteractor {

    AfiApiServiceEndPoints service;

    public PaymentListInteractorImpl(AfiApiServiceEndPoints service) {
        this.service = service;
    }

    public PaymentListInteractorImpl(final PaymentListPresenter presenter, Context context) {
    }

    @Override
    public ArrayList<Payment> getAllPayments(final PaymentListPresenter presenter, Context context) throws ParseException {
        Call<List<Payment>> call = service.getAllPaymentCalendar();
        call.enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Response<List<Payment>> response, Retrofit retrofit) {
                ArrayList<Payment> payments = (ArrayList<Payment>) response.body();
                presenter.onPaymentsFound(payments);
            }
/*
                if (payments != null) {
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
                    ArrayList<SyncUser> listav = new ArrayList<>();

                    for (SyncUser user : lista) listav.add(user);
                    Collections.sort(listav);
                    presenter.onUsersFound(listav);
                    System.out.println(users.get(0).getName());


                } else System.out.println("NULL");
            }
*/
            @Override
            public void onFailure(Throwable t) {

            }
        });

        return null;
    }
}
