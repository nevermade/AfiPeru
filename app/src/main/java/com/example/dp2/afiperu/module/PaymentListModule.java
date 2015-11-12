package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;
import com.example.dp2.afiperu.syncmodel.SyncPayment;
import com.example.dp2.afiperu.ui.adapter.PaymentListAdapter;
import com.example.dp2.afiperu.ui.viewmodel.PaymentListView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 17/10/2015.
 */
@Module
public class PaymentListModule {

    private PaymentListView view;

    public PaymentListModule(PaymentListView view) {
        this.view = view;
    }

    @Provides
    public PaymentListView provideView() {
        return view;
    }

    @Provides
    public PaymentListPresenter providePresenter(PaymentListView view, PaymentListInteractor interactor) {
        return new PaymentListPresenter(view, interactor);
    }

    @Provides
    public PaymentListAdapter provideAdapter(Context context, PaymentListView view){
        return new PaymentListAdapter(((BaseFragment)view).getActivity(),(BaseFragment) view, new ArrayList<SyncPayment>());
    }

}
