package com.example.dp2.afiperu.interactor;

import android.content.Context;

import com.example.dp2.afiperu.domain.User;

/**
 * Created by Fernando on 22/11/2015.
 */
public interface SettingsInteractor {
    void setPushSettings(Context context, User user);
}
