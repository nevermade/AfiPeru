package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.interactor.BlogSearchInteractorImpl;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractor;
import com.example.dp2.afiperu.interactor.ChangePasswordInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
@Module
public class InteractorModule {
    @Provides
    public BlogSearchInteractor provideSearchInteractor(){
        return new BlogSearchInteractorImpl();
    }
    @Provides
    public ChangePasswordInteractor provideChangePasswordInteractor(){
        return new ChangePasswordInteractorImpl();
    }
}
