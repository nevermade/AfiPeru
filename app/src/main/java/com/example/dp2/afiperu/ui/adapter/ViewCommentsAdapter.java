package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.fragment.CommentFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ViewCommentsAdapter extends BaseArrayAdapter<AttendanceChild> {

    public static final int FILTER_COLOR = 0xff7fb2d6;

    public ViewCommentsAdapter(Context context, BaseFragment fragment, List<AttendanceChild> objects) {
        super(context, fragment, R.layout.kid_list_item2, objects);
    }

    @Override
    public void prepareItemView(View convertView, final AttendanceChild item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.kids_item_name2);
        TextView age = (TextView) convertView.findViewById(R.id.kids_item_date2);

        name.setText(item.getChild().getNames() + " " + item.getChild().getLastName());
        age.setText(convertView.getResources().getString(R.string.kids_age, item.getChild().getAge()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable(CommentFragment.KID_ARG, item.getChild());
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_LISTA_COMENTARIOS);
                CommentFragment fragment = new CommentFragment();
                fragment.setArguments(args);
                getFragment().addFragmentToStack(fragment, DetailActivity.FRAGMENT_LISTA_COMENTARIOS);
            }
        });

    }



}
