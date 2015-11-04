package com.example.dp2.afiperu.module;

import android.content.Context;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.interactor.PeriodReportInteractor;
import com.example.dp2.afiperu.presenter.PeriodReportPresenter;
import com.example.dp2.afiperu.ui.adapter.PeriodReportAdapter;
import com.example.dp2.afiperu.ui.viewmodel.PeriodReportView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nevermade on 20/10/2015.
 */
@Module
public class PeriodReportModule {

    private PeriodReportView view;

    public PeriodReportModule(PeriodReportView view) {
        this.view = view;
    }

    @Provides
    public PeriodReportView provideView(){
        return view;
    }

    @Provides
    public PeriodReportPresenter providePresenter(PeriodReportView view, PeriodReportInteractor interactor){
        return new PeriodReportPresenter(interactor, view);
    }

    @Provides
    public PeriodReportAdapter provideAdapter(Context context, PeriodReportView view){
        return new PeriodReportAdapter(((BaseFragment)view).getActivity(), (BaseFragment)view, new ArrayList<Document>());
    }

}
