package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.lists.Kid2Adapter;
import com.example.dp2.afiperu.lists.KidAdapter;
import com.example.dp2.afiperu.lists.KidItem;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class Kids2Fragment extends BaseFragment{

    public static final String KIDS_ARG = "kids_arg";

    public Kids2Fragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.kids2;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<KidItem> kids = (ArrayList<KidItem>)args.getSerializable(KIDS_ARG);
        Kid2Adapter adapter = new Kid2Adapter(getContext(), this, kids);

        ListView newsList = (ListView)rootView.findViewById(R.id.kids2_list);
        newsList.setAdapter(adapter);
        newsList.setEmptyView(rootView.findViewById(R.id.empty2_kids_list));




    }





}
