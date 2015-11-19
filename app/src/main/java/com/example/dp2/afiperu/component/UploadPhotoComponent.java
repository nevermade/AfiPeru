package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.module.UploadPhotoModule;
import com.example.dp2.afiperu.presenter.UploadPhotoPresenter;
import com.example.dp2.afiperu.ui.fragment.UploadPhotosFragment;

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
