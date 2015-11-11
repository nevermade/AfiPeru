package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;

import java.util.List;

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
                boolean needSync = false;
                List<SyncComment> comments = SyncComment.find(SyncComment.class, "needsync = ?", "1");
                if(!comments.isEmpty()){
                    needSync = true;
                }

                if(needSync){
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == DialogInterface.BUTTON_POSITIVE){
                                ((DetailActivity) getActivity()).logOff();
                            }else if(which == DialogInterface.BUTTON_NEGATIVE){
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.data_not_synced)
                            .setPositiveButton(android.R.string.yes, dialogClickListener)
                            .setNegativeButton(android.R.string.no, dialogClickListener);
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    ((DetailActivity) getActivity()).logOff();
                }
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
