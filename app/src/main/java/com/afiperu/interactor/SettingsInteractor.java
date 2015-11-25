package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.domain.User;

/**
 * Created by Fernando on 22/11/2015.
 */
public interface SettingsInteractor {
    void setPushSettings(Context context, User user);
}
