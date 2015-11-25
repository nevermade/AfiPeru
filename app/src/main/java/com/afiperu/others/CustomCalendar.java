package com.afiperu.others;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afiperu.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Fernando on 16/11/2015.
 */
public class CustomCalendar extends LinearLayout {

    public static final String[] MONTHS = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"
    };

    private CalendarAdapter calendarAdapter;
    private int currentMonth;
    private int currentYear;
    private List<Integer> markedDates = new ArrayList<>();

    private OnDateSelectedListener listener;

    public void setOnDateSelectedListener(OnDateSelectedListener listener){
        this.listener = listener;
    }

    public CustomCalendar(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.custom_calendar_view, this);

        calendarAdapter = new CalendarAdapter(getContext(), new ArrayList<Integer>());
        GridView grid = (GridView)rootView.findViewById(R.id.custom_calendar_grid);
        grid.setAdapter(calendarAdapter);

        ImageView prevButton = (ImageView)rootView.findViewById(R.id.custom_calendar_prev);
        prevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonth--;
                if(currentMonth == 0){
                    currentMonth = 12;
                    currentYear--;
                }
                updateCalendar(currentMonth, currentYear, CustomCalendar.this.getRootView());
            }
        });
        ImageView nextButton = (ImageView)rootView.findViewById(R.id.custom_calendar_next);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonth++;
                if (currentMonth == 13) {
                    currentMonth = 1;
                    currentYear++;
                }
                updateCalendar(currentMonth, currentYear, CustomCalendar.this.getRootView());
            }
        });

        Calendar calendar = new GregorianCalendar();
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentYear = calendar.get(Calendar.YEAR);
        updateCalendar(currentMonth, currentYear, rootView);
    }

    private void updateCalendar(int month, int year, View view){
        TextView title = (TextView)view.findViewById(R.id.custom_calendar_title);
        title.setText(MONTHS[month-1] + " " + year);

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK);
        int goBack;
        if(monthBeginningCell == Calendar.SUNDAY){
            goBack = 6;
        }else{
            goBack = monthBeginningCell - 2;
        }
        calendar.add(Calendar.DAY_OF_MONTH, -goBack);

        final ArrayList<Integer> cells = new ArrayList<>();
        for(int i=0; i<42; i++){
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            cells.add(y*10000 + (m+1)*100 + d);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendarAdapter.update(cells);

        GridView grid = (GridView)view.findViewById(R.id.custom_calendar_grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener != null){
                    listener.onSelectDate(cells.get(position));
                }
            }
        });
    }

    public void updateMarkedDates(List<Integer> markedDates){
        if(markedDates != null){
            this.markedDates = markedDates;
        }else{
            this.markedDates.clear();
        }
        calendarAdapter.notifyDataSetChanged();
    }

    public class CalendarAdapter extends ArrayAdapter<Integer> {

        public CalendarAdapter(Context context, List<Integer> cells){
            super(context, R.layout.custom_calendar_list_item, cells);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.custom_calendar_list_item, parent, false);
            }

            TextView text = (TextView)convertView.findViewById(R.id.custom_calendar_item);
            int date = getItem(position);
            text.setText(String.valueOf(date % 100));
            /*int textAppearance;
            if(currentMonth == (date % 10000) / 100){
                textAppearance = R.style.text_appearance_medium_dark;
            }else{
                textAppearance = R.style.text_appearance_medium_dark_soft;
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                text.setTextAppearance(textAppearance);
            }else{
                text.setTextAppearance(getContext(), textAppearance);
            }*/

            int color;
            if(currentMonth == (date % 10000) / 100) {
                if (markedDates.contains(date)) {
                    color = R.color.drawer_highlight;
                } else {
                    color = R.color.dark_text;
                }
            }else{
                color = R.color.list_view_divider;
            }
            text.setTextColor(ContextCompat.getColor(getContext(), color));

            return convertView;
        }

        public void update(ArrayList<Integer> cells){
            clear();
            addAll(cells);
            notifyDataSetInvalidated();
        }
    }

    public interface OnDateSelectedListener{
        void onSelectDate(int date);
    }

}
