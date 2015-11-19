package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.syncmodel.SyncDocument;
import com.example.dp2.afiperu.syncmodel.SyncDocumentUser;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.DocumentsFragment;
import com.example.dp2.afiperu.ui.fragment.DownloadedUserFragment;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;

public class DocumentsAdapter extends BaseArrayAdapter<SyncDocument> {

    public DocumentsAdapter(Context context, BaseFragment fragment, List<SyncDocument> objects) {
        super(context, fragment, R.layout.documents_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, final SyncDocument docItem, int position) {
        ImageView icon = (ImageView) convertView.findViewById(R.id.docs_item_icon);
        TextView name = (TextView) convertView.findViewById(R.id.docs_item_name);
        TextView date = (TextView) convertView.findViewById(R.id.docs_item_size_date);
        ImageView menu = (ImageView) convertView.findViewById(R.id.docs_item_menu);
        int color;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            color = convertView.getResources().getColor(R.color.toolbar_background, null);
        }else{
            color = convertView.getResources().getColor(R.color.toolbar_background);
        }
        menu.setColorFilter(color);

        name.setText(docItem.getName());
        date.setText(convertView.getResources().getString(R.string.documents_size_date, docItem.getFilesize(), docItem.getUploadDate()));
        icon.setImageResource(DocumentsAdapter.getDocumentIconId(docItem.getName()));

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.docs_item_menu) {
                    PopupMenu popup = new PopupMenu(getContext(), v);
                    popup.getMenuInflater().inflate(R.menu.docs_menu_afi, popup.getMenu());
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.docs_menu_view:
                                    if (NetworkManager.isNetworkConnected(getContext())) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(Constants.DOCS_URL + docItem.getUrl()));
                                        getFragment().startActivity(intent);
                                        ((DocumentsFragment) getFragment()).recordVisualization(docItem.getDocId());
                                    } else if (docItem.getLastUri() != null) {
                                        ((DetailActivity) getFragment().getActivity()).openFile(docItem.getLastUri());
                                    } else {
                                        showNoInternetMessage();
                                    }
                                    break;
                                case R.id.docs_menu_download:
                                    if (NetworkManager.isNetworkConnected(getContext())) {
                                        downloadDocument(docItem, Constants.DOCS_URL + docItem.getUrl());
                                        ((DocumentsFragment) getFragment()).recordVisualization(docItem.getDocId());
                                    } else if (docItem.getLastUri() != null) {
                                        ((DetailActivity) getFragment().getActivity()).openFile(docItem.getLastUri());
                                    } else {
                                        showNoInternetMessage();
                                    }
                                    break;
                                case R.id.docs_menu_check_views:
                                    Bundle args = new Bundle();
                                    DownloadedUserFragment fragment = new DownloadedUserFragment();
                                    ArrayList<SyncDocumentUser> users = new ArrayList<>();
                                    users.addAll(docItem.queryUsers());
                                    args.putSerializable(DownloadedUserFragment.LIST_ARG, users);
                                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_USUARIOS_REVISADO);
                                    fragment.setArguments(args);
                                    getFragment().addFragmentToStack(fragment, DetailActivity.FRAGMENT_USUARIOS_REVISADO);
                                    break;
                            }
                            return true;
                        }
                    });
                }
            }
        });
    }

    public static int getDocumentIconId(String name){
        if(name.endsWith(".doc") || name.endsWith(".docx")){
            return R.drawable.ic_docs_doc;
        }else if(name.endsWith(".xls") || name.endsWith(".xlsx")){
            return R.drawable.ic_docs_xls;
        }else if(name.endsWith(".pdf")){
            return R.drawable.ic_docs_pdf;
        }else{
            return R.drawable.ic_docs_generic;
        }
    }

    public void showNoInternetMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.no_internet).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
