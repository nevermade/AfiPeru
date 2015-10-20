package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.PaymentDepositModule;
import com.example.dp2.afiperu.presenter.PaymentDepositPresenter;
import com.example.dp2.afiperu.ui.fragment.PaymentDepositFragment;

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
