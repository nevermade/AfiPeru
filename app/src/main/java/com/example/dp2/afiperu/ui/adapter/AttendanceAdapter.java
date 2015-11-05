package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Attendance;
import com.example.dp2.afiperu.rest.model.AttendanceVolunteer;
import com.example.dp2.afiperu.ui.dialogs.ScoreVolunteerDialog;

import java.util.List;

public class AttendanceAdapter extends BaseArrayAdapter<AttendanceVolunteer> {

    public AttendanceAdapter(Context context, BaseFragment fragment, List<AttendanceVolunteer> objects) {
        super(context, fragment, R.layout.attendance_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView, AttendanceVolunteer item, int position) {
        //ImageView icon = (ImageView) convertView.findViewById(R.id.attendance_item_icon);
        TextView name = (TextView) convertView.findViewById(R.id.attendance_item_name);

        //setImage(icon, item.getPicURL(), "news_author_" + item.getPicId() + ".png");
        name.setText(item.getVolunteer().getNames());
        ImageView scoreicon = (ImageView) convertView.findViewById(R.id.attendance_item_scorebutton);
        ImageView checkbox = (ImageView) convertView.findViewById(R.id.attendance_checkbox);
        if (item.getAttended()) checkbox.setImageResource(R.drawable.ic_checked_checkbox);
        else checkbox.setImageResource(R.drawable.ic_unchecked_checkbox);
        checkbox.setOnClickListener(new checkBoxListener(convertView, item));
        //TextView X = (TextView) convertView.findViewById(R.id.close_scorevolunteer);
        /*
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager fragmentManager = getFragment().getFragmentManager();
                DialogFragment dialog = new ScoreVolunteerDialog();
                dialog.show(getFragment().getFragmentManager(), "NoticeDialogFragment");

            }
        });*/
        scoreicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //FragmentManager fragmentManager = getFragment().getFragmentManager();
                DialogFragment dialog = new ScoreVolunteerDialog();
                dialog.show(getFragment().getFragmentManager(), "NoticeDialogFragment");
                /*
                LayoutInflater inflater = getFragment().getLayoutInflater(Bundle.EMPTY);
                View view = inflater.inflate(R.layout.scorevolunteer_dialog, null);
                TextView icon_close = (TextView) view.findViewById(R.id.close_scorevolunteer);
                icon_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //FragmentManager fragmentManager = getFragment().getFragmentManager();
                        Toast.makeText(getContext(), "Hola", Toast.LENGTH_SHORT).show();
                        //ScoreVolunteerDialog.this.dismiss();
                        Toast.makeText(getContext(), "Hola", Toast.LENGTH_SHORT).show();

                    }
                });
                */

            }
        });
/*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Switch switc = (Switch) convertView.findViewById(R.id.attendance_item_toggle);
            switc.setChecked(item.isToggleOn());
        }

        */
    }

    public class checkBoxListener implements View.OnClickListener{
        AttendanceVolunteer av;
        ImageView checkbox;
        public checkBoxListener (View convertView, AttendanceVolunteer av){
            this.av=av;
            checkbox = (ImageView)convertView.findViewById(R.id.attendance_checkbox);

        }

        @Override
        public void onClick(View v) {
            if (av.getAttended()) {

                av.setAttended(false);
                checkbox.setImageResource(R.drawable.ic_unchecked_checkbox);
            }else{
                av.setAttended(true);
                checkbox.setImageResource(R.drawable.ic_checked_checkbox);
            }
        }
    }


}
