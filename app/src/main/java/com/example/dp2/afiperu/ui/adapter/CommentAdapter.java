package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Comment;

import java.util.List;

public class CommentAdapter extends BaseArrayAdapter<Comment> {

    public static final int FILTER_COLOR = 0xff7fb2d6;

    public CommentAdapter(Context context, BaseFragment fragment, List<Comment> objects) {
        super(context, fragment, R.layout.comment_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, Comment item, int position) {
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

        Comment item = getItem(position);
        name.setText(item.getName());
        age.setText(item.getComment());




  */
    }
}
