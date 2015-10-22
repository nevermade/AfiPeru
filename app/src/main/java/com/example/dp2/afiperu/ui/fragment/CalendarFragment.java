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
import com.example.dp2.afiperu.domain.AFIEvent;
import com.example.dp2.afiperu.ui.activity.DetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

/**
 * Created by Fernando on 19/10/2015.
 */
public class CalendarFragment extends BaseFragment {

    public static final String EVENTS_ARG = "events_arg";

    private Hashtable<Long, AFIEvent> events;

    @Override
    public int getLayout() {
        return R.layout.calendar;
    }

    @Override
    public void prepareView(final View rootView, Bundle args, Bundle savedInstanceState) {
        events = (Hashtable<Long, AFIEvent>)args.getSerializable(EVENTS_ARG);

        CalendarView calendar = (CalendarView)rootView.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar date = new GregorianCalendar(year, month, dayOfMonth);
                long dateLong = date.getTime().getTime();
                AFIEvent event = events.get(dateLong);

                LinearLayout eventInfo = (LinearLayout)rootView.findViewById(R.id.calendar_event_info);
                TextView eventTitle = (TextView)rootView.findViewById(R.id.calendar_event_title);
                TextView eventDate = (TextView)rootView.findViewById(R.id.calendar_event_date);
                TextView eventTime = (TextView)rootView.findViewById(R.id.calendar_event_time);
                TextView eventAddress = (TextView)rootView.findViewById(R.id.calendar_event_address);
                if(event != null){
                    eventInfo.setVisibility(View.VISIBLE);
                    eventTitle.setText(event.getTitle());
                    eventDate.setText(DateFormat.getDateInstance().format(new Date(event.getDateTime())));
                    eventTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(event.getDateTime())));
                    eventAddress.setText(event.getAddressStr());
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
