package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerCommentKidComponent;
import com.example.dp2.afiperu.module.CommentKidModule;
import com.example.dp2.afiperu.presenter.CommentKidPresenter;
import com.example.dp2.afiperu.rest.model.AttendanceChild;
import com.example.dp2.afiperu.ui.adapter.CommentKidAdapter;
import com.example.dp2.afiperu.ui.viewmodel.CommentKidView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class CommentKidFragment extends BaseFragment implements CommentKidView {

    @Inject
    CommentKidPresenter presenter;
    @Inject
    CommentKidAdapter adapter;

    public static final String KIDS_ARG = "kids_arg";

    public CommentKidFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.kids;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<AttendanceChild> children = (ArrayList<AttendanceChild>)args.getSerializable(KIDS_ARG);

        ListView newsList = (ListView)rootView.findViewById(R.id.kids_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_kids_list));

        adapter.update(children);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerCommentKidComponent.builder()
                .afiAppComponent(appComponent)
                .commentKidModule(new CommentKidModule(this))
                .build()
                .inject(this);
    }

    public void makeComment(int kidId, int face, String message){
        presenter.makeComment(getContext(), kidId, face, message);
    }

}
