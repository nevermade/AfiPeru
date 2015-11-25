package com.afiperu.module;

import com.afiperu.ui.fragment.SettingsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DABARCA on 21/10/2015.
 */
@Module
public class SettingsModule {
    private SettingsFragment view;

    public SettingsModule(SettingsFragment view) {
        this.view = view;
    }

    @Provides
    public SettingsFragment provideView(){
        return view;
    }
}
