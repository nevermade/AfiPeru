package com.example.dp2.afiperu.ui.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Document;
import com.example.dp2.afiperu.ui.viewmodel.PeriodReportView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class PeriodReportAdapter extends BaseArrayAdapter<Document>{
    Context context;
    PeriodReportView view;
    ArrayList<Document> docs;
    public PeriodReportAdapter(Context context, BaseFragment fragment, List objects) {
        super(context, fragment, R.layout.period_report_list_item, objects);
        this.context=context;
        this.view=(PeriodReportView) fragment;
        this.docs=(ArrayList<Document>)objects;
    }

    public void updateDocs(ArrayList<Document> docs){
        clear();
        addAll(docs);
        notifyDataSetChanged();
    }

    @Override
    public void prepareItemView(View convertView, Document item, int position) {
        ImageView icon = (ImageView) convertView.findViewById(R.id.period_report_item_icon);
        TextView name = (TextView) convertView.findViewById(R.id.period_report_item_name);
        TextView date = (TextView) convertView.findViewById(R.id.period_report_item_size_date);
        //ImageView menu = (ImageView) convertView.findViewById(R.id.docs_item_menu);

        name.setText(item.getName());
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getUploadDate(),
                DateUtils.DAY_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(convertView.getResources().getString(R.string.documents_size_date, item.getFilesize(), formattedDate));
        icon.setImageResource(item.getIconId());
    }


}
