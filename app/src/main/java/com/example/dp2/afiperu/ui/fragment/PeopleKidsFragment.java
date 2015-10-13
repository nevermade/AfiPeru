package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.ui.adapter.PeopleKidsAdapter;
import com.example.dp2.afiperu.domain.PeopleKids;

import java.util.ArrayList;

/**
 * Created by Nevermade on 02/10/2015.
 */
public class PeopleKidsFragment extends BaseFragment {
    public static final String PEOPLE_KIDS_ARG = "people_kids_arg";

    public PeopleKidsFragment() {super(); }


    @Override
    public int getLayout() {
        return R.layout.people_kids;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<PeopleKids> peopleKids = (ArrayList<PeopleKids>)args.getSerializable(PEOPLE_KIDS_ARG);
        PeopleKidsAdapter adapter = new PeopleKidsAdapter(getContext(), this, peopleKids);

        ListView peopleKidsList = (ListView)rootView.findViewById(R.id.people_kids_list);
        peopleKidsList.setAdapter(adapter);
        peopleKidsList.setEmptyView(rootView.findViewById(R.id.empty_people_kids_list));
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

}
