package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.domain.Comment;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.fragment.CommentFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Kid2Adapter extends BaseArrayAdapter<Kid> {

    public static final int FILTER_COLOR = 0xff7fb2d6;

    public Kid2Adapter(Context context, BaseFragment fragment, List<Kid> objects) {
        super(context, fragment, R.layout.kid_list_item2, objects);
    }

    @Override
    public void prepareItemView(View convertView, Kid item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.kids_item_name2);
        TextView age = (TextView) convertView.findViewById(R.id.kids_item_date2);
        ImageView menu = (ImageView) convertView.findViewById(R.id.kids_item_menu2);

        name.setText(item.getNames());
        age.setText(convertView.getResources().getString(R.string.kids_age, item.getAge()));
        menu.setImageResource(false? android.R.drawable.checkbox_on_background : android.R.drawable.checkbox_off_background);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (v.getId() == R.id.sessions_item_menu) {
                //if(layout == R.layout.comments) {
                Bundle args = new Bundle();
                ArrayList<Comment> comments = new ArrayList<>();
                Calendar calendar = new GregorianCalendar(2015, 8, 20, 15, 21);
                comments.add(new Comment("Juan Perez", "Se comporto a la altura de las circunstancias, muy partic..."));
                calendar = new GregorianCalendar(2015, 8, 19, 12, 05);
                comments.add(new Comment("Andrea Abarca", "Muy bien."));
                calendar = new GregorianCalendar(2015, 8, 13, 9, 37);
                comments.add(new Comment("Diego Maguiño ", "Se esforzo en actividades."));
                calendar = new GregorianCalendar(2015, 8, 10, 10, 02);
                comments.add(new Comment("Luis Miguel Miranda", "Muy bien."));
                calendar = new GregorianCalendar(2015, 8, 16, 13, 14);
                comments.add(new Comment("Kevin Brown", "Muy participativo."));
                calendar = new GregorianCalendar(2015, 8, 20, 15, 24);
                comments.add(new Comment("Maria Garcia", "Excelente"));
                //Collections.sort(sessions);
                args.putSerializable(CommentFragment.COMMENT_ARG, comments);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, 12);
                CommentFragment fragment = new CommentFragment();
                fragment.setArguments(args);
                getFragment().addFragmentToStack(fragment, DetailActivity.FRAGMENT_LISTA_COMENTARIOS);

                //}


                //}
            }
        });

    }



}
