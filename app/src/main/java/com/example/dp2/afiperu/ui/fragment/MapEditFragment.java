package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.component.DaggerPointsOfReunionComponent;
import com.example.dp2.afiperu.component.DaggerSessionComponent;
import com.example.dp2.afiperu.module.PointsOfReunionModule;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.presenter.SessionPresenter;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.adapter.SessionAdapter;
import com.example.dp2.afiperu.ui.viewmodel.PointsOfReunionView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

/**
 * Created by Fernando on 11/10/2015.
 */
public class MapEditFragment extends MapFragment implements PointsOfReunionView,
        GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener {

    @Inject
    PointsOfReunionPresenter presenter;

    public boolean needsSave(){
        for(MarkerInfo marker : markersInfo){
            if(marker.isEdited() || marker.isDeleted()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveSuccessful(){
        for(int i=0; i<markersInfo.size(); i++){
        MarkerInfo markerInfo = markersInfo.get(i);
        if(markerInfo.isEdited()){
            markerInfo.setEdited(false);
        }else if(markerInfo.isDeleted()){
            markersInfo.remove(i);
            i--;
        }
    }
        GoogleMap map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
        refillMap(map);
    }

    @Override
    public void saveFailed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.save_failed).setNeutralButton(android.R.string.ok, null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void save(){
        for(MarkerInfo marker : markersInfo){
            if(marker.isCreated()) {
                //CREATE
            }else if(marker.isEdited()){
                //UPDATE where
            }else if(marker.isDeleted()){
                //DELETE where
            }
        }
        /*presenter.editMeetingPoints(sessionId, uneditedPoints,
                newPoints,
                deletedPoints);*/
    }

    public void trySave(){
        if(needsSave()){
            save();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.save_not_needed).setNeutralButton(android.R.string.ok, null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean tryBack(){
        if(needsSave()){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        ((DetailActivity)getContext()).goBack();
                    }else if(which == DialogInterface.BUTTON_NEGATIVE){
                        ((DetailActivity)getContext()).goBack();
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.save_needed)
                    .setNeutralButton(R.string.save_needed_cancel, dialogClickListener)
                    .setPositiveButton(R.string.save_needed_save, dialogClickListener)
                    .setNegativeButton(R.string.save_needed_exit, dialogClickListener);
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerPointsOfReunionComponent.builder()
                .afiAppComponent(appComponent)
                .pointsOfReunionModule(new PointsOfReunionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapLongClickListener(this);
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        super.prepareView(rootView, args, savedInstanceState);
        ImageView deleteIcon = (ImageView)rootView.findViewById(R.id.map_bar_delete_icon);
        deleteIcon.setVisibility(View.VISIBLE);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastMarkerObject != null) {
                    lastMarkerObject.setIcon(MarkerInfo.getColoredIcon(MarkerInfo.MARKER_KIND_SESSION_REUNION_DELETED));
                    getLastMarker().setDeleted();
                }
            }
        });
    }

    @Override
    public void onMapLongClick(LatLng point){
        GoogleMap map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
        MarkerInfo marker = new MarkerInfo(point);
        Marker m = map.addMarker(new MarkerOptions().position(point)
                .title(marker.getTitle(getResources()))
                .icon(marker.getColoredIcon())
                .draggable(marker.isReunion() && markersAreDraggable()));
        marker.markerId = m.getId();
        markersInfo.add(marker);
    }

    @Override
    public boolean markersAreDraggable(){
        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if(!onMarkerClick(marker)) { //Incuye setLastMarker(marker)
            marker.showInfoWindow();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        updateMarker(marker);
        marker.setIcon(MarkerInfo.getColoredIcon(MarkerInfo.MARKER_KIND_SESSION_REUNION_EDITED));
        getLastMarker().setEdited(true);
    }

    public void updateMarker(Marker marker){
        for(int i=0; i<markersInfo.size(); i++){
            MarkerInfo markerInfo = markersInfo.get(i);
            if(markerInfo.markerId.equals(marker.getId())){
                LatLng position = marker.getPosition();
                markerInfo.latitude = (position.latitude);
                markerInfo.longitude = (position.longitude);
                markerInfo.setEdited(true);
                break;
            }
        }

        lastMarker = -1;
        lastMarkerObject = null;
        if(!onMarkerClick(marker)) { //Incuye setLastMarker(marker)
            marker.showInfoWindow();
        }
    }
}
