package com.example.dp2.afiperu.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.domain.BlogArticle;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.BlogArticleFragment;
import com.example.dp2.afiperu.ui.fragment.BlogSearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 12/10/2015.
 */
public class BlogSearchAdapter extends BaseArrayAdapter<Blog> {
    Context context;
    View.OnClickListener onBlogClickedListener;
    View.OnClickListener onStarClickedListener;
    ArrayList<Blog> blogs;



    public BlogSearchAdapter(Context context, BaseFragment fragment, List<Blog> objects) {
        super(context, fragment, R.layout.blogs_list_item, objects);
        this.context=context;
        this.blogs=(ArrayList<Blog>)objects;
    }

    public void updateBlogs(ArrayList<Blog> blogs){
        if(blogs!=null) {
            clear();
            addAll(blogs);
            //notifyDataSetChanged();
        }
    }

    public void setOnBlogClickedListener(View.OnClickListener onBlogClickedListener) {
        this.onBlogClickedListener = onBlogClickedListener;
    }

    public void setOnStarClickedListener(View.OnClickListener onStarClickedListener) {
        this.onStarClickedListener = onStarClickedListener;
    }

    @Override
    public void prepareItemView(final View convertView, final Blog item, final int position) {


        TextView title = (TextView) convertView.findViewById(R.id.blogs_item_title);
        TextView author = (TextView) convertView.findViewById(R.id.blog_item_author);
        TextView uploadDate = (TextView) convertView.findViewById(R.id.blog_item_uploadDate);
        ImageView favoriteIcon= (ImageView) convertView.findViewById(R.id.blog_item_icon_favorite);

        title.setText(item.getTitle());
        author.setText(item.getAuthor());
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getUploadDate(),
                DateUtils.DAY_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        uploadDate.setText(formattedDate);
        favoriteIcon.setImageResource(getStarDrawable(item.isFavorite()));

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.blog_item_icon_favorite) {
                    boolean isFavorite = ((BlogSearchFragment) getFragment()).toggleFavorite(position);
                    ImageView favoriteIcon = (ImageView) v;
                    favoriteIcon.setImageResource(getStarDrawable(isFavorite));
                }
            }
        });

        LinearLayout blogItem = (LinearLayout)convertView.findViewById(R.id.blogs_item_text);
        blogItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.blogs_item_text){
                    BlogArticleFragment blogArticleFragment = new BlogArticleFragment();
                    Bundle args = new Bundle();
                    BlogArticle arg = new BlogArticle(item.getTitle(), item.getUploadDate(),
                            convertView.getResources().getString(R.string.article_example),
                            2001,
                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                            item.getAuthor(),
                            convertView.getResources().getString(R.string.author_short_desc_example));
                    args.putSerializable(BlogArticleFragment.BLOG_ARTICLE_ARG, arg);
                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_DETALLE_BLOG);
                    blogArticleFragment.setArguments(args);
                    getFragment().addFragmentToStack(blogArticleFragment, DetailActivity.FRAGMENT_DETALLE_BLOG);
                }
            }
        });

    }
    public static int getStarDrawable(boolean isFavorite){
        if(!isFavorite){
            return R.drawable.ic_star_empty;
        }else{
            return R.drawable.ic_star_full;
        }
    }
}
