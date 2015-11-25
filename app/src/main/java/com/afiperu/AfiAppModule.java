package com.afiperu;

import android.app.Application;
import android.content.Context;

import com.afiperu.rest.AfiApiServiceAdapter;
import com.afiperu.rest.AfiApiServiceEndPoints;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

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

    @Provides @Singleton public Retrofit provideRetrofitAdapter(){
        return AfiApiServiceAdapter.getInstance();
    }

    @Provides @Singleton public AfiApiServiceEndPoints provideAfiApiServiceEndPoints(Retrofit adapter){
        return adapter.create(AfiApiServiceEndPoints.class);
    }
}
