package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerChangePasswordComponent;
import com.afiperu.module.ChangePasswordModule;
import com.afiperu.presenter.ChangePasswordPresenter;
import com.afiperu.ui.activity.DetailActivity;
import com.afiperu.ui.viewmodel.ChangePasswordView;

import javax.inject.Inject;


public class ChangePasswordFragment extends BaseFragment implements ChangePasswordView {

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
    public void displayPasswordChangedError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message)
                .setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void displayPasswordChangedFailure() {
        Toast.makeText(getContext(), "No se pudo cambiar su contraseña", Toast.LENGTH_SHORT).show();
        ((DetailActivity)getActivity()).goBack();
    }
}
