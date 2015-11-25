package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.module.UploadPhotoModule;
import com.afiperu.presenter.UploadPhotoPresenter;
import com.afiperu.ui.fragment.UploadPhotosFragment;

import dagger.Component;

/**
 * Created by DABARCA on 21/10/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = UploadPhotoModule.class
)
public interface UploadPhotoComponent {
    void inject(UploadPhotosFragment fragment);
    UploadPhotoPresenter getPresenter();
}
