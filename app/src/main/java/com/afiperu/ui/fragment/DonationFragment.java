package com.afiperu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.util.Constants;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Fernando on 23/09/2015.
 */
public class DonationFragment extends BaseFragment{

    public DonationFragment(){
        super();
    }

    public static PayPalConfiguration paypalConfig = new PayPalConfiguration()
            .environment(Constants.PAYPAL_ENVIRONMENT).clientId(
                    Constants.PAYPAL_CLIENT_ID);

    @Override
    public int getLayout(){
        return R.layout.donation;
    }

    @Override
    public void prepareView(final View rootView, Bundle args, Bundle savedInstanceState){
        Button btnDonate=(Button)rootView.findViewById(R.id.btn_donate);
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView amount = (TextView) rootView.findViewById(R.id.donation_amount);

                DecimalFormat df = new DecimalFormat("#.00");
                String formattedAmount = df.format(Double.valueOf(amount.getText().toString()));
                PayPalItem payPalItem = new PayPalItem("pago de padrino", 1, new BigDecimal(formattedAmount), "USD", null);

                PayPalItem[] items = new PayPalItem[1];
                items[0] = payPalItem;

                PayPalPaymentDetails details = new PayPalPaymentDetails(new BigDecimal("0.0"), PayPalItem.getItemTotal(items), new BigDecimal("0.0"));

                PayPalPayment payment = new PayPalPayment(
                        new BigDecimal(formattedAmount),
                        "USD",
                        "Donación",
                        Constants.PAYMENT_INTENT
                );
                payment.items(items).paymentDetails(details);
                ;
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, PaymentListFragment.paypalConfig);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                getActivity().startActivityForResult(intent, Constants.REQUEST_CODE_PAYMENT);

            }
        });
    }

    public void clean(){
        View v = getView();
        if(v != null){
            EditText amount = (EditText)v.findViewById(R.id.donation_amount);
            amount.setText("");
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    /*public void initializePaypal(){
        initializePaypal();
        Intent intent = new Intent(getActivity(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        getActivity().startService(intent);
    }*/
}
