package com.afiperu.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.afiperu.R;

/**
 * Created by Fernando on 01/10/2015.
 */
public abstract class ScoreVolunteerDialog extends DialogFragment {

    public static final String RATING_ARG = "rating_arg";
    public static final String COMMENT_ARG = "comment_arg";

    private int stars = 1;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        Bundle args = getArguments();
        int rating = args.getInt(RATING_ARG);
        String comment = args.getString(COMMENT_ARG);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.scorevolunteer_dialog, null);

        final EditText commentText = (EditText)view.findViewById(R.id.rating_dialog_text);
        final ImageView[] starPics = new ImageView[5];
        starPics[0] = (ImageView)view.findViewById(R.id.star1);
        starPics[1] = (ImageView)view.findViewById(R.id.star2);
        starPics[2] = (ImageView)view.findViewById(R.id.star3);
        starPics[3] = (ImageView)view.findViewById(R.id.star4);
        starPics[4] = (ImageView)view.findViewById(R.id.star5);
        for(int i=0; i<starPics.length; i++){
            final int starNumber = i;
            starPics[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stars = starNumber + 1;
                    for (int j = 0; j <= starNumber; j++) {
                        starPics[j].setImageResource(R.drawable.ic_star_full);
                    }
                    for (int j = starNumber + 1; j < starPics.length; j++) {
                        starPics[j].setImageResource(R.drawable.ic_star_empty);
                    }
                }
            });
        }

        stars = rating;
        if(stars < 1){
            stars = 1;
        }else if(stars > 5){
            stars = 5;
        }
        for (int j = 0; j < stars; j++) {
            starPics[j].setImageResource(R.drawable.ic_star_full);
        }
        if(comment != null) {
            commentText.setText(comment);
        }

        builder.setView(view)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = commentText.getText().toString();
                        acceptRatingAndComment(stars, comment);
                    }
                });
        AlertDialog dialog = builder.create();

        //Background color
        dialog.getWindow().setBackgroundDrawableResource(R.color.main_background);

        return dialog;
    }

    public abstract void acceptRatingAndComment(int stars, String comment);

}
