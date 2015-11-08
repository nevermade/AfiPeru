package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.dialogs.KidCommentDialog;
import com.example.dp2.afiperu.ui.fragment.CommentKidFragment;

import java.util.List;

public class CommentKidAdapter extends BaseArrayAdapter<AttendanceChild> {

    public CommentKidAdapter(Context context, BaseFragment fragment, List<AttendanceChild> objects) {
        super(context, fragment, R.layout.kid_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, final AttendanceChild item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.kids_item_name);
        TextView age = (TextView) convertView.findViewById(R.id.kids_item_date);
        final ImageView check = (ImageView) convertView.findViewById(R.id.kids_item_menu);

        name.setText(item.getChild().getNames() + " " + item.getChild().getLastName());
        age.setText(convertView.getResources().getString(R.string.kids_age, item.getChild().getAge()));

        check.setImageResource(item.getCommented() == 1 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable(KidCommentDialog.COMMENT_ARG, item.getComment());
                DialogFragment newFragment = new KidCommentDialog(){
                    @Override
                    public void acceptComment(int face, String message){
                        ((CommentKidFragment)getFragment()).makeComment(item.getChild().getId(), face, message);
                        check.setImageResource(R.drawable.ic_checked_checkbox);
                        Comment comment = new Comment();
                        comment.setFace(face);
                        comment.setMessage(message);
                        item.setComment(comment);
                    }
                };
                newFragment.setArguments(args);
                getFragment().showPopup(newFragment, DetailActivity.DIALOG_TAG_DETAIL_COMMENT);
            }
        });
    }
}
