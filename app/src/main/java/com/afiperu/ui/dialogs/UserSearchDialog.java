package com.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public abstract class UserSearchDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.users_search, null);
        builder.setView(v)
                .setTitle(R.string.users_menu_search)
                .setPositiveButton(R.string.search_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TextView name = (TextView) v.findViewById(R.id.user_fullname);
                        TextView nrodoc = (TextView) v.findViewById(R.id.user_nrodoc);
                        TextView perfil = (TextView) v.findViewById(R.id.combo_perfil);
                        onSearch(name.getText().toString(), nrodoc.getText().toString(), perfil.getText().toString());
                    }
                }).setNegativeButton(R.string.search_no, null);

        final String[] options = getResources().getStringArray(R.array.search_profile_options);

        final TextView comboPerfil = (TextView)v.findViewById(R.id.combo_perfil);
        comboPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Elija un perfil")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                comboPerfil.setText(options[which]);
                            }
                        });
                AlertDialog result = builder.create();

                result.getWindow().setBackgroundDrawableResource(R.color.main_background);
                result.show();
            }
        });

        AlertDialog result = builder.create();

        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return result;
    }

    public abstract void onSearch(String name, String doc, String profile);

}
