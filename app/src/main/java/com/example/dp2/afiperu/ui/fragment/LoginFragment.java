package com.example.dp2.afiperu.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerLoginComponent;
import com.example.dp2.afiperu.module.LoginModule;
import com.example.dp2.afiperu.presenter.LoginPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.viewmodel.LoginView;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class LoginFragment extends BaseFragment implements LoginView {


    @Inject
    LoginPresenter presenter;

    View rooView;
    EditText username;
    EditText password;

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
        DaggerLoginComponent.builder()
                .afiAppComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showApp(String name) {
        DetailActivity activity = ((DetailActivity) getActivity());
        Toast.makeText(getContext(), "Bienvenido "+ name, Toast.LENGTH_SHORT).show();
        activity.selectItem(DetailActivity.FRAGMENT_NOTICIAS);
        activity.hideAppElements(false);
        hideSoftKeyboard();
    }
    @Override
    public void displayLoginError() {
        Toast.makeText(getContext(), "Usuario o contraseña inválido", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){

        Button loginBtn=(Button)rootView.findViewById(R.id.login_btn);
        username=(EditText)rootView.findViewById(R.id.login_username);
        password=(EditText)rootView.findViewById(R.id.login_password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void hideSoftKeyboard() {
        if(getActivity().getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}
