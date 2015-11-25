package com.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afiperu.R;
import com.afiperu.common.BaseArrayAdapter;
import com.afiperu.domain.Drawer;

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
        if(item.getFragmentId()==-1){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                name.setTextColor(convertView.getResources().getColor(R.color.drawer_highlight, null));
            }else{
                name.setTextColor(convertView.getResources().getColor(R.color.drawer_highlight));
            }
        }
    }

    public void removeItem(Drawer option){
        remove(option);
        notifyDataSetChanged();
    }
}
