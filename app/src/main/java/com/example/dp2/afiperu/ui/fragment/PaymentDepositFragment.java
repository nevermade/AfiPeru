package com.example.dp2.afiperu.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerPaymentDepositComponent;
import com.example.dp2.afiperu.module.PaymentDepositModule;
import com.example.dp2.afiperu.presenter.PaymentDepositPresenter;
import com.example.dp2.afiperu.ui.dialogs.VoucherDateDialog;
import com.example.dp2.afiperu.ui.viewmodel.PaymentDepositView;

import javax.inject.Inject;

/**
 * Created by DABARCA on 19/10/2015.
 */
public class PaymentDepositFragment extends BaseFragment implements PaymentDepositView {

    @Inject
    PaymentDepositPresenter presenter;

    public static final String FEE_ID_ARG = "fee_id_arg";

    private int feeId;

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        feeId = args.getInt(FEE_ID_ARG);

        TextView vouchDate = (TextView)rootView.findViewById(R.id.payment_deposit_date);
        vouchDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                VoucherDateDialog vd = new VoucherDateDialog();
                vd.show(getFragmentManager(),"Voucher Date");
            }
        });
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
