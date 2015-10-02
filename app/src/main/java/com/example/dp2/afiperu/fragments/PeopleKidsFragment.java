package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.PeopleKidsAdapter;
import com.example.dp2.afiperu.lists.PeopleKidsItem;

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
        ArrayList<PeopleKidsItem> peopleKidsItems = (ArrayList<PeopleKidsItem>)args.getSerializable(PEOPLE_KIDS_ARG);
        PeopleKidsAdapter adapter = new PeopleKidsAdapter(getContext(), this, peopleKidsItems);

        ListView peopleKidsList = (ListView)rootView.findViewById(R.id.people_kids_list);
        peopleKidsList.setAdapter(adapter);
        peopleKidsList.setEmptyView(rootView.findViewById(R.id.empty_people_kids_list));
    }

}
