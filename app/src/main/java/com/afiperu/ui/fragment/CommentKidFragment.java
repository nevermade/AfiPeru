package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerCommentKidComponent;
import com.afiperu.module.CommentKidModule;
import com.afiperu.presenter.CommentKidPresenter;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.ui.adapter.CommentKidAdapter;
import com.afiperu.ui.viewmodel.CommentKidView;

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

    private int sessionId;

    public CommentKidFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.kids;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<SyncAttendanceChild> children = (ArrayList<SyncAttendanceChild>)args.getSerializable(KIDS_ARG);

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

    public void makeComment(SyncAttendanceChild child, SyncComment comment){
        presenter.makeComment(getContext(), child, comment);
    }

}
