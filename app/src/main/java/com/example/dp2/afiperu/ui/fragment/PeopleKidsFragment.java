package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerKidComponent;
import com.example.dp2.afiperu.module.KidModule;
import com.example.dp2.afiperu.presenter.KidPresenter;
import com.example.dp2.afiperu.syncmodel.SyncKid;
import com.example.dp2.afiperu.ui.adapter.PeopleKidsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.KidView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Nevermade on 02/10/2015.
 */
public class PeopleKidsFragment extends BaseFragment implements KidView{
    public static final String PEOPLE_KIDS_ARG = "people_kids_arg";

    @Inject
    KidPresenter presenter;
    @Inject
    PeopleKidsAdapter adapter;

    public PeopleKidsFragment() {super(); }


    @Override
    public int getLayout() {
        return R.layout.people_kids;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        //ArrayList<SyncKid> peopleKids = (ArrayList<SyncKid>)args.getSerializable(PEOPLE_KIDS_ARG);
        //PeopleKidsAdapter adapter = new PeopleKidsAdapter(getContext(), this, peopleKids);

        ListView peopleKidsList = (ListView)rootView.findViewById(R.id.people_kids_list);
        peopleKidsList.setAdapter(adapter);
        peopleKidsList.setEmptyView(rootView.findViewById(R.id.empty_people_kids_list));
        presenter.getAllKids(getContext());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerKidComponent.builder()
                .afiAppComponent(appComponent)
                .kidModule(new KidModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showKids(ArrayList<SyncKid> kids) {
        adapter.update(kids);
    }


}
