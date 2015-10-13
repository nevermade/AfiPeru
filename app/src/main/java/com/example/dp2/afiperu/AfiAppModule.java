package com.example.dp2.afiperu;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 11/10/2015.
 */
@Module
public class AfiAppModule {
    private AfiApp app;

    public AfiAppModule(AfiApp app) {
        this.app = app;
    }

    @Provides @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides @Singleton public Context provideContext() {
        return app;
    }


}
