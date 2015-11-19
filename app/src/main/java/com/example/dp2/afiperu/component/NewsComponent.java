package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.NewsModule;
import com.example.dp2.afiperu.presenter.NewsPresenter;
import com.example.dp2.afiperu.ui.adapter.NewsAdapter;
import com.example.dp2.afiperu.ui.fragment.NewsFragment;

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
