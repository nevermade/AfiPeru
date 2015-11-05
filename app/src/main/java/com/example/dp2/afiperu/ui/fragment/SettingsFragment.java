package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.Constants;

/**
 * Created by Fernando on 19/10/2015.
 */
public class SettingsFragment extends BaseFragment {

    @Override
    public int getLayout() {
        return R.layout.config;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        TextView changePassword = (TextView)rootView.findViewById(R.id.config_change_password_button);
        TextView signOut = (TextView)rootView.findViewById(R.id.config_sign_out_button);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_CONFIGURACIÓN);
                ChangePasswordFragment passwordFragment = new ChangePasswordFragment();
                passwordFragment.setArguments(args);
                addFragmentToStack(passwordFragment, DetailActivity.FRAGMENT_CONFIGURACIÓN);
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.loggedUser=null;
                ((DetailActivity) getActivity()).getSharedPreferences().edit().remove("loggedUser").commit();
                ((DetailActivity) getActivity()).selectItem(DetailActivity.FRAGMENT_LOGIN);
            }
        });
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
