package com.example.dp2.afiperu.lists;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.fragments.BaseFragment;

import java.util.List;

/**
 * Created by Nevermade on 02/10/2015.
 */
public class PeopleKidsAdapter extends BaseArrayAdapter<PeopleKidsItem> {

    public PeopleKidsAdapter(Context context, BaseFragment fragment, List<PeopleKidsItem> objects) {
        super(context, fragment, R.layout.people_kids_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView, final PeopleKidsItem item, final int position) {
        TextView name= (TextView) convertView.findViewById(R.id.people_kids_item_name);
        TextView age=(TextView)convertView.findViewById(R.id.people_kids_item_age);
        ImageView gender=(ImageView)convertView.findViewById(R.id.people_kids_item_gender_pic);

        name.setText(item.getName()+" "+item.getLastName());
        age.setText(String.valueOf(item.getAge()));
        if(item.isMale())
            gender.setImageResource(R.drawable.ic_male);
        else
            gender.setImageResource(R.drawable.ic_female);
    }
}
