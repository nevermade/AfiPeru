package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerPaymentDepositComponent;
import com.example.dp2.afiperu.module.PaymentDepositModule;
import com.example.dp2.afiperu.presenter.PaymentDepositPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.dialogs.DatePickerDialog;
import com.example.dp2.afiperu.ui.viewmodel.PaymentDepositView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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

        final TextView bank = (TextView)rootView.findViewById(R.id.payment_deposit_bank);
        final String[] options = getResources().getStringArray(R.array.payment_banks);
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.select_bank)
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                bank.setText(options[which]);
                            }
                        });
                AlertDialog result = builder.create();

                result.getWindow().setBackgroundDrawableResource(R.color.main_background);
                result.show();
            }
        });

        final EditText voucher = (EditText)rootView.findViewById(R.id.payment_deposit_voucher);
        final TextView vouchDate = (TextView)rootView.findViewById(R.id.payment_deposit_date);
        vouchDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog vd = new DatePickerDialog(){
                    @Override
                    public void confirm(int day, int month, int year) {
                        vouchDate.setText(String.format("%02d/%02d/%d", day, month, year));
                    }
                };
                vd.show(getFragmentManager(), DetailActivity.DIALOG_TAG_DATE);
            }
        });
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        vouchDate.setText(format.format(new Date()));

        Button button = (Button)rootView.findViewById(R.id.payment_deposit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String voucherCode = voucher.getText().toString();
                if(voucherCode.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.voucher_code_missing).setNeutralButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else {
                    long date = DatePickerDialog.getDate(getContext(), vouchDate.getText().toString());
                    String bankName = bank.getText().toString();
                    if (System.currentTimeMillis() <= date) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage(R.string.payment_date_wrong).setNeutralButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        presenter.registerBankPayment(getContext(), feeId, voucherCode, date, bankName);
                    }
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(){
        Toast.makeText(getContext(), getResources().getString(R.string.payment_success), Toast.LENGTH_SHORT).show();
        ((DetailActivity)getActivity()).goBack();
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
