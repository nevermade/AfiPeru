package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.CommentModule;
import com.example.dp2.afiperu.presenter.CommentPresenter;
import com.example.dp2.afiperu.ui.adapter.CommentAdapter;
import com.example.dp2.afiperu.ui.fragment.CommentFragment;

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
