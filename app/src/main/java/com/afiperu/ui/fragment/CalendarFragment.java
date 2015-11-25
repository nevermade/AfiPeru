package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;
import com.afiperu.syncmodel.SyncSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fernando on 19/10/2015.
 */
public class CalendarFragment extends BaseFragment {

    public static final String EVENTS_ARG = "events_arg";

    private ArrayList<SyncSession> events;

    @Override
    public int getLayout() {
        return R.layout.calendar;
    }

    @Override
    public void prepareView(final View rootView, Bundle args, Bundle savedInstanceState) {
        events = (ArrayList<SyncSession>)args.getSerializable(EVENTS_ARG);

        final Map<Integer,SyncSession> dic = new HashMap<>();
        for (SyncSession session : events) {

            GregorianCalendar calendar = new GregorianCalendar();

            calendar.setTimeInMillis(session.getDate());
            int year= calendar.get(calendar.YEAR);
            int month = calendar.get(calendar.MONTH)+1;
            int  day= calendar.get(calendar.DAY_OF_MONTH);
            dic.put(year*10000+month*100+day,session);
        }
        CalendarView calendar = (CalendarView)rootView.findViewById(R.id.calendar);
        GregorianCalendar calendar2 = new GregorianCalendar();

        calendar2.setTimeInMillis(calendar.getDate());

        int year= calendar2.get(calendar2.YEAR);
        int month = calendar2.get(calendar2.MONTH)+1;
        int  day= calendar2.get(calendar2.DAY_OF_MONTH);

        SyncSession event = dic.get(year*10000+(month+1)*100+day);
        LinearLayout eventInfo = (LinearLayout)rootView.findViewById(R.id.calendar_event_info);
        TextView eventTitle = (TextView)rootView.findViewById(R.id.calendar_event_title);
        TextView eventDate = (TextView)rootView.findViewById(R.id.calendar_event_date);
        TextView eventTime = (TextView)rootView.findViewById(R.id.calendar_event_time);
        TextView eventAddress = (TextView)rootView.findViewById(R.id.calendar_event_address);

        if(event != null){
            eventInfo.setVisibility(View.VISIBLE);
            eventTitle.setText(event.getName());
            eventDate.setText(DateFormat.getDateInstance().format(new Date(event.getDate())));
            eventTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(event.getDate())));
            eventAddress.setText(event.getLocation().getAddress());
        }else{
            eventInfo.setVisibility(View.INVISIBLE);
        }

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar date = new GregorianCalendar(year, month, dayOfMonth);

                //long dateLong = date.getTime().getTime();
                SyncSession event = dic.get(year*10000+(month+1)*100+dayOfMonth);

                LinearLayout eventInfo = (LinearLayout)rootView.findViewById(R.id.calendar_event_info);
                TextView eventTitle = (TextView)rootView.findViewById(R.id.calendar_event_title);
                TextView eventDate = (TextView)rootView.findViewById(R.id.calendar_event_date);
                TextView eventTime = (TextView)rootView.findViewById(R.id.calendar_event_time);
                TextView eventAddress = (TextView)rootView.findViewById(R.id.calendar_event_address);
                if(event != null){
                    eventInfo.setVisibility(View.VISIBLE);
                    eventTitle.setText(event.getName());
                    eventDate.setText(DateFormat.getDateInstance().format(new Date(event.getDate())));
                    eventTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(event.getDate())));
                    eventAddress.setText(event.getName());
                }else{
                    eventInfo.setVisibility(View.VISIBLE);
                    eventTitle.setText(getString(R.string.no_events));

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
