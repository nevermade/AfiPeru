package com.example.dp2.afiperu.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.dp2.afiperu.R;

/**
 * Created by Usuario on 01/10/2015.
 */
public class KidCommentDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.comment_dialog, null))
                .setPositiveButton(getResources().getString(R.string.comment_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(getResources().getString(R.string.comment_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        KidCommentDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
