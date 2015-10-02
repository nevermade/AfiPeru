package com.example.dp2.afiperu.lists;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.dp2.afiperu.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.fragments.AttendanceFragment;
import com.example.dp2.afiperu.fragments.BaseFragment;
import com.example.dp2.afiperu.fragments.Kids2Fragment;
import com.example.dp2.afiperu.fragments.KidsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentAdapter extends BaseArrayAdapter<CommentItem> {

    public static final int FILTER_COLOR = 0xff7fb2d6;

    public CommentAdapter(Context context, BaseFragment fragment, List<CommentItem> objects) {
        super(context, fragment, R.layout.comment_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, CommentItem item, int position) {
        //ImageView icon = (ImageView) convertView.findViewById(R.id.comments_item_photo);
        TextView name = (TextView) convertView.findViewById(R.id.comments_item_name);
        TextView age = (TextView) convertView.findViewById(R.id.comments_item_date);
        ImageView menu = (ImageView) convertView.findViewById(R.id.comments_item_menu);
        int color;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            color = convertView.getResources().getColor(R.color.toolbar_background, null);
        }else{
            color = convertView.getResources().getColor(R.color.toolbar_background);
        }



//        ((DetailActivity)getActivity()).yourPublicMethod();
        //menu.setColorFilter(color);

        name.setText(item.getName());
        age.setText(item.getComment());
        /*
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(formattedDate);
*/
/*


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment_list_item, null);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.comments_item_photo);
        TextView name = (TextView) convertView.findViewById(R.id.comments_item_name);
        TextView age = (TextView) convertView.findViewById(R.id.comments_item_date);
        ImageView menu = (ImageView) convertView.findViewById(R.id.comments_item_menu);

        CommentItem item = getItem(position);
        name.setText(item.getName());
        age.setText(item.getComment());




  */
    }
}
