package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.BlogArticle;

/**
 * Created by Fernando on 23/09/2015.
 */
public class BlogArticleFragment extends BaseFragment {

    public static final String BLOG_ARTICLE_ARG = "blog_article_arg";

    public BlogArticleFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.blog_detail;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        BlogArticle article = (BlogArticle)args.getSerializable(BLOG_ARTICLE_ARG);

        TextView title = (TextView)rootView.findViewById(R.id.blog_detail_title);
        title.setText(article.getTitle());
        TextView date = (TextView)rootView.findViewById(R.id.blog_detail_date);
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), article.getUploadDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(getResources().getString(R.string.news_article_author_date, article.getAuthorName(), formattedDate));
        TextView content = (TextView)rootView.findViewById(R.id.blog_detail_content);
        content.setText(article.getContent());

        ImageView authorPic = (ImageView)rootView.findViewById(R.id.blog_detail_author_pic);
        setImage(authorPic, article.getAuthorIconURL(), "blog_author_" + article.getAuthorIconId() + ".jpg");
        TextView authorName = (TextView)rootView.findViewById(R.id.blog_detail_author_name);
        authorName.setText(article.getAuthorName());
        TextView authorShortDesc = (TextView)rootView.findViewById(R.id.blog_detail_author_short_desc);
        authorShortDesc.setText(article.getAuthorShortDesc());
    }

}
