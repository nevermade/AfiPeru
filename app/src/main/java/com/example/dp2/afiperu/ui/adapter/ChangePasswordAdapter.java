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
public class ChangePasswordAdapter {
    Context context;
    View.OnClickListener onBlogClickedListener;
    View.OnClickListener onStarClickedListener;
    ArrayList<Blog> blogs;



    public ChangePasswordAdapter(Context context, BaseFragment fragment, List<Blog> objects) {
//        super(context, fragment, R.layout.blogs_list_item, objects);
        this.context=context;
//        this.blogs=(ArrayList<Blog>)objects;
    }
/*
    public void updateBlogs(ArrayList<Blog> blogs){
        if(blogs!=null) {
            clear();
            addAll(blogs);
            notifyDataSetChanged();
        }
    }
*/

}
