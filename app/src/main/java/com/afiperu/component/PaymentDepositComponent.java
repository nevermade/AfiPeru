package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.PaymentDepositModule;
import com.afiperu.presenter.PaymentDepositPresenter;
import com.afiperu.ui.fragment.PaymentDepositFragment;

import dagger.Component;

/**
 * Created by DABARCA on 19/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = PaymentDepositModule.class
)
public interface PaymentDepositComponent {
    void inject(PaymentDepositFragment fragment);
    PaymentDepositPresenter getPresenter();
}
