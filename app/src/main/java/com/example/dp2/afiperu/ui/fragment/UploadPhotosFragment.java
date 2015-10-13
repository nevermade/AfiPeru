package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;

/**
 * Created by Fernando on 23/09/2015.
 */
public class UploadPhotosFragment extends BaseFragment {

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

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

}
