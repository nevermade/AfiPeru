package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.UploadPhotoInteractor;
import com.example.dp2.afiperu.presenter.UploadPhotoPresenter;
import com.example.dp2.afiperu.ui.viewmodel.UploadPhotoView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class UploadPhotoModule {
    private UploadPhotoView view;

    public UploadPhotoModule(UploadPhotoView view) {
        this.view = view;
    }

    @Provides
    public UploadPhotoView provideView(){
        return view;
    }

    @Provides
    public UploadPhotoPresenter providePresenter(UploadPhotoView view, UploadPhotoInteractor interactor){
        return new UploadPhotoPresenter(interactor,view);
    }
}
