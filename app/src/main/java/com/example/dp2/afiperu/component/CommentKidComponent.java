package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.CommentKidModule;
import com.example.dp2.afiperu.presenter.CommentKidPresenter;
import com.example.dp2.afiperu.ui.adapter.CommentKidAdapter;
import com.example.dp2.afiperu.ui.fragment.CommentKidFragment;

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
