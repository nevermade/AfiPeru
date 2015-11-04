package com.example.dp2.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.example.dp2.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public class VoucherDateDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.voucher_date_dialog, null))
                .setTitle("Fecha de pago")
                .setPositiveButton("Aceptar", null).setNegativeButton(R.string.search_no, null);
        AlertDialog result = builder.create();

        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return result;
    }

}
