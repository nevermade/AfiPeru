package com.afiperu.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afiperu.R;
import com.afiperu.common.BaseArrayAdapter;
import com.afiperu.common.BaseFragment;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.ui.dialogs.ScoreVolunteerDialog;
import com.afiperu.ui.fragment.AttendanceFragment;

import java.util.List;

public class AttendanceAdapter extends BaseArrayAdapter<SyncAttendanceVolunteer> {

    public AttendanceAdapter(Context context, BaseFragment fragment, List<SyncAttendanceVolunteer> objects) {
        super(context, fragment, R.layout.attendance_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView, final SyncAttendanceVolunteer item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.attendance_item_name);
        name.setText(item.getNames());
        final ImageView checkbox = (ImageView) convertView.findViewById(R.id.attendance_checkbox);
        checkbox.setImageResource(item.getAttended() == 1 ? R.drawable.ic_checked_checkbox : R.drawable.ic_unchecked_checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getAttended() == 1){
                    item.setAttended(0);
                    checkbox.setImageResource(R.drawable.ic_unchecked_checkbox);
                    ((AttendanceFragment)getFragment()).edited();
                }else{
                    item.setAttended(1);
                    checkbox.setImageResource(R.drawable.ic_checked_checkbox);
                    ((AttendanceFragment)getFragment()).edited();
                }
            }
        });
        ImageView scoreicon = (ImageView) convertView.findViewById(R.id.attendance_item_scorebutton);
        scoreicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getAttended() == 1) {
                    DialogFragment dialog = new ScoreVolunteerDialog() {
                        @Override
                        public void acceptRatingAndComment(int stars, String comment) {
                            item.setRating(stars);
                            item.setComment(comment);
                            ((AttendanceFragment)getFragment()).edited();
                        }
                    };
                    Bundle args = new Bundle();
                    args.putInt(ScoreVolunteerDialog.RATING_ARG, item.getRating());
                    args.putString(ScoreVolunteerDialog.COMMENT_ARG, item.getComment());
                    dialog.setArguments(args);
                    dialog.show(getFragment().getFragmentManager(), "NoticeDialogFragment");
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.cant_comment_if_didnt_attend)
                           .setNeutralButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

}
