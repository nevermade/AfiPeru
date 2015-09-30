package com.example.dp2.afiperu.com.example.dp2.layouttest.lists;


import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.fragments.BaseFragment;

import java.util.List;

public class DocumentsAdapter extends BaseArrayAdapter<DocumentsItem> {

    public DocumentsAdapter(Context context, BaseFragment fragment, List<DocumentsItem> objects) {
        super(context, fragment, R.layout.documents_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, DocumentsItem item, int position) {
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
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getUploadDate(),
                DateUtils.DAY_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(convertView.getResources().getString(R.string.documents_size_date, item.getFilesize(), formattedDate));
        icon.setImageResource(item.getIconId());

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
                                    break;
                            }
                            return true;
                        }
                    });
                }
            }
        });
    }
}
