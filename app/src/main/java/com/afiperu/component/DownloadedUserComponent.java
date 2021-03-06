package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.DownloadedUserModule;
import com.afiperu.ui.adapter.DownloadedUserAdapter;
import com.afiperu.ui.fragment.DownloadedUserFragment;

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
