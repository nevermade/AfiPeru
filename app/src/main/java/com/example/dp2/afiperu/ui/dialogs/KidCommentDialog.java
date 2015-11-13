package com.example.dp2.afiperu.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.adapter.CommentKidAdapter;
import com.example.dp2.afiperu.ui.fragment.CommentKidFragment;

/**
 * Created by Usuario on 01/10/2015.
 */
public abstract class KidCommentDialog extends DialogFragment {

    public static final String COMMENT_ARG = "comment_arg";

    private int face;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        SyncComment comment = (SyncComment)args.getSerializable(COMMENT_ARG);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View rootView = inflater.inflate(R.layout.comment_dialog, null);

        final ImageView happyFace = (ImageView)rootView.findViewById(R.id.happy_face);
        final ImageView sadFace = (ImageView)rootView.findViewById(R.id.sad_face);
        if(comment != null) {
            EditText commentText = (EditText) rootView.findViewById(R.id.comment_dialog_text);
            commentText.setText(comment.getMessage());
            if(comment.getFace() == -1){
                sadFace.setImageResource(R.drawable.sad);
                face = -1;
            }else if(comment.getFace() == 1){
                happyFace.setImageResource(R.drawable.happy);
                face = 1;
            }else{
                face = 0;
            }
        }else{
            face = 0;
        }
        happyFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(face == 1){
                    happyFace.setImageResource(R.drawable.happy_off);
                    face = 0;
                }else{
                    happyFace.setImageResource(R.drawable.happy);
                    face = 1;
                }
                sadFace.setImageResource(R.drawable.sad_off);
            }
        });
        sadFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(face == -1){
                    sadFace.setImageResource(R.drawable.sad_off);
                    face = 0;
                }else{
                    sadFace.setImageResource(R.drawable.sad);
                    face = -1;
                }
                happyFace.setImageResource(R.drawable.happy_off);
            }
        });

        builder.setView(rootView)
                .setPositiveButton(getResources().getString(R.string.comment_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText text = (EditText)rootView.findViewById(R.id.comment_dialog_text);
                        acceptComment(face, text.getText().toString());
                    }
                })
                .setNegativeButton(getResources().getString(R.string.comment_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        KidCommentDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public abstract void acceptComment(int face, String message);

}
