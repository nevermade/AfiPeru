package com.example.dp2.afiperu.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dp2.afiperu.DetailActivity;
import com.example.dp2.afiperu.R;

/**
 * Created by Usuario on 01/10/2015.
 */
public class KidCommentFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.comment_dialog, null))
                // Add action buttons
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        //Toast.makeText(DetailActivity.this, "Comentario guardado.", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        KidCommentFragment.this.getDialog().cancel();
                        //Toast.makeText(DetailActivity.this, "Comentario guardado.", Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }

}
