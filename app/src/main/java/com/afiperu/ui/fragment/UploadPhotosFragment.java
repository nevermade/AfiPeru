package com.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerUploadPhotoComponent;
import com.afiperu.module.UploadPhotoModule;
import com.afiperu.presenter.UploadPhotoPresenter;
import com.afiperu.ui.viewmodel.UploadPhotoView;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class UploadPhotosFragment extends BaseFragment implements UploadPhotoView {

    @Inject
    UploadPhotoPresenter presenter;

    public UploadPhotosFragment(){
        super();
    }

    private Bitmap capturedBitmap;
    private String path;

    @Override
    public int getLayout(){
        return R.layout.upload_photos;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        LinearLayout button = (LinearLayout)rootView.findViewById(R.id.upload_photo_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capturedBitmap != null && path != null){
                    AlertDialog.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.uploadPhoto(getContext(), path);
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.upload_photo_prompt)
                            .setPositiveButton(android.R.string.yes, listener)
                            .setNegativeButton(android.R.string.no, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.no_photo_message)
                            .setNeutralButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    public void updateBitmap(Bitmap bitmap, String filePath){
        if(getView() != null) {
            if (bitmap != null) {
                path = filePath;
                if(capturedBitmap != null){
                    capturedBitmap.recycle();
                }
                capturedBitmap = bitmap;
                ImageView iv = (ImageView) getView().findViewById(R.id.photo_pic);
                iv.setImageBitmap(bitmap);
                iv.setAlpha(1f);
                TextView tv = (TextView) getView().findViewById(R.id.no_photo_text);
                tv.setText("");

                TextView buttonText = (TextView) getView().findViewById(R.id.send_icon_message);
                buttonText.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_text));
                ImageView buttonImage = (ImageView) getView().findViewById(R.id.send_icon);
                buttonImage.setImageResource(R.drawable.ic_big_send);
            } else {
                path = null;
                ImageView iv = (ImageView) getView().findViewById(R.id.photo_pic);
                iv.setImageResource(R.drawable.no_photo);
                iv.setAlpha(0.15f);
                TextView tv = (TextView) getView().findViewById(R.id.no_photo_text);
                tv.setText(getString(R.string.no_photo_message));

                TextView buttonText = (TextView) getView().findViewById(R.id.send_icon_message);
                buttonText.setTextColor(ContextCompat.getColor(getContext(), R.color.list_view_divider));
                ImageView buttonImage = (ImageView) getView().findViewById(R.id.send_icon);
                buttonImage.setImageResource(R.drawable.ic_big_send_soft);
            }
        }
    }

    @Override
    public void uploadPhotoSuccess(){
        updateBitmap(null, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.photo_success)
                .setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean tryBack(){
        if(capturedBitmap != null){
            ImageView iv = (ImageView) getView().findViewById(R.id.photo_pic);
            iv.setImageResource(R.drawable.no_photo);
            iv.setAlpha(0.15f);
            capturedBitmap.recycle();
            capturedBitmap = null;
            path = null;
        }
        return true;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerUploadPhotoComponent.builder()
                .afiAppComponent(appComponent)
                .uploadPhotoModule(new UploadPhotoModule(this))
                .build()
                .inject(this);
    }

}
