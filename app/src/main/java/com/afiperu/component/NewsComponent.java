package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.NewsModule;
import com.afiperu.presenter.NewsPresenter;
import com.afiperu.ui.adapter.NewsAdapter;
import com.afiperu.ui.fragment.NewsFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = NewsModule.class
)
public interface NewsComponent {
    void inject(NewsFragment fragment);
    NewsAdapter getAdapter();
    NewsPresenter getPresenter();
}
