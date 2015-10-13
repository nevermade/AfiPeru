package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.ui.adapter.CommentAdapter;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class CommentFragment extends BaseFragment {

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
        ArrayList<Comment> comments = (ArrayList<Comment>)args.getSerializable(COMMENT_ARG);
        CommentAdapter adapter = new CommentAdapter(getContext(), this, comments);

        ListView newsList = (ListView)rootView.findViewById(R.id.comments_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_comments_list));
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

}
