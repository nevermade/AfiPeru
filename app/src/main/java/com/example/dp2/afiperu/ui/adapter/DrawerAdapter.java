package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.domain.Drawer;

import java.util.List;

public class DrawerAdapter extends BaseArrayAdapter<Drawer> {

    public DrawerAdapter(Context context, List<Drawer> objects) {
        super(context, null, R.layout.drawer_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, Drawer item, int position) {
        ImageView icon = (ImageView) convertView.findViewById(R.id.drawer_list_item_icon);
        TextView name = (TextView) convertView.findViewById(R.id.drawer_list_item_name);

        icon.setImageResource(item.getIconId());
        name.setText(item.getName());
        convertView.setTag(item.getFragmentId());
        if(item.getName().equals(convertView.getResources().getString(R.string.menu_postular))){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                name.setTextColor(convertView.getResources().getColor(R.color.drawer_highlight, null));
            }else{
                name.setTextColor(convertView.getResources().getColor(R.color.drawer_highlight));
            }
        }
    }
}