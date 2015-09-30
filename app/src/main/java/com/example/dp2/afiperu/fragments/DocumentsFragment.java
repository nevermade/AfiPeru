package com.example.dp2.afiperu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.com.example.dp2.layouttest.lists.DocumentsAdapter;
import com.example.dp2.afiperu.com.example.dp2.layouttest.lists.DocumentsItem;

import java.util.ArrayList;

/**
 * Created by Fernando on 23/09/2015.
 */
public class DocumentsFragment extends BaseFragment{

    public static final String DOCUMENTS_ARG = "docs_arg";

    public DocumentsFragment(){
        super();
    }

    @Override
    public int getLayout(){
        return R.layout.documents;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ArrayList<DocumentsItem> documents = (ArrayList<DocumentsItem>)args.getSerializable(DOCUMENTS_ARG);
        DocumentsAdapter adapter = new DocumentsAdapter(getContext(), this, documents);

        ListView docsList = (ListView)rootView.findViewById(R.id.docs_list);
        docsList.setAdapter(adapter);
        docsList.setEmptyView(rootView.findViewById(R.id.empty_docs_list));
    }

}
