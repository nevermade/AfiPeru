package com.example.dp2.afiperu.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.dp2.afiperu.DetailActivity;
import com.example.dp2.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public class CommentSearchDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.comments_search, null))
                .setTitle(R.string.search_title_comments)
                .setPositiveButton(R.string.search_yes, null).setNegativeButton(R.string.search_no, null);
        AlertDialog result = builder.create();

        //Background color
        result.getWindow().setBackgroundDrawableResource(R.color.main_background);
        //Title text color
        /*int titleId = result.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView title = (TextView)result.findViewById(titleId);
        int color;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            color = getResources().getColor(R.color.dark_text, null);
        }else{
            color = getResources().getColor(R.color.dark_text);
        }
        title.setTextColor(color);*/

        return result;
    }

    @Override
    public void show(FragmentManager manager, String tag){
        super.show(manager, tag);
        //Button text color
        /*((AlertDialog)getDialog()).getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(DetailActivity.DARK_COLOR);
        ((AlertDialog)getDialog()).getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(DetailActivity.DARK_COLOR);
        ((AlertDialog)getDialog()).getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(DetailActivity.DARK_COLOR);*/
    }

}
