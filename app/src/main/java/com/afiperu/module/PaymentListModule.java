package com.afiperu.module;

import android.content.Context;

import com.afiperu.common.BaseFragment;
import com.afiperu.interactor.PaymentListInteractor;
import com.afiperu.presenter.PaymentListPresenter;
import com.afiperu.syncmodel.SyncPayment;
import com.afiperu.ui.adapter.PaymentListAdapter;
import com.afiperu.ui.viewmodel.PaymentListView;

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
