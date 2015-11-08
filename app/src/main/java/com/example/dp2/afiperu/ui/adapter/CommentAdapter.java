package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Comment;

import java.util.List;

public class CommentAdapter extends BaseArrayAdapter<Comment> {

    public CommentAdapter(Context context, BaseFragment fragment, List<Comment> objects) {
        super(context, fragment, R.layout.comment_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, Comment item, int position) {
        TextView author = (TextView) convertView.findViewById(R.id.comments_author);
        TextView text = (TextView) convertView.findViewById(R.id.comments_text);
        ImageView face = (ImageView) convertView.findViewById(R.id.comments_image);

        author.setText(item.getAuthor().getName() + " " + item.getAuthor().getLastName());
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
