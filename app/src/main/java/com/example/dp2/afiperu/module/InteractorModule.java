package com.example.dp2.afiperu.module;

import com.example.dp2.afiperu.interactor.BlogSearchInteractor;
import com.example.dp2.afiperu.interactor.BlogSearchInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentListInteractorImpl;
import com.example.dp2.afiperu.interactor.PaymentListInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 12/10/2015.
 */
/* Se necesita un interactor por cada presenter */
@Module
public class InteractorModule {
    @Provides
    public BlogSearchInteractor provideSearchInteractor(){
        return new BlogSearchInteractorImpl();
    }

    @Provides
    public PaymentListInteractor providePaymentListInteractor(){return new PaymentListInteractorImpl();}
}
