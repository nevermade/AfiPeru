package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerSettingsComponent;
import com.example.dp2.afiperu.interactor.SettingsInteractor;
import com.example.dp2.afiperu.module.SessionModule;
import com.example.dp2.afiperu.module.SettingsModule;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 19/10/2015.
 */
public class SettingsFragment extends BaseFragment {

    @Inject
    SettingsInteractor interactor;

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
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.data_not_synced)
                            .setPositiveButton(android.R.string.yes, dialogClickListener)
                            .setNegativeButton(android.R.string.no, null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    ((DetailActivity) getActivity()).logOff();
                }
            }
        });

        //Notification buttons
        if(AppEnum.EnumAction.SESSION_AND_DOCUMENTS.hasPermission(Constants.loggedUser)){
            final ImageView eventButton = (ImageView)rootView.findViewById(R.id.config_event_button);
            eventButton.setImageResource(
                    Constants.loggedUser.getPushEvents() == 1 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
            );
            eventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int notify = Constants.loggedUser.getPushEvents();
                    Constants.loggedUser.setPushEvents(notify == 1 ? 0 : 1);
                    interactor.setPushSettings(getContext(), Constants.loggedUser);
                    eventButton.setImageResource(notify == 0 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
                }
            });
            final ImageView documentsButton = (ImageView)rootView.findViewById(R.id.config_documents_button);
            documentsButton.setImageResource(
                    Constants.loggedUser.getPushDocuments() == 1 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
            );
            documentsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int notify = Constants.loggedUser.getPushDocuments();
                    Constants.loggedUser.setPushDocuments(notify == 1 ? 0 : 1);
                    interactor.setPushSettings(getContext(), Constants.loggedUser);
                    documentsButton.setImageResource(notify == 0 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
                }
            });
        }else{
            RelativeLayout eventsLine = (RelativeLayout)rootView.findViewById(R.id.config_events_line);
            eventsLine.setVisibility(View.GONE);
            RelativeLayout documentsLine = (RelativeLayout)rootView.findViewById(R.id.config_document_line);
            documentsLine.setVisibility(View.GONE);
        }

        if(AppEnum.EnumAction.PAYMENT.hasPermission(Constants.loggedUser) && DetailActivity.isGodfather(Constants.loggedUser.getProfiles())){
            final ImageView paymentButton = (ImageView)rootView.findViewById(R.id.config_payment_button);
            paymentButton.setImageResource(
                    Constants.loggedUser.getPushFees() == 1 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
            );
            paymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int notify = Constants.loggedUser.getPushFees();
                    Constants.loggedUser.setPushFees(notify == 1 ? 0 : 1);
                    interactor.setPushSettings(getContext(), Constants.loggedUser);
                    paymentButton.setImageResource(notify == 0 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
                }
            });
        }else{
            RelativeLayout paymentLine = (RelativeLayout)rootView.findViewById(R.id.config_payment_line);
            paymentLine.setVisibility(View.GONE);
        }

        if(AppEnum.EnumAction.LIST_PERIOD_REPORT.hasPermission(Constants.loggedUser)){
            final ImageView reportButton = (ImageView)rootView.findViewById(R.id.config_reports_button);
            reportButton.setImageResource(
                    Constants.loggedUser.getPushReports() == 1 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
            );
            reportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int notify = Constants.loggedUser.getPushReports();
                    Constants.loggedUser.setPushReports(notify == 1 ? 0 : 1);
                    interactor.setPushSettings(getContext(), Constants.loggedUser);
                    reportButton.setImageResource(notify == 0 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
                }
            });
        }
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerSettingsComponent.builder()
                .afiAppComponent(appComponent)
                .settingsModule(new SettingsModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
