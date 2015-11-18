package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.syncmodel.SyncNews;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.fragment.NewsArticleFragment;
import com.example.dp2.afiperu.ui.fragment.NewsFragment;

import java.util.List;

public class NewsAdapter extends BaseArrayAdapter<SyncNews> {

    public static int getStarDrawable(boolean isFavorite){
        if(!isFavorite){
            return R.drawable.ic_star_empty;
        }else{
            return R.drawable.ic_star_full;
        }
    }

    public NewsAdapter(Context context, BaseFragment fragment, List<SyncNews> objects) {
        super(context, fragment, R.layout.news_list_item, objects);
    }

    public BaseFragment getTopFragment(){
        return (BaseFragment)super.getFragment().getParentFragment();
    }

    @Override
    public void prepareItemView(final View convertView, final SyncNews item, final int position) {
        ImageView pic = (ImageView) convertView.findViewById(R.id.news_item_pic);
        TextView title = (TextView) convertView.findViewById(R.id.news_item_title);
        TextView date = (TextView) convertView.findViewById(R.id.news_item_date);
        ImageView favoriteIcon = (ImageView) convertView.findViewById(R.id.news_item_icon_favorite);

        setImage(pic, item.getImageURL(), "news_" + item.getNewsId() + ".jpg");
        title.setText(item.getTitle());
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(formattedDate);
        favoriteIcon.setImageResource(getStarDrawable(item.getIsFavorite() == 1));
        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.news_item_icon_favorite) {
                    item.toggleFavorite();
                    item.save();
                    ImageView favoriteIcon = (ImageView) v;
                    favoriteIcon.setImageResource(getStarDrawable(item.getIsFavorite() == 1));
                }
            }
        });

        RelativeLayout newsItem = (RelativeLayout)convertView.findViewById(R.id.news_item);
        newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.news_item){
                    NewsArticleFragment newsArticleFragment = new NewsArticleFragment();
                    Bundle args = new Bundle();
                    args.putSerializable(NewsArticleFragment.NEWS_ARTICLE_ARG, item);
                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_DETALLE_NOTICIAS);
                    newsArticleFragment.setArguments(args);
                    getFragment().addFragmentToStack(newsArticleFragment, DetailActivity.FRAGMENT_DETALLE_NOTICIAS);
                }
            }
        });
    }
}
