package com.afiperu.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afiperu.R;
import com.afiperu.ui.activity.DetailActivity;

/**
 * Created by Fernando on 01/10/2015.
 */
public abstract class CommentSearchDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.comments_search, null);

        final EditText editText = (EditText)view.findViewById(R.id.comments_search_text);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatePickerDialog vd = new DatePickerDialog(){
                    @Override
                    public void confirm(int day, int month, int year) {
                        ((TextView)v).setText(String.format("%02d/%02d/%d", day, month, year));
                    }
                    @Override
                    public void cancel(){
                        ((TextView)v).setText(getString(R.string.no_date_selected));
                    }
                };
                vd.show(getFragmentManager(), DetailActivity.DIALOG_TAG_DATE);
            }
        };
        final TextView from = (TextView)view.findViewById(R.id.comments_search_from);
        from.setOnClickListener(listener);
        final TextView to = (TextView)view.findViewById(R.id.comments_search_to);
        to.setOnClickListener(listener);
        final String[] options = new String[2];
        options[0]= "Más antiguo";
        options[1]= "Más actual";
        final TextView order = (TextView)view.findViewById(R.id.comments_search_order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Ordenar por")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                order.setText(options[which]);
                            }
                        });
                AlertDialog result = builder.create();

                result.getWindow().setBackgroundDrawableResource(R.color.main_background);
                result.show();
            }
        });


        builder.setView(view)
                .setTitle(R.string.search_title_comments)
                .setPositiveButton(R.string.search_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String authorOrContent = editText.getText().toString();
                        long fromDate = DatePickerDialog.getDate(getContext(), from.getText().toString());
                        long toDate = DatePickerDialog.getDate(getContext(), to.getText().toString());
                        String orderBy = order.getText().toString();
                        search(authorOrContent, fromDate, toDate,orderBy);
                    }
                }).setNegativeButton(R.string.search_no, null);
        AlertDialog result = builder.create();

        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return result;
    }

    public abstract void search(String authorOrContent, long fromDate, long toDate, String orderBy);

}
