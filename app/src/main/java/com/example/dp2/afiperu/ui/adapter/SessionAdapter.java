package com.example.dp2.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseArrayAdapter;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.domain.Action;
import com.example.dp2.afiperu.domain.Attendance;
import com.example.dp2.afiperu.domain.Kid;
import com.example.dp2.afiperu.domain.Location;
import com.example.dp2.afiperu.domain.PointOfReunion;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.rest.model.AttendanceVolunteer;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.fragment.AttendanceFragment;
import com.example.dp2.afiperu.ui.fragment.DocumentsFragment;
import com.example.dp2.afiperu.ui.fragment.Kids2Fragment;
import com.example.dp2.afiperu.ui.fragment.KidsFragment;
import com.example.dp2.afiperu.ui.fragment.MapEditFragment;
import com.example.dp2.afiperu.ui.fragment.MapFragment;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionAdapter extends BaseArrayAdapter<Session> {

    public SessionAdapter(Context context, BaseFragment fragment, List<Session> objects) {
        super(context, fragment, R.layout.session_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, final Session item, int position) {
        TextView name = (TextView) convertView.findViewById(R.id.sessions_item_name);
        TextView date = (TextView) convertView.findViewById(R.id.sessions_item_date);
        ImageView menu = (ImageView) convertView.findViewById(R.id.sessions_item_menu);
        int color;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            color = convertView.getResources().getColor(R.color.toolbar_background, null);
        }else{
            color = convertView.getResources().getColor(R.color.toolbar_background);
        }
        menu.setColorFilter(color);

        name.setText(item.getName());
        CharSequence formattedDate = DateUtils.getRelativeDateTimeString(getContext(), item.getDate(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH);
        date.setText(formattedDate);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.sessions_item_menu) {
                    PopupMenu popup = new PopupMenu(getContext(), v);
                    popup.getMenuInflater().inflate(R.menu.sessions_menu_afi, popup.getMenu());
                    if(!AppEnum.EnumAction.ATTENDANCE_AND_QUALIFICATION.hasPermission())
                        popup.getMenu().findItem(R.id.sessions_menu_attendance).setVisible(false);

                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch(menuItem.getItemId()){
                                case R.id.sessions_menu_show_comments:
                                    Bundle args = new Bundle();
                                    ArrayList<Kid> kids2 = new ArrayList<>();
                                    kids2.add(new Kid("Eduardo Arenas", 12, true));
                                    kids2.add(new Kid("Julio Castillo", 12, false));
                                    kids2.add(new Kid("Juan Reyes", 12, true));
                                    kids2.add(new Kid("Kevin Brown", 12, true));
                                    kids2.add(new Kid("Robert Aduviri", 12, false));
                                    Collections.sort(kids2);
                                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_COMENTARIOS);
                                    args.putSerializable(Kids2Fragment.KIDS_ARG, kids2);
                                    Kids2Fragment kidsFragment2 = new Kids2Fragment();
                                    kidsFragment2.setArguments(args);
                                    getFragment().addFragmentToStack(kidsFragment2, DetailActivity.FRAGMENT_COMENTARIOS);
                                    break;
                                case R.id.sessions_menu_comment:
                                    args = new Bundle();
                                    ArrayList<Kid> kids = new ArrayList<>();
                                    kids.add(new Kid("Eduardo Arenas", 12, true));
                                    kids.add(new Kid("Julio Castillo", 12, false));
                                    kids.add(new Kid("Juan Reyes", 12, true));
                                    kids.add(new Kid("Kevin Brown", 12, true));
                                    kids.add(new Kid("Robert Aduviri", 12, false));
                                    Collections.sort(kids);
                                    args.putSerializable(KidsFragment.KIDS_ARG, kids);
                                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_COMENTARIOS);
                                    KidsFragment kidsFragment = new KidsFragment();
                                    kidsFragment.setArguments(args);
                                    getFragment().addFragmentToStack(kidsFragment, DetailActivity.FRAGMENT_COMENTARIOS);
                                    break;
                                case R.id.sessions_menu_attendance:
                                    args = new Bundle();
                                    List<AttendanceVolunteer> restvolunteers = item.getAttendanceVolunteers();
                                    ArrayList<AttendanceVolunteer> realvol = new ArrayList<AttendanceVolunteer>();
                                    for (AttendanceVolunteer av : restvolunteers) realvol.add(av);
                                    ArrayList<Attendance> volunteers = new ArrayList<>();
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Alonso Alvarez", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Fernando Banda", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Luis Barcena", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Daekef Abarca", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Gloria Cisneros", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Diego Malpartida", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Gabriel Tovar", false));
                                    volunteers.add(new Attendance(
                                            2001,
                                            "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                                            "Luis Incio", false));
                                    Collections.sort(realvol);
                                    Collections.sort(volunteers);
                                    args.putSerializable(AttendanceFragment.ATTENDANCE_ARG, realvol);
                                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_ASISTENCIA);
                                    AttendanceFragment attendanceFragment = new AttendanceFragment();
                                    attendanceFragment.setArguments(args);
                                    getFragment().addFragmentToStack(attendanceFragment, DetailActivity.FRAGMENT_ASISTENCIA);
                                    break;
                                case R.id.sessions_menu_map:
                                    MapFragment mapFragment;
                                    args = new Bundle();
                                    Location location = item.getLocation();
                                    List<PointOfReunion> pointsOfReunion = item.getPointsOfReunion();
                                    ArrayList<MarkerInfo> markers = new ArrayList<>();
                                    boolean hasEditMapPermission = AppEnum.EnumAction.CREATE_OR_EDIT_POINTS_OF_REUNION.hasPermission();
                                    markers.add(new MarkerInfo(location));
                                    for(PointOfReunion pointOfReunion : pointsOfReunion){
                                        if(pointOfReunion.getSelected() == 1 || hasEditMapPermission) {
                                            markers.add(new MarkerInfo(pointOfReunion, pointOfReunion.getSelected() == 1));
                                        }
                                    }
                                    args.putSerializable(MapFragment.MARKERS_ARG, markers);
                                    args.putInt(MapFragment.SESSION_ID_ARG, item.getId());

                                    int fragmentId;
                                    if(hasEditMapPermission){ //Si es editable
                                        fragmentId = DetailActivity.FRAGMENT_MAPA_EDITABLE;
                                        mapFragment = new MapEditFragment();
                                    }else{
                                        fragmentId = DetailActivity.FRAGMENT_MAPA;
                                        mapFragment = new MapFragment();
                                    }
                                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, fragmentId);
                                    mapFragment.setArguments(args);
                                    getFragment().addFragmentToStack(mapFragment, fragmentId);
                                    break;
                                case R.id.sessions_menu_docs:
                                    DocumentsFragment docsFragment = new DocumentsFragment();
                                    args = new Bundle();
                                    args.putSerializable(DocumentsFragment.DOCUMENTS_ARG, item.getDocuments());
                                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_DOCUMENTOS);
                                    docsFragment.setArguments(args);
                                    getFragment().addFragmentToStack(docsFragment, DetailActivity.FRAGMENT_DOCUMENTOS);
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
