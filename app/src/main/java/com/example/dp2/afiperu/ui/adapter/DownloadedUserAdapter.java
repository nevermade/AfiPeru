package com.example.dp2.afiperu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.syncmodel.SyncDocumentUser;
import com.example.dp2.afiperu.ui.viewmodel.DownloadedUserView;

import java.util.List;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class DownloadedUserAdapter extends BaseArrayAdapter<SyncDocumentUser> {
    Context context;
    DownloadedUserView view;
    public DownloadedUserAdapter(Context context, BaseFragment fragment, List<SyncDocumentUser> objects) {
        super(context, fragment, R.layout.downloaded_user_list_item, objects);
        this.context=context;
        this.view=(DownloadedUserView)fragment;
    }

    @Override
    public void prepareItemView(View convertView, SyncDocumentUser item, int position) {
        TextView name= (TextView)convertView.findViewById(R.id.downloaded_user_item_name);
        TextView code= (TextView)convertView.findViewById(R.id.downloaded_user_item_code);
        ImageView checkbox= (ImageView)convertView.findViewById(R.id.downloaded_user_item_checkbox);

        name.setText(item.getNames());
        code.setText(item.getUsername());
        checkbox.setImageResource(getDrawable(item.getSeen() == 1));
    }

    private int getDrawable(boolean hasDownloaded){
        if(hasDownloaded){
            return R.drawable.ic_checked_checkbox;
        } else {
            return R.drawable.ic_unchecked_checkbox;
        }
    }
}
