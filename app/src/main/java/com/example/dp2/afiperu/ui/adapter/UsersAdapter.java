package com.example.dp2.afiperu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.syncmodel.SyncUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 01/10/2015.
 */
public class UsersAdapter extends BaseArrayAdapter<SyncUser> {

    public UsersAdapter(Context context, BaseFragment fragment, List<SyncUser> objects) {
        super(context, fragment, R.layout.users_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView,final SyncUser item,final int position) {
        TextView name= (TextView)convertView.findViewById(R.id.user_item_name);
        TextView code= (TextView)convertView.findViewById(R.id.user_item_code);
        //TextView score= (TextView)convertView.findViewById(R.id.users_item_score);

        name.setText(item.getName()+" "+item.getLastName());
        code.setText(item.getProfile() + " - " + item.getNickName());
        //score.setText(String.format("%.2f",item.getScore()));
    }

}
