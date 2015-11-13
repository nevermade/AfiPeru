package com.example.dp2.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.dp2.afiperu.R;

import java.util.GregorianCalendar;

/**
 * Created by Fernando on 01/10/2015.
 */
public abstract class DatePickerDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.date_picker_dialog, null);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    DatePicker datePicker = (DatePicker)v.findViewById(R.id.date_picker);
                    confirm(datePicker.getDayOfMonth(), datePicker.getMonth()+1, datePicker.getYear());
                }else if(which == DialogInterface.BUTTON_NEGATIVE){
                    cancel();
                }
            }
        };

        builder.setView(v)
                .setTitle(R.string.pick_date)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener);
        AlertDialog result = builder.create();

        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return result;
    }

    public abstract void confirm(int day, int month, int year);

    public void cancel(){

    }

    public static long getDate(Context context, String text){
        if(text.equals(context.getString(R.string.no_date_selected))){
            return -1;
        }else {
            //Milisegundos
            String[] numbers = text.split("/");
            int day = Integer.parseInt(numbers[0]);
            int month = Integer.parseInt(numbers[1]);
            int year = Integer.parseInt(numbers[2]);
            return (new GregorianCalendar(year, month - 1, day)).getTimeInMillis();
        }
    }

}
