package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.dialogs.KidCommentDialog;

import java.util.List;

public class KidAdapter extends BaseArrayAdapter<Kid> {

    public static final int FILTER_COLOR = 0xff7fb2d6;

    public KidAdapter(Context context, BaseFragment fragment, List<Kid> objects) {
        super(context, fragment, R.layout.kid_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, Kid item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.kids_item_name);
        TextView age = (TextView) convertView.findViewById(R.id.kids_item_date);
        ImageView menu = (ImageView) convertView.findViewById(R.id.kids_item_menu);

        name.setText(item.getName());
        age.setText(convertView.getResources().getString(R.string.kids_age, item.getAge()));

        menu.setImageResource(item.isWritten() ? android.R.drawable.checkbox_on_background : android.R.drawable.checkbox_off_background);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new KidCommentDialog();
                getFragment().showPopup(newFragment, DetailActivity.DIALOG_TAG_DETAIL_COMMENT);
            }
        });
    }
}
