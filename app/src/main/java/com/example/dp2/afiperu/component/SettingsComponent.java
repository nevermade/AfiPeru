package com.example.dp2.afiperu.component;

import com.example.dp2.afiperu.ActivityScope;
import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.interactor.SettingsInteractor;
import com.example.dp2.afiperu.module.SettingsModule;
import com.example.dp2.afiperu.ui.fragment.SettingsFragment;

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