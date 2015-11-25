package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.CommentKidModule;
import com.afiperu.presenter.CommentKidPresenter;
import com.afiperu.ui.adapter.CommentKidAdapter;
import com.afiperu.ui.fragment.CommentKidFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = CommentKidModule.class
)
public interface CommentKidComponent {
    void inject(CommentKidFragment fragment);
    CommentKidAdapter getAdapter();
    CommentKidPresenter getPresenter();
}
