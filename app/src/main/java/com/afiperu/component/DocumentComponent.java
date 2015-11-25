package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.DocumentModule;
import com.afiperu.presenter.DocumentPresenter;
import com.afiperu.ui.adapter.DocumentsAdapter;
import com.afiperu.ui.fragment.DocumentsFragment;
import com.afiperu.ui.fragment.PeriodReportFragment;

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
