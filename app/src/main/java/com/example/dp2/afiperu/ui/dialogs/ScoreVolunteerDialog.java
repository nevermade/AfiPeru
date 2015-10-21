package com.example.dp2.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public class ScoreVolunteerDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.scorevolunteer_dialog, null);
/*
        TextView icon_close = (TextView) view.findViewById(R.id.close_scorevolunteer);

        Toast.makeText(getContext(),"Hola",Toast.LENGTH_SHORT).show();

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager fragmentManager = getFragment().getFragmentManager();
                Toast.makeText(getContext(),"Hola",Toast.LENGTH_SHORT).show();
                ScoreVolunteerDialog.this.dismiss();
                Toast.makeText(getContext(),"Hola",Toast.LENGTH_SHORT).show();

            }
        });

*/


        builder.setView(inflater.inflate(R.layout.scorevolunteer_dialog, null));
        return builder.create();
    }

}
