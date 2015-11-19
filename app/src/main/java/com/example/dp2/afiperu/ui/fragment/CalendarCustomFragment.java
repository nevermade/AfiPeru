package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.others.CustomCalendar;
import com.example.dp2.afiperu.syncmodel.SyncSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fernando on 19/10/2015.
 */
public class CalendarCustomFragment extends BaseFragment {

    public static final String EVENTS_ARG = "events_arg";

    private ArrayList<SyncSession> events;

    @Override
    public int getLayout() {
        return R.layout.calendar_custom;
    }

    @Override
    public void prepareView(final View rootView, Bundle args, Bundle savedInstanceState) {
        events = (ArrayList<SyncSession>)args.getSerializable(EVENTS_ARG);

        final Map<Integer,SyncSession> dic = new HashMap<>();
        List<Integer> markedDates = new ArrayList<>();
        for (SyncSession session : events) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(session.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            int date = year*10000+(month+1)*100+day;
            dic.put(date,session);
            markedDates.add(date);
        }
        CustomCalendar calendar = (CustomCalendar)rootView.findViewById(R.id.calendar_custom);
        calendar.updateMarkedDates(markedDates);
        GregorianCalendar calendar2 = new GregorianCalendar();

        int year = calendar2.get(Calendar.YEAR);
        int month = calendar2.get(Calendar.MONTH);
        int day = calendar2.get(Calendar.DAY_OF_MONTH);

        SyncSession event = dic.get(year*10000+(month+1)*100+day);
        LinearLayout eventInfo = (LinearLayout)rootView.findViewById(R.id.calendar_event_info);
        if(event != null) {
            eventInfo.setVisibility(View.VISIBLE);
            TextView eventTitle = (TextView)rootView.findViewById(R.id.calendar_event_title);
            TextView eventDate = (TextView)rootView.findViewById(R.id.calendar_event_date);
            TextView eventTime = (TextView)rootView.findViewById(R.id.calendar_event_time);
            TextView eventAddress = (TextView)rootView.findViewById(R.id.calendar_event_address);
            eventTitle.setText(event.getName());
            eventDate.setText(DateFormat.getDateInstance().format(new Date(event.getDate())));
            eventTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(event.getDate())));
            eventAddress.setText(event.getLocation().getAddress());
        }else{
            eventInfo.setVisibility(View.INVISIBLE);
        }

        calendar.setOnDateSelectedListener(new CustomCalendar.OnDateSelectedListener() {
            @Override
            public void onSelectDate(int date) {
                SyncSession event = dic.get(date);
                LinearLayout eventInfo = (LinearLayout)rootView.findViewById(R.id.calendar_event_info);
                if(event != null) {
                    eventInfo.setVisibility(View.VISIBLE);
                    TextView eventTitle = (TextView)rootView.findViewById(R.id.calendar_event_title);
                    TextView eventDate = (TextView)rootView.findViewById(R.id.calendar_event_date);
                    TextView eventTime = (TextView)rootView.findViewById(R.id.calendar_event_time);
                    TextView eventAddress = (TextView)rootView.findViewById(R.id.calendar_event_address);
                    eventTitle.setText(event.getName());
                    eventDate.setText(DateFormat.getDateInstance().format(new Date(event.getDate())));
                    eventTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(event.getDate())));
                    eventAddress.setText(event.getLocation().getAddress());
                }else{
                    eventInfo.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
