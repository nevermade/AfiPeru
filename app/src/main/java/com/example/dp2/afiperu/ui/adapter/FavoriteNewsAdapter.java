package com.example.dp2.afiperu.ui.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.domain.NewsArticle;
import com.example.dp2.afiperu.domain.News;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.NewsArticleFragment;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;

import java.util.List;

public class FavoriteNewsAdapter extends BaseArrayAdapter<News> {

    public static final int EMPTY_STAR = R.drawable.ic_star_empty;
    public static final int FULL_STAR = R.drawable.ic_star_full;
    public static int getStarDrawable(boolean isFavorite){
        if(!isFavorite){
            return R.drawable.ic_star_empty;
        }else{
            return R.drawable.ic_star_full;
        }
    }

    public FavoriteNewsAdapter(Context context, BaseFragment fragment, List<News> objects) {
        super(context, fragment, R.layout.news_list_item, objects);
    }

    @Override
    public void prepareItemView(final View convertView, final News item, final int position) {
        ImageView pic = (ImageView) convertView.findViewById(R.id.news_item_pic);
        TextView title = (TextView) convertView.findViewById(R.id.news_item_title);
        ImageView authorIcon = (ImageView) convertView.findViewById(R.id.news_item_author_icon);
        TextView authorName = (TextView) convertView.findViewById(R.id.news_item_author_name);
        TextView date = (TextView) convertView.findViewById(R.id.news_item_date);
        ImageView favoriteIcon = (ImageView) convertView.findViewById(R.id.news_item_icon_favorite);

        setImage(pic, item.getPicURL(), "news_" + item.getPicId() + ".jpg");
        title.setText(item.getTitle());
        setImage(authorIcon, item.getAuthorIconURL(), "news_author_" + item.getAuthorIconId() + ".png");
        authorName.setText(item.getAuthorName());
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getUploadDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(formattedDate);
        favoriteIcon.setImageResource(getStarDrawable(item.isFavorite()));
        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.news_item_icon_favorite) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == DialogInterface.BUTTON_POSITIVE){

                            }else if(which == DialogInterface.BUTTON_NEGATIVE){

                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.delete_news_favorite).setPositiveButton(android.R.string.yes, dialogClickListener)
                            .setNegativeButton(android.R.string.no, dialogClickListener);
                    AlertDialog alert = builder.create();
                    alert.show();
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
                    NewsArticle arg = new NewsArticle(item.getPicId(), item.getPicURL(),
                            item.getTitle(), item.getAuthorName(), item.getUploadDate(),
                            convertView.getResources().getString(R.string.article_example),
                            new MarkerInfo(-12.0731492, -77.0819083, MarkerInfo.MARKER_KIND_EVENT_ADDRESS, null));
                    args.putSerializable(NewsArticleFragment.NEWS_ARTICLE_ARG, arg);
                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_DETALLE_NOTICIAS);
                    newsArticleFragment.setArguments(args);
                    getFragment().addFragmentToStack(newsArticleFragment, DetailActivity.FRAGMENT_DETALLE_NOTICIAS);
                }
            }
        });
    }
}
