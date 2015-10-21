package com.example.dp2.afiperu.presenter;

import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.interactor.PeriodReportInteractor;
import com.example.dp2.afiperu.ui.viewmodel.PeriodReportView;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class PeriodReportPresenter extends BasePresenter {
    PeriodReportInteractor interactor;
    PeriodReportView view;

    public PeriodReportPresenter(PeriodReportInteractor interactor, PeriodReportView view) {
        this.interactor = interactor;
        this.view = view;
    }

    public void getAllDocuments(){
        view.displayDocuments(interactor.getAllPeriodReports());

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
