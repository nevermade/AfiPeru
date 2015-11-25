package com.afiperu.component;

import com.afiperu.ActivityScope;
import com.afiperu.AfiAppComponent;
import com.afiperu.interactor.SettingsInteractor;
import com.afiperu.module.SettingsModule;
import com.afiperu.ui.fragment.SettingsFragment;

import dagger.Component;

/**
 * Created by Fernando on 22/11/2015.
 */
@ActivityScope
@Component(
        dependencies = AfiAppComponent.class,
        modules = SettingsModule.class
)
public interface SettingsComponent {
    void inject(SettingsFragment fragment);
    SettingsInteractor getInteractor();
}