package com.example.dp2.afiperu.ui.fragment;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;

/**
 * Created by Fernando on 23/09/2015.
 */
public class LoginFragment extends BaseFragment {

    public LoginFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.login;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

}
