package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.BlogSearchModule;
import com.example.dp2.afiperu.presenter.BlogSearchPresenter;
import com.example.dp2.afiperu.ui.adapter.BlogSearchAdapter;
import com.example.dp2.afiperu.ui.fragment.BlogSearchFragment;

import dagger.Component;

/**
 * Created by Nevermade on 12/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = BlogSearchModule.class
)
public interface BlogSearchComponent {
    void inject(BlogSearchFragment searchFragment);
    BlogSearchPresenter getPresenter();
    BlogSearchAdapter getAdapter();
}
