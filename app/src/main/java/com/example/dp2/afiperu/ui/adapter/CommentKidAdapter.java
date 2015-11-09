package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.syncmodel.SyncAttendanceChild;
import com.example.dp2.afiperu.syncmodel.SyncComment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.dialogs.KidCommentDialog;
import com.example.dp2.afiperu.ui.fragment.CommentKidFragment;
import com.example.dp2.afiperu.util.Constants;

import java.util.List;

public class CommentKidAdapter extends BaseArrayAdapter<SyncAttendanceChild> {

    public CommentKidAdapter(Context context, BaseFragment fragment, List<SyncAttendanceChild> objects) {
        super(context, fragment, R.layout.kid_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, final SyncAttendanceChild item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.kids_item_name);
        TextView age = (TextView) convertView.findViewById(R.id.kids_item_date);
        final ImageView check = (ImageView) convertView.findViewById(R.id.kids_item_menu);

        name.setText(item.getKid().getNames());
        age.setText(convertView.getResources().getString(R.string.kids_age, item.getKid().getAge()));

        check.setImageResource(item.getComment() != null ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable(KidCommentDialog.COMMENT_ARG, item.getComment());
                DialogFragment newFragment = new KidCommentDialog(){
                    @Override
                    public void acceptComment(int face, String message){
                        ((CommentKidFragment)getFragment()).makeComment(item.getKid().getKidId(), face, message);
                        check.setImageResource(R.drawable.ic_checked_checkbox);
                        SyncComment comment = new SyncComment(message, face,
                                Constants.loggedUser.getName() + " " + Constants.loggedUser.getLastName());
                        item.setComment(comment);
                    }
                };
                newFragment.setArguments(args);
                getFragment().showPopup(newFragment, DetailActivity.DIALOG_TAG_DETAIL_COMMENT);
            }
        });
    }
}
