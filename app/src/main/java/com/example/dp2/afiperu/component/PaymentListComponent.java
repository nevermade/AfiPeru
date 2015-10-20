package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.PaymentListModule;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;
import com.example.dp2.afiperu.ui.adapter.PaymentListAdapter;
import com.example.dp2.afiperu.ui.fragment.PaymentListFragment;

import dagger.Component;

/**
 * Created by Nevermade on 17/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = PaymentListModule.class
)
public interface PaymentListComponent {
    void inject(PaymentListFragment paymentListFragment);
    PaymentListPresenter getPresenter();
    PaymentListAdapter getAdapter();
}
