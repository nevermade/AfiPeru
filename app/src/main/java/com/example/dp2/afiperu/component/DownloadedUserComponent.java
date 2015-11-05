package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.DownloadedUserModule;
import com.example.dp2.afiperu.ui.adapter.DownloadedUserAdapter;
import com.example.dp2.afiperu.ui.fragment.DownloadedUserFragment;

import dagger.Component;

/**
 * Created by Nevermade on 20/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = DownloadedUserModule.class
)
public interface DownloadedUserComponent {
    void inject(DownloadedUserFragment fragment);
    DownloadedUserAdapter getAdapter();
}
