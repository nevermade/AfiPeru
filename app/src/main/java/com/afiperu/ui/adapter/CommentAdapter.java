package com.afiperu.ui.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afiperu.R;
import com.afiperu.common.BaseArrayAdapter;
import com.afiperu.common.BaseFragment;
import com.afiperu.syncmodel.SyncComment;

import java.util.List;

public class CommentAdapter extends BaseArrayAdapter<SyncComment> {

    public CommentAdapter(Context context, BaseFragment fragment, List<SyncComment> objects) {
        super(context, fragment, R.layout.comment_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, SyncComment item, int position) {
        TextView author = (TextView) convertView.findViewById(R.id.comments_author);
        TextView text = (TextView) convertView.findViewById(R.id.comments_text);
        ImageView face = (ImageView) convertView.findViewById(R.id.comments_image);

        author.setText(item.getAuthorNames());
        text.setText(item.getMessage());
        switch(item.getFace()){
            case 0:
                face.setVisibility(View.GONE);
                break;
            case 1:
                face.setImageResource(R.drawable.happy);
                break;
            case -1:
                face.setImageResource(R.drawable.sad);
                break;
        }
    }
}
