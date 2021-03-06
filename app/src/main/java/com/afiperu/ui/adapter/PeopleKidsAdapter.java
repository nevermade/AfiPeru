package com.afiperu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afiperu.R;
import com.afiperu.common.BaseArrayAdapter;
import com.afiperu.common.BaseFragment;
import com.afiperu.syncmodel.SyncKid;

import java.util.List;

/**
 * Created by Nevermade on 02/10/2015.
 */
public class PeopleKidsAdapter extends BaseArrayAdapter<SyncKid> {

    public PeopleKidsAdapter(Context context, BaseFragment fragment, List<SyncKid> objects) {
        super(context, fragment, R.layout.people_kids_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView, final SyncKid item, final int position) {
        TextView name= (TextView) convertView.findViewById(R.id.people_kids_item_name);
        TextView age=(TextView)convertView.findViewById(R.id.people_kids_item_age);
        ImageView gender=(ImageView)convertView.findViewById(R.id.people_kids_item_gender_pic);

        name.setText(item.getNames());
        age.setText(String.valueOf(item.getAge()));
        if(item.getGender()==null) {
        }
        else if (item.getGender()==0)
            gender.setImageResource(R.drawable.ic_male);
        else
            gender.setImageResource(R.drawable.ic_female);
    }
}
