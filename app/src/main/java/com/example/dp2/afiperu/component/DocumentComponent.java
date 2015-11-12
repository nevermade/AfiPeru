package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.DocumentModule;
import com.example.dp2.afiperu.presenter.DocumentPresenter;
import com.example.dp2.afiperu.ui.adapter.DocumentsAdapter;
import com.example.dp2.afiperu.ui.fragment.DocumentsFragment;
import com.example.dp2.afiperu.ui.fragment.PeriodReportFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = DocumentModule.class
)
public interface DocumentComponent {
    void inject(DocumentsFragment fragment);
    void inject(PeriodReportFragment fragment);
    DocumentsAdapter getAdapter();
    DocumentPresenter getPresenter();
}
