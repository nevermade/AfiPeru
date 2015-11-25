package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.UploadPhotoPresenter;
import com.squareup.okhttp.RequestBody;

/**
 * Created by Fernando on 13/11/2015.
 */
public interface UploadPhotoInteractor {
    void uploadPhoto(final UploadPhotoPresenter presenter, final Context context, RequestBody photo);
}
