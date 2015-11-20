package com.example.dp2.afiperu.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.syncmodel.SyncNews;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.util.Constants;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class NewsArticleFragment extends BaseFragment {

    public static final String NEWS_ARTICLE_ARG = "news_article_arg";

    private String title;
    private String url;

    public NewsArticleFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.news_detail;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        final SyncNews article = (SyncNews)args.getSerializable(NEWS_ARTICLE_ARG);
        title = article.getTitle();
        url = String.valueOf(article.getNewsId());

        ImageView pic = (ImageView)rootView.findViewById(R.id.news_detail_pic);
        setImage(pic, article.getImageURL(), "news_" + article.getNewsId() + ".jpg");
        TextView title = (TextView)rootView.findViewById(R.id.news_detail_title);
        title.setText(article.getTitle());
        TextView date = (TextView)rootView.findViewById(R.id.news_detail_date);
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), article.getDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(formattedDate);
        TextView content = (TextView)rootView.findViewById(R.id.news_detail_content);
        content.setText(article.getContent());
        LinearLayout mapIcon = (LinearLayout)rootView.findViewById(R.id.news_detail_map_icon);

        final MarkerInfo marker = null;//article.getMarker();
        if(marker != null) {
            mapIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.news_detail_map_icon) {
                        MapFragment mapFragment = new MapFragment();
                        Bundle args = new Bundle();
                        ArrayList<MarkerInfo> markerList = new ArrayList<>();
                        markerList.add(marker);
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

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    public void shareOnFacebook(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TITLE, title);
        intent.putExtra(Intent.EXTRA_TEXT, Constants.DRUPAL_ARTICLES_URL + url);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.share_option)));
    }

}
