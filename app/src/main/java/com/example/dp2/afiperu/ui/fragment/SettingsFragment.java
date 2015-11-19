package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.Constants;

import java.util.List;

/**
 * Created by Fernando on 19/10/2015.
 */
public class SettingsFragment extends BaseFragment {

    public static final String NOTIFICATIONS_KEY_CALENDAR = "cal";
    public static final String NOTIFICATIONS_KEY_EVENTS = "eve";
    public static final String NOTIFICATIONS_KEY_PAYMENTS = "pay";

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

        final ImageView calendarButton = (ImageView)rootView.findViewById(R.id.config_calendar_button);
        calendarButton.setImageResource(
                PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(NOTIFICATIONS_KEY_CALENDAR, true)
                ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
        );
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                boolean notify = preferences.getBoolean(NOTIFICATIONS_KEY_CALENDAR, true);
                SharedPreferences.Editor editor = preferences.edit();
                if(notify){
                    editor.putBoolean(NOTIFICATIONS_KEY_CALENDAR, false);
                    calendarButton.setImageResource(R.drawable.ic_unchecked_checkbox);
                }else{
                    editor.putBoolean(NOTIFICATIONS_KEY_CALENDAR, true);
                    calendarButton.setImageResource(R.drawable.ic_checked_checkbox);
                }
            }
        });

        if(!DetailActivity.isOnlyGodfather(Constants.loggedUser.getProfiles())){
            final ImageView eventButton = (ImageView)rootView.findViewById(R.id.config_event_button);
            eventButton.setImageResource(
                    PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(NOTIFICATIONS_KEY_EVENTS, true)
                    ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
            );
            eventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    boolean notify = preferences.getBoolean(NOTIFICATIONS_KEY_EVENTS, true);
                    SharedPreferences.Editor editor = preferences.edit();
                    if(notify){
                        editor.putBoolean(NOTIFICATIONS_KEY_EVENTS, false);
                        eventButton.setImageResource(R.drawable.ic_unchecked_checkbox);
                    }else{
                        editor.putBoolean(NOTIFICATIONS_KEY_EVENTS, true);
                        eventButton.setImageResource(R.drawable.ic_checked_checkbox);
                    }
                }
            });
        }else{
            RelativeLayout eventsLine = (RelativeLayout)rootView.findViewById(R.id.config_events_line);
            eventsLine.setVisibility(View.GONE);
        }

        if(DetailActivity.isGodfather(Constants.loggedUser.getProfiles())){
            final ImageView paymentButton = (ImageView)rootView.findViewById(R.id.config_payment_button);
            paymentButton.setImageResource(
                    PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(NOTIFICATIONS_KEY_PAYMENTS, true)
                            ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox
            );
            paymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    boolean notify = preferences.getBoolean(NOTIFICATIONS_KEY_PAYMENTS, true);
                    SharedPreferences.Editor editor = preferences.edit();
                    if(notify){
                        editor.putBoolean(NOTIFICATIONS_KEY_PAYMENTS, false);
                        paymentButton.setImageResource(R.drawable.ic_unchecked_checkbox);
                    }else{
                        editor.putBoolean(NOTIFICATIONS_KEY_PAYMENTS, true);
                        paymentButton.setImageResource(R.drawable.ic_checked_checkbox);
                    }
                }
            });
        }else{
            RelativeLayout paymentLine = (RelativeLayout)rootView.findViewById(R.id.config_payment_line);
            paymentLine.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
