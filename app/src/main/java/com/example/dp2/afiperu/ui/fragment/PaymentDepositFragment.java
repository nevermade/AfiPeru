package com.example.dp2.afiperu.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerPaymentDepositComponent;
import com.example.dp2.afiperu.module.PaymentDepositModule;
import com.example.dp2.afiperu.presenter.PaymentDepositPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.dialogs.VoucherDateDialog;
import com.example.dp2.afiperu.ui.viewmodel.PaymentDepositView;

import javax.inject.Inject;

/**
 * Created by DABARCA on 19/10/2015.
 */
public class PaymentDepositFragment extends BaseFragment implements PaymentDepositView {

    @Inject
    PaymentDepositPresenter presenter;

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //if (title != null) builder.setTitle(title);
        EditText vouchDate = (EditText)rootView.findViewById(R.id.voucherDate);
        vouchDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                VoucherDateDialog vd = new VoucherDateDialog();
                vd.show(getFragmentManager(),"Voucher Date");
            }
        });/*
        builder.setMessage("Holi");
        builder.setPositiveButton("Depósito", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle args= new Bundle();
                PaymentDepositFragment fragment = new PaymentDepositFragment();
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_REGISTRAR_PAGO);
                fragment.setArguments(args);
                getFragment().addFragmentToStack(fragment, DetailActivity.FRAGMENT_REGISTRAR_PAGO);
            }
        });
        builder.setNegativeButton("PayPal", null);

        builder.show();
        */
    }

    @Override
    public int getLayout() {
        return R.layout.payment_deposit;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerPaymentDepositComponent.builder()
                .afiAppComponent(appComponent)
                .paymentDepositModule(new PaymentDepositModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
