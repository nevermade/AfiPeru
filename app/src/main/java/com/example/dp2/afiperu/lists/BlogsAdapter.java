package com.example.dp2.afiperu.lists;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.fragments.BaseFragment;
import com.example.dp2.afiperu.fragments.BlogsFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Nevermade on 30/09/2015.
 */
public class BlogsAdapter extends BaseArrayAdapter<BlogsItem> {

    public static int getStarDrawable(boolean isFavorite){
        if(!isFavorite){
            return R.drawable.ic_star_empty;
        }else{
            return R.drawable.ic_star_full;
        }
    }

    public BlogsAdapter(Context context, BaseFragment fragment, List<BlogsItem> objects) {
        super(context, fragment, R.layout.blogs_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, BlogsItem item,final int position) {

        TextView title = (TextView) convertView.findViewById(R.id.blogs_item_title);
        TextView author = (TextView) convertView.findViewById(R.id.blog_item_author);
        TextView uploadDate = (TextView) convertView.findViewById(R.id.blog_item_uploadDate);
        ImageView favoriteIcon= (ImageView) convertView.findViewById(R.id.blog_item_icon_favorite);

        title.setText(item.getTitle());
        author.setText(item.getAuthor());
        Calendar calendar= new GregorianCalendar();
        calendar.setTimeInMillis(item.getUploadDate());
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        uploadDate.setText(day + "/" + month + "/" + year);
        favoriteIcon.setImageResource(getStarDrawable(item.isFavorite()));

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.blog_item_icon_favorite) {
                    boolean isFavorite = ((BlogsFragment) getFragment()).toggleFavorite(position);
                    ImageView favoriteIcon = (ImageView) v;
                    favoriteIcon.setImageResource(getStarDrawable(isFavorite));
                }
            }
        });


    }



}
