package com.example.dp2.afiperu.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.EnumMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 18/10/2015.
 */
public class PaymentListAdapter extends BaseArrayAdapter <Payment>{
    Context context;
    ArrayList<Payment>payments;
    public PaymentListAdapter(Context context, BaseFragment fragment, List<Payment> objects) {
        super(context, fragment, R.layout.payments_list_item, objects);
        payments=(ArrayList<Payment>)objects;
        this.context=context;
    }

    @Override
    public void prepareItemView(final View convertView, Payment item, int position) {
        TextView expirationDate = (TextView)convertView.findViewById(R.id.payment_item_expirationDate);
        TextView mount= (TextView)convertView.findViewById(R.id.payment_item_mount);
        TextView state= (TextView)convertView.findViewById(R.id.payment_item_state);
        TextView none= (TextView)convertView.findViewById(R.id.payment_item_none);
        Button payBtn= (Button)convertView.findViewById(R.id.payment_item_action);


        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        expirationDate.setText(dateFormat.format(item.getExpirationDate()));
        mount.setText(String.valueOf(item.getMount()));
        state.setText(EnumMapping.getPaymentState(item.getState()));
        payBtn.setText("Pagar");

        GradientDrawable sd=(GradientDrawable)payBtn.getBackground().getCurrent();
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog((Activity) convertView.getContext(),"Método de Pago","Seleccione un método de pago.");
            }
        });

        if(item.getState()== AppEnum.EnumPaymentState.EXPIRATED.ordinal()){
            expirationDate.setTextColor(Color.RED);
            mount.setTextColor(Color.RED);
            state.setTextColor(Color.RED);
            sd.setColor(Color.RED);
        }else {
            expirationDate.setTextColor(Color.BLUE);
            mount.setTextColor(Color.BLUE);
            state.setTextColor(Color.BLUE);
            sd.setColor(Color.BLUE);
            if(item.getState()== AppEnum.EnumPaymentState.PAYED.ordinal()){
                payBtn.setVisibility(View.GONE);
                none.setVisibility(View.VISIBLE);
                none.setTextColor(Color.BLUE);
            }
        }
    }

    public void updatePayments(ArrayList<Payment>payments){
        this.payments.clear();
        for(Payment payment:payments)
            this.payments.add(payment);
        notifyDataSetChanged();

    }
    public void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Depósito", null);
        builder.setNegativeButton("PayPal", null);
        builder.show();
    }
}
