package com.example.dp2.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public class UserSearchDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.users_search, null);
        builder.setView(v)
                .setTitle(R.string.users_menu_search)
                .setPositiveButton(R.string.search_yes, null).setNegativeButton(R.string.search_no, null);


        final String[] options = new String[4];
        options[0]="Miembro AFI";
        options[1]="Voluntario";
        options[2]="Padrino";
        options[3]="Cualquiera";/*
        builder.setNeutralButton(android.R.string.ok, null)
                .setTitle(R.string.choose_address).setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //goToAddress(addresses.get(which));
            }
        });
*/      final TextView comboPerfil = (TextView)v.findViewById(R.id.combo_perfil);
        //comboPerfil.setText("Holi");


        comboPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("comboclick");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Elija un perfil")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                comboPerfil.setText(options[which]);
                            }
                        });
                AlertDialog result = builder.create();

                result.getWindow().setBackgroundDrawableResource(R.color.main_background);
                result.show();
            }
        });


        AlertDialog result = builder.create();



        //comboPerfil.
        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return result;
    }

}
