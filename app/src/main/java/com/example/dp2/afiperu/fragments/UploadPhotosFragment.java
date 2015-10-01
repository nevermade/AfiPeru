package com.example.dp2.afiperu.fragments;

import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.BlogArticleItem;

/**
 * Created by Fernando on 23/09/2015.
 */
public class UploadPhotosFragment extends BaseFragment{

    public UploadPhotosFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.upload_photos;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ImageView emptyPhotoPic = (ImageView)rootView.findViewById(R.id.photo_pic);
    }

}
