package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerChangePasswordComponent;
import com.example.dp2.afiperu.module.ChangePasswordModule;
import com.example.dp2.afiperu.presenter.ChangePasswordPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.adapter.ChangePasswordAdapter;
import com.example.dp2.afiperu.ui.viewmodel.ChangePasswordView;

import javax.inject.Inject;


public class ChangePasswordFragment extends BaseFragment implements ChangePasswordView {

    @Inject
    ChangePasswordAdapter changePasswordAdapter;
    @Inject
    ChangePasswordPresenter presenter;

    EditText currentPassword;
    EditText newPassword;
    EditText newPasswordRepeat;

    public ChangePasswordFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.change_password;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        Button chanPwd=(Button)rootView.findViewById(R.id.changepassword);
        currentPassword=(EditText)rootView.findViewById(R.id.actpassword);
        newPassword=(EditText)rootView.findViewById(R.id.newpassword);
        newPasswordRepeat=(EditText)rootView.findViewById(R.id.newpassword2);
        chanPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cPwd=currentPassword.getText().toString();
                String nPwd=newPassword.getText().toString();
                String nPWdR=newPasswordRepeat.getText().toString();
                if(nPwd.equals(nPWdR))
                    if(nPwd.length() >= 8) {
                        presenter.changePassword(getContext(), cPwd, nPwd);
                    }else{
                        Toast.makeText(getContext(), getString(R.string.password_must_have_8), Toast.LENGTH_SHORT).show();
                    }
                else
                    Toast.makeText(getContext(), getString(R.string.password_not_same), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerChangePasswordComponent.builder()
                .afiAppComponent(appComponent)
                .changePasswordModule(new ChangePasswordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setupAdapter() {

    }

    @Override
    public void displayPasswordChangedSuccess() {
        Toast.makeText(getContext(), "Se ha actualizado su contraseña", Toast.LENGTH_SHORT).show();
        ((DetailActivity)getActivity()).goBack();
    }

    @Override
    public void displayPasswordChangedError() {
        Toast.makeText(getContext(), "Su contraseña actual es errónea", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayPasswordChangedFailure() {
        Toast.makeText(getContext(), "No se pudo cambiar su contraseña", Toast.LENGTH_SHORT).show();
        ((DetailActivity)getActivity()).goBack();
    }
}
