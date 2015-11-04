package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.DownloadedUserFragment;

import java.util.List;

public class DocumentsAdapter extends BaseArrayAdapter<Document> {

    public DocumentsAdapter(Context context, BaseFragment fragment, List<Document> objects) {
        super(context, fragment, R.layout.documents_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, Document item, int position) {
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

        name.setText(item.getName());
        date.setText(convertView.getResources().getString(R.string.documents_size_date, item.getFilesize(), item.getUploadDate()));
        icon.setImageResource(DocumentsAdapter.getDocumentIconId(item.getName()));

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
                                    Toast.makeText(getContext(), "Ver documento...", Toast.LENGTH_LONG).show();
                                    break;
                                case R.id.docs_menu_download:
                                    Toast.makeText(getContext(), "Descargar documento...", Toast.LENGTH_LONG).show();
                                    break;
                                case R.id.docs_menu_check_views:
                                    Toast.makeText(getContext(), "Revisar vistas...", Toast.LENGTH_LONG).show();
                                    Bundle args = new Bundle();
                                    DownloadedUserFragment fragment = new DownloadedUserFragment();
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
}
