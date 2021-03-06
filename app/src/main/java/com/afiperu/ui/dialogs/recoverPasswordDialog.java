package com.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public class recoverPasswordDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.recover_password, null))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(),"Enviado...",Toast.LENGTH_SHORT).show();
                        
                    }
                })
                .setNegativeButton(getResources().getString(R.string.comment_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        recoverPasswordDialog.this.getDialog().cancel();
                    }
                }).setTitle("Recuperar contraseña");
        AlertDialog result = builder.create();

        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return result;
    }

}
