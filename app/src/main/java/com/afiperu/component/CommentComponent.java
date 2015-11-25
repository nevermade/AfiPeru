package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.CommentModule;
import com.afiperu.presenter.CommentPresenter;
import com.afiperu.ui.adapter.CommentAdapter;
import com.afiperu.ui.fragment.CommentFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = CommentModule.class
)
public interface CommentComponent {
    void inject(CommentFragment fragment);
    CommentAdapter getAdapter();
    CommentPresenter getPresenter();
}
