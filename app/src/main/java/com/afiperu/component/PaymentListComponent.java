package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.PaymentListModule;
import com.afiperu.presenter.PaymentListPresenter;
import com.afiperu.ui.adapter.PaymentListAdapter;
import com.afiperu.ui.fragment.PaymentListFragment;

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
