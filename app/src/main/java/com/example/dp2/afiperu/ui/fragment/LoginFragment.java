package com.example.dp2.afiperu.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
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
import com.example.dp2.afiperu.util.Constants;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class LoginFragment extends BaseFragment implements LoginView {


    @Inject
    LoginPresenter presenter;

    EditText username;
    EditText password;
    TextView recoverpass;
    ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    View rootView;
    public LoginFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.login;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
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
        activity.setActions(Constants.loggedUser);
        Toast.makeText(activity.getApplicationContext(), "Bienvenido "+ name, Toast.LENGTH_SHORT).show();
        activity.selectItem(DetailActivity.FRAGMENT_NOTICIAS);
        activity.hideAppElements(false);
        hideSoftKeyboard();
        SharedPreferences.Editor editor = ((DetailActivity) getActivity()).getSharedPreferences().edit();
        Gson gson= new Gson();
        editor.putString("loggedUser", gson.toJson(Constants.loggedUser));
        editor.commit();
        getView().getViewTreeObserver().removeGlobalOnLayoutListener(layoutListener);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
    @Override
    public void displayLoginError() {
        Toast.makeText(getContext(), "Usuario o contraseña inválido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRecoverPassSuccess() {
        Toast.makeText(getContext(), "Se ha enviado una nueva contraseña a su correo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRecoverPassFailure() {
        Toast.makeText(getContext(), "No se puedo reestablecer su contraseña", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void prepareView(final View rootView, Bundle args, Bundle savedInstanceState) {

        Button loginBtn = (Button) rootView.findViewById(R.id.login_btn);
        username = (EditText) rootView.findViewById(R.id.login_username);
        password = (EditText) rootView.findViewById(R.id.login_password);
        recoverpass = (TextView)rootView.findViewById(R.id.button_recoverpass);

        layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int defHeihgt=rootView.getRootView().getHeight();
                int heightDiff = defHeihgt - rootView.getHeight();
                if (heightDiff > defHeihgt*0.20) { // if more than 100 pixels, its probably a keyboard...
                    recoverpass.setVisibility(View.GONE);
                }else
                    recoverpass.setVisibility(View.VISIBLE);
            }
        };

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(username.getText().toString(), password.getText().toString());
            }
        });
        recoverpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dView=getActivity().getLayoutInflater().inflate(R.layout.recover_password, null);

                final AlertDialog d = new AlertDialog.Builder(getContext())
                        .setView(dView)
                        .setTitle("Recuperar contraseña")
                        .setPositiveButton("Aceptar", null) //Set to null. We override the onclick
                        .setNegativeButton("Cancelar", null)
                        .create();
                d.getWindow().setBackgroundDrawableResource(R.color.main_background);
                d.show();
                d.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = ((EditText) dView.findViewById(R.id.txtEmail)).getText().toString();
                        if (isValid(email)) {
                            presenter.recoverPass(email);
                            d.dismiss();
                        }
                    }

                    private boolean isValid(String email) {
                        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                        return matcher.find();
                    }
                });
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
