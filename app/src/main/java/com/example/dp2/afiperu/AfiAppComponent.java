package com.example.dp2.afiperu;

import android.content.Context;

import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.module.InteractorModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nevermade on 11/10/2015.
 */
@Singleton
@Component(
        modules = {
                AfiAppModule.class,
                InteractorModule.class
        }
)
public interface AfiAppComponent {
        Context getContext();
        BlogSearchInteractor getBlogSearchInteractor();
}
