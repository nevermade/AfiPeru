package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Attendance;

import java.util.List;

public class AttendanceAdapter extends BaseArrayAdapter<Attendance> {

    public AttendanceAdapter(Context context, BaseFragment fragment, List<Attendance> objects) {
        super(context, fragment, R.layout.attendance_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, Attendance item, int position) {
        ImageView icon = (ImageView) convertView.findViewById(R.id.attendance_item_icon);
        TextView name = (TextView) convertView.findViewById(R.id.attendance_item_name);

        setImage(icon, item.getPicURL(), "news_author_" + item.getPicId() + ".png");
        name.setText(item.getName());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Switch switc = (Switch) convertView.findViewById(R.id.attendance_item_toggle);
            switc.setChecked(item.isToggleOn());
        }
    }
}
