package com.afiperu.ui.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.syncmodel.SyncNews;
import com.afiperu.ui.fragment.FavoriteNewsFragment;

import java.util.List;

public class FavoriteNewsAdapter extends NewsAdapter {

    public FavoriteNewsAdapter(Context context, BaseFragment fragment, List<SyncNews> objects) {
        super(context, fragment, objects);
    }

    @Override
    public void prepareItemView(final View convertView, final SyncNews item, final int position) {
        super.prepareItemView(convertView, item, position);
        ImageView favoriteIcon = (ImageView) convertView.findViewById(R.id.news_item_icon_favorite);
        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        item.toggleFavorite();
                        item.save();
                        ((FavoriteNewsFragment)(getFragment())).updateFavorites();
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.delete_news_favorite).setPositiveButton(android.R.string.yes, dialogClickListener)
                        .setNegativeButton(android.R.string.no, null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
