package com.afiperu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerPaymentListComponent;
import com.afiperu.module.PaymentListModule;
import com.afiperu.presenter.PaymentListPresenter;
import com.afiperu.syncmodel.SyncPayment;
import com.afiperu.ui.adapter.PaymentListAdapter;
import com.afiperu.ui.viewmodel.PaymentListView;
import com.afiperu.util.Constants;
import com.afiperu.util.NetworkManager;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;

import java.util.List;

import javax.inject.Inject;

public class PaymentListFragment extends BaseFragment implements PaymentListView {

    @Inject
    PaymentListPresenter paymentListPresenter;
    @Inject
    PaymentListAdapter paymentListAdapter;

    public PaymentListPresenter getPaymentListPresenter() {
        return paymentListPresenter;
    }

    public static PayPalConfiguration paypalConfig = new PayPalConfiguration()
            .environment(Constants.PAYPAL_ENVIRONMENT).clientId(
                    Constants.PAYPAL_CLIENT_ID);

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paymentListPresenter.getAllPayments(getContext());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Starting PayPal service
        Intent intent = new Intent(getActivity(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        getActivity().startService(intent);
    }

    public void displayPaymentSuccess(){
        Constants.PROGRESS.dismiss();
        //paymentListAdapter.removeClickedItem();
        paymentListAdapter.clear();
        paymentListAdapter.notifyDataSetChanged();
        paymentListPresenter.getAllPayments(getContext());
        Toast.makeText(getActivity(), "Se ha registrado su pago correctamente.", Toast.LENGTH_SHORT).show();
        getActivity().stopService(new Intent(getActivity(), PayPalService.class));
    }

    public void displayPaymentError(){
        Toast.makeText(getActivity(), "Error al validar el pago.", Toast.LENGTH_SHORT).show();
        Constants.PROGRESS.dismiss();
        getActivity().stopService(new Intent(getActivity(), PayPalService.class));
    }

    public void displayPaymentFailure(){
        Toast.makeText(getActivity(), "No se puede conectar con el servidor.", Toast.LENGTH_SHORT).show();
        Constants.PROGRESS.dismiss();
    }

}
