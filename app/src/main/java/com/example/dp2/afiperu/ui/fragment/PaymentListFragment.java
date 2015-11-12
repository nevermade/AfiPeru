package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerPaymentListComponent;
import com.example.dp2.afiperu.module.PaymentListModule;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;
import com.example.dp2.afiperu.syncmodel.SyncPayment;
import com.example.dp2.afiperu.ui.adapter.PaymentListAdapter;
import com.example.dp2.afiperu.ui.viewmodel.PaymentListView;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.List;

import javax.inject.Inject;

public class PaymentListFragment extends BaseFragment implements PaymentListView {

    @Inject
    PaymentListPresenter paymentListPresenter;
    @Inject
    PaymentListAdapter paymentListAdapter;

    @Override
    public int getLayout() {
        return R.layout.payments;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView paymentList=(ListView)rootView.findViewById(R.id.payment_list);
        paymentList.setAdapter(paymentListAdapter);
        paymentList.setEmptyView(rootView.findViewById(R.id.empty_payments_list));
        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }
        paymentListPresenter.getAllPayments(getContext());
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerPaymentListComponent.builder()
                .afiAppComponent(appComponent)
                .paymentListModule(new PaymentListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    public void displayPayments(List<SyncPayment> payments){
        paymentListAdapter.update(payments);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void displayNoPaymentsFound(){
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

}
