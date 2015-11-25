package com.afiperu.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.afiperu.R;
import com.afiperu.common.BasePresenter;
import com.afiperu.interactor.UploadPhotoInteractor;
import com.afiperu.ui.viewmodel.UploadPhotoView;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.MediaType;

import java.io.File;

/**
 * Created by Fernando on 13/11/2015.
 */
public class UploadPhotoPresenter extends BasePresenter {
    UploadPhotoInteractor interactor;
    UploadPhotoView view;

    public UploadPhotoPresenter(UploadPhotoInteractor interactor, UploadPhotoView view){
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void uploadPhoto(Context context, String file){
        File photoFile = new File(file);
        if(photoFile.exists()) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
            interactor.uploadPhoto(this, context, body);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.file_not_found).setNeutralButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void photoUploadSuccess(){
        view.uploadPhotoSuccess();
    }

    public void photoUploadFailure(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(onNoInternetString()).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
