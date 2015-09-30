package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.com.example.dp2.layouttest.lists.KidItem;
import com.example.dp2.afiperu.com.example.dp2.layouttest.lists.KidAdapter;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class KidsFragment extends BaseFragment{

    public static final String KIDS_ARG = "kids_arg";

    public KidsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.kids;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<KidItem> kids = (ArrayList<KidItem>)args.getSerializable(KIDS_ARG);
        KidAdapter adapter = new KidAdapter(getContext(), this, kids);

        ListView newsList = (ListView)rootView.findViewById(R.id.kids_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty_kids_list));
    }

}
