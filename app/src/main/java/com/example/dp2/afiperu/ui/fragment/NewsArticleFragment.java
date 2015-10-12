package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.domain.MarkerInfo;
import com.example.dp2.afiperu.domain.NewsArticle;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class NewsArticleFragment extends BaseFragment {

    public static final String NEWS_ARTICLE_ARG = "news_article_arg";

    public NewsArticleFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.news_detail;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        final NewsArticle article = (NewsArticle)args.getSerializable(NEWS_ARTICLE_ARG);

        ImageView pic = (ImageView)rootView.findViewById(R.id.news_detail_pic);
        setImage(pic, article.getPicURL(), "news_" + article.getPicId() + ".jpg");
        TextView title = (TextView)rootView.findViewById(R.id.news_detail_title);
        title.setText(article.getTitle());
        TextView date = (TextView)rootView.findViewById(R.id.news_detail_date);
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), article.getUploadDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(getResources().getString(R.string.news_article_author_date, article.getAuthorName(), formattedDate));
        TextView content = (TextView)rootView.findViewById(R.id.news_detail_content);
        content.setText(article.getContent());
        LinearLayout mapIcon = (LinearLayout)rootView.findViewById(R.id.news_detail_map_icon);
        if(article.getMarker() != null) {
            mapIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.news_detail_map_icon) {
                        MapFragment mapFragment = new MapFragment();
                        Bundle args = new Bundle();
                        ArrayList<MarkerInfo> markerList = new ArrayList<>();
                        markerList.add(article.getMarker());
                        args.putSerializable(MapFragment.MARKERS_ARG, markerList);
                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_MAPA);
                        mapFragment.setArguments(args);
                        addFragmentToStack(mapFragment, DetailActivity.FRAGMENT_MAPA);
                    }
                }
            });
        }else{
            mapIcon.setVisibility(View.GONE);
        }
    }

}
