package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.component.DaggerCommentComponent;
import com.afiperu.module.CommentModule;
import com.afiperu.presenter.CommentPresenter;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.syncmodel.SyncKid;
import com.afiperu.ui.adapter.CommentAdapter;
import com.afiperu.ui.viewmodel.CommentView;
import com.afiperu.util.NetworkManager;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 23/09/2015.
 */
public class CommentFragment extends BaseFragment implements CommentView {

    @Inject
    CommentPresenter presenter;
    @Inject
    CommentAdapter adapter;

    public static final String KID_ARG = "kid_arg";
    public int kidid = 0;
    public CommentFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.comments;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){

        TextView name = (TextView)rootView.findViewById(R.id.comments_kid_name);
        TextView gender = (TextView)rootView.findViewById(R.id.comments_kid_gender);
        TextView age = (TextView)rootView.findViewById(R.id.comments_kid_age);
        TextView sessions = (TextView)rootView.findViewById(R.id.comments_kid_sessions);

        ListView newsList = (ListView)rootView.findViewById(R.id.comments_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_comments_list));

        SyncKid child = (SyncKid)args.getSerializable(KID_ARG);
        kidid=child.getKidId();
        name.setText(child.getNames());
        gender.setText(child.getGender() == 0 ? getResources().getString(R.string.sex_male)
                : getResources().getString(R.string.sex_female));
        age.setText(getResources().getString(R.string.kids_age, child.getAge()));
        sessions.setText(child.getSessions().toString());

        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }
        presenter.getAllComments(getContext(), child.getKidId());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerCommentComponent.builder()
                .afiAppComponent(appComponent)
                .commentModule(new CommentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showComments(List<SyncComment> comments){
        adapter.update(comments);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(){
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }
    @Override
    public void onSearch(String query){
        presenter.queryKidAndComment(getContext(),kidid,query,-1,-1,"Más actual");
    }



    @Override
    public void onCommentAdvancedSearch(String authorOrContent, long fromDate, long toDate, String orderBy){
        presenter.queryKidAndComment(getContext(),kidid,authorOrContent,fromDate,toDate,orderBy);
    }

}
