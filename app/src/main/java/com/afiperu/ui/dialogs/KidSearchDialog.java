package com.afiperu.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public abstract class KidSearchDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.kid_search, null);

        builder.setView(v)
                .setTitle(R.string.search_title_kids)
                .setPositiveButton(R.string.search_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView name = (TextView)v.findViewById(R.id.kid_fullname);
                        TextView edadini = (TextView)v.findViewById(R.id.kid_search_age_from);
                        TextView edadfin = (TextView)v.findViewById(R.id.kid_search_age_to);
                        TextView genero = (TextView)v.findViewById(R.id.kid_gender);
                        onSearch(name.getText().toString(), edadini.getText().toString(), edadfin.getText().toString(),
                                genero.getText().toString());
                    }
                })
                .setNegativeButton(R.string.search_no, null);

        final String[] options = getResources().getStringArray(R.array.search_gender_options);
        final TextView comboGender = (TextView)v.findViewById(R.id.kid_gender);
        comboGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.select_gender)
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                comboGender.setText(options[which]);
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

    public abstract void onSearch(String name, String fromAge, String toAge, String gender);

}
