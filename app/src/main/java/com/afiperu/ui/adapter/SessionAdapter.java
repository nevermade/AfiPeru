package com.afiperu.ui.adapter;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.afiperu.R;
import com.afiperu.common.BaseArrayAdapter;
import com.afiperu.common.BaseFragment;
import com.afiperu.others.MarkerInfo;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.syncmodel.SyncLocation;
import com.afiperu.syncmodel.SyncPointOfReunion;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.ui.activity.DetailActivity;
import com.afiperu.ui.fragment.AttendanceFragment;
import com.afiperu.ui.fragment.CommentKidFragment;
import com.afiperu.ui.fragment.DocumentsFragment;
import com.afiperu.ui.fragment.MapEditFragment;
import com.afiperu.ui.fragment.MapFragment;
import com.afiperu.ui.fragment.ViewCommentsFragment;
import com.afiperu.util.AppEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionAdapter extends BaseArrayAdapter<SyncSession> {

    public SessionAdapter(Context context, BaseFragment fragment, List<SyncSession> objects) {
        super(context, fragment, R.layout.session_list_item, objects);
    }

    @Override
    public void prepareItemView(View convertView, final SyncSession item, int position) {
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
                    if(!AppEnum.EnumAction.LIST_COMMENTS.hasPermission())
                        popup.getMenu().findItem(R.id.sessions_menu_show_comments).setVisible(false);
                    if(!AppEnum.EnumAction.ATTENDANCE_AND_QUALIFICATION.hasPermission())
                        popup.getMenu().findItem(R.id.sessions_menu_attendance).setVisible(false);

                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch(menuItem.getItemId()){
                                case R.id.sessions_menu_show_comments:
                                    if(System.currentTimeMillis() <= item.getDate()){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setMessage(R.string.sessions_date_error).setNeutralButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    } else {
                                        ArrayList<SyncAttendanceChild> children = new ArrayList<>();
                                        children.addAll(item.queryAttendanceChildren());
                                        Collections.sort(children);
                                        Bundle args = new Bundle();
                                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_COMENTARIOS);
                                        args.putSerializable(ViewCommentsFragment.KIDS_ARG, children);
                                        ViewCommentsFragment kidsFragment2 = new ViewCommentsFragment();
                                        kidsFragment2.setArguments(args);
                                        getFragment().addFragmentToStack(kidsFragment2, DetailActivity.FRAGMENT_COMENTARIOS);
                                    }
                                    break;
                                case R.id.sessions_menu_comment:
                                    if(System.currentTimeMillis() <= item.getDate()){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setMessage(R.string.sessions_date_error).setNeutralButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    } else {
                                        ArrayList<SyncAttendanceChild> children = new ArrayList<>();
                                        children.addAll(item.queryAttendanceChildren());
                                        Collections.sort(children);
                                        Bundle args = new Bundle();
                                        args.putSerializable(CommentKidFragment.KIDS_ARG, children);
                                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_COMENTARIOS);
                                        CommentKidFragment kidsFragment = new CommentKidFragment();
                                        kidsFragment.setArguments(args);
                                        getFragment().addFragmentToStack(kidsFragment, DetailActivity.FRAGMENT_COMENTARIOS);
                                    }
                                    break;
                                case R.id.sessions_menu_attendance:
                                    if(System.currentTimeMillis() <= item.getDate()){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setMessage(R.string.sessions_date_error).setNeutralButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    } else {
                                        ArrayList<SyncAttendanceVolunteer> volunteers = new ArrayList<>();
                                        volunteers.addAll(item.queryAttendanceVolunteer());
                                        Collections.sort(volunteers);
                                        Bundle args = new Bundle();
                                        args.putSerializable(AttendanceFragment.ATTENDANCE_ARG, volunteers);
                                        args.putInt(AttendanceFragment.SESSION_ID_ARG, item.getSessionId());
                                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, DetailActivity.FRAGMENT_ASISTENCIA);
                                        AttendanceFragment attendanceFragment = new AttendanceFragment();
                                        attendanceFragment.setArguments(args);
                                        getFragment().addFragmentToStack(attendanceFragment, DetailActivity.FRAGMENT_ASISTENCIA);
                                    }
                                    break;
                                case R.id.sessions_menu_map:
                                    MapFragment mapFragment;
                                    Bundle args = new Bundle();
                                    SyncLocation location = item.getLocation();
                                    List<SyncPointOfReunion> pointsOfReunion = item.queryPointsOfReunion();
                                    ArrayList<MarkerInfo> markers = new ArrayList<>();
                                    boolean hasEditMapPermission = AppEnum.EnumAction.CREATE_OR_EDIT_POINTS_OF_REUNION.hasPermission();
                                    markers.add(new MarkerInfo(location));
                                    for(SyncPointOfReunion pointOfReunion : pointsOfReunion){
                                        if(pointOfReunion.getSelected() == 1 || hasEditMapPermission) {
                                            markers.add(new MarkerInfo(pointOfReunion, pointOfReunion.getSelected() == 1));
                                        }
                                    }
                                    args.putSerializable(MapFragment.MARKERS_ARG, markers);
                                    args.putInt(MapFragment.SESSION_ID_ARG, item.getSessionId());

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
                                    ArrayList<SyncDocument> documents = new ArrayList<>();
                                    documents.addAll(item.queryDocuments());
                                    args = new Bundle();
                                    args.putSerializable(DocumentsFragment.DOCUMENTS_ARG, documents);
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
