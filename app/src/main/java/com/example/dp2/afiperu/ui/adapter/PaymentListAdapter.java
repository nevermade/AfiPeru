package com.example.dp2.afiperu.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.syncmodel.SyncPayment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.PaymentDepositFragment;
import com.example.dp2.afiperu.ui.fragment.PaymentListFragment;
import com.example.dp2.afiperu.util.Constants;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nevermade on 18/10/2015.
 */
public class PaymentListAdapter extends BaseArrayAdapter <SyncPayment>{

    private static SyncPayment clickedPayment;

    public PaymentListAdapter(Context context, BaseFragment fragment, List<SyncPayment> objects) {
        super(context, fragment, R.layout.payments_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView, final SyncPayment item, int position) {
        TextView expirationDate = (TextView)convertView.findViewById(R.id.payment_item_expirationDate);
        TextView mount= (TextView)convertView.findViewById(R.id.payment_item_mount);
        TextView state= (TextView)convertView.findViewById(R.id.payment_item_state);
        TextView none= (TextView)convertView.findViewById(R.id.payment_item_none);
        Button payBtn= (Button)convertView.findViewById(R.id.payment_item_action);

        expirationDate.setText((new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date(item.getDueDate()))));
        mount.setText(String.valueOf(item.getAmount()));
        String stateText;
        switch(item.getStatus()){
            case 0: stateText = getContext().getResources().getString(R.string.payment_status_expired); break;
            case 1: stateText = getContext().getResources().getString(R.string.payment_status_pending); break;
            case 2: stateText = getContext().getResources().getString(R.string.payment_status_approved); break;
            case 3: stateText = getContext().getResources().getString(R.string.payment_status_approval_pending); break;
            default: stateText = ""; break;
        }
        state.setText(stateText);

        GradientDrawable sd=(GradientDrawable)payBtn.getBackground().getCurrent();
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(item);

            }
        });

        if(item.getStatus() == 0){
            expirationDate.setTextColor(Color.RED);
            mount.setTextColor(Color.RED);
            state.setTextColor(Color.RED);
            sd.setColor(Color.RED);
        }else {
            expirationDate.setTextColor(Color.BLUE);
            mount.setTextColor(Color.BLUE);
            state.setTextColor(Color.BLUE);
            sd.setColor(Color.BLUE);
            if(item.getStatus() == 2 || item.getStatus() == 3){
                payBtn.setVisibility(View.GONE);
                none.setVisibility(View.VISIBLE);
                none.setTextColor(Color.BLUE);
            }
        }
    }

    public void showDialog(final SyncPayment item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getContext().getString(R.string.payment_method))
                .setMessage(getContext().getString(R.string.payment_method_prompt))
                .setPositiveButton(R.string.payment_method_deposit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle args = new Bundle();
                        PaymentDepositFragment fragment = new PaymentDepositFragment();
                        args.putInt(PaymentDepositFragment.FEE_ID_ARG, item.getFeeId());
                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_REGISTRAR_PAGO);
                        fragment.setArguments(args);
                        getFragment().addFragmentToStack(fragment, DetailActivity.FRAGMENT_REGISTRAR_PAGO);
                    }
                })
                .setNeutralButton(R.string.payment_method_paypal, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clickedPayment=item;
                        launchPaypalPayment(item);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void launchPaypalPayment(SyncPayment item){

        //BigDecimal amount=BigDecimal.valueOf(item.getAmount());
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedAmount= df.format((Double)(item.getAmount()/Constants.FROM_USD_TO_PEN));
        PayPalItem payPalItem= new PayPalItem("pago de padrino",1,new BigDecimal(formattedAmount),"USD",item.getFeeId().toString());

        PayPalItem[] items= new PayPalItem[1];
        items[0]=payPalItem;

        PayPalPaymentDetails details = new PayPalPaymentDetails(new BigDecimal("0.0"),PayPalItem.getItemTotal(items),new BigDecimal("0.0"));

        PayPalPayment payment = new PayPalPayment(
                new BigDecimal(formattedAmount),
                "USD",
                "Pago del "+ (new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date(item.getDueDate()))),
                Constants.PAYMENT_INTENT
        );
        payment.items(items).paymentDetails(details);
        Constants.PAYMENT_FEE_ID=item.getFeeId();
        Intent intent= new Intent(getFragment().getActivity(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, PaymentListFragment.paypalConfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        if(Constants.FROM_USD_TO_PEN!=0) {
            Constants.PAYMENT_TYPE = 0;
            getFragment().getActivity().startActivityForResult(intent, Constants.REQUEST_CODE_PAYMENT);
        }
        else
            Toast.makeText(getFragment().getActivity(), "No se pudo obtener la tasa de conversión", Toast.LENGTH_SHORT).show();
    }

    public void removeClickedItem(){
        remove(clickedPayment);
        notifyDataSetChanged();
    }

}
