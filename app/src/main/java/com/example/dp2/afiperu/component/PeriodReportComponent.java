package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.PeriodReportModule;
import com.example.dp2.afiperu.presenter.PeriodReportPresenter;
import com.example.dp2.afiperu.ui.adapter.PeriodReportAdapter;
import com.example.dp2.afiperu.ui.fragment.PeriodReportFragment;

import dagger.Component;

/**
 * Created by Nevermade on 20/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = PeriodReportModule.class
)
public interface PeriodReportComponent {
    void inject(PeriodReportFragment fragment);
    PeriodReportAdapter getAdapter();
    PeriodReportPresenter getPresenter();
}
