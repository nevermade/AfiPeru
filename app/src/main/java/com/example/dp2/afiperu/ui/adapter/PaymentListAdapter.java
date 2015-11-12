package com.example.dp2.afiperu.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.syncmodel.SyncPayment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.PaymentDepositFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nevermade on 18/10/2015.
 */
public class PaymentListAdapter extends BaseArrayAdapter <SyncPayment>{

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
                showDialog(item.getFeeId());
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

    public void showDialog(final int feeId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getContext().getString(R.string.payment_method))
                .setMessage(getContext().getString(R.string.payment_method_prompt))
                .setPositiveButton(R.string.payment_method_deposit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle args = new Bundle();
                        PaymentDepositFragment fragment = new PaymentDepositFragment();
                        args.putInt(PaymentDepositFragment.FEE_ID_ARG, feeId);
                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_REGISTRAR_PAGO);
                        fragment.setArguments(args);
                        getFragment().addFragmentToStack(fragment, DetailActivity.FRAGMENT_REGISTRAR_PAGO);
                    }
                })
                .setNeutralButton(R.string.payment_method_paypal, null)
                .setNegativeButton(android.R.string.cancel, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
