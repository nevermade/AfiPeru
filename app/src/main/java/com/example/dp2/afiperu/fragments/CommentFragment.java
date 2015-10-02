package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.CommentAdapter;
import com.example.dp2.afiperu.lists.CommentItem;
import com.example.dp2.afiperu.lists.SessionAdapter;
import com.example.dp2.afiperu.lists.SessionItem;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class CommentFragment extends BaseFragment{

    public static final String COMMENT_ARG = "comment_arg";

    public CommentFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.comments;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<CommentItem> comments = (ArrayList<CommentItem>)args.getSerializable(COMMENT_ARG);
        CommentAdapter adapter = new CommentAdapter(getContext(), this, comments);

        ListView newsList = (ListView)rootView.findViewById(R.id.comments_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_comments_list));
    }

}
