package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.component.DaggerPointsOfReunionComponent;
import com.example.dp2.afiperu.domain.NewPointOfReunion;
import com.example.dp2.afiperu.module.PointsOfReunionModule;
import com.example.dp2.afiperu.others.MarkerInfo;
import com.example.dp2.afiperu.presenter.PointsOfReunionPresenter;
import com.example.dp2.afiperu.syncmodel.SyncPointOfReunion;
import com.example.dp2.afiperu.ui.activity.DetailActivity;
import com.example.dp2.afiperu.ui.viewmodel.PointsOfReunionView;
import com.example.dp2.afiperu.util.NetworkManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Fernando on 11/10/2015.
 */
public class MapEditFragment extends MapFragment implements PointsOfReunionView, GoogleMap.OnMapLongClickListener {

    @Inject
    PointsOfReunionPresenter presenter;

    private boolean saved = true;

    @Override
    public void saveSuccessful(){
        Toast.makeText(getContext(), getResources().getString(R.string.save_success), Toast.LENGTH_SHORT).show();
        ((DetailActivity)getActivity()).goBack();
    }

    @Override
    public void saveFailed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.save_failed).setNeutralButton(android.R.string.ok, null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void save(){
        ArrayList<SyncPointOfReunion> previousPoints = new ArrayList<>();
        ArrayList<NewPointOfReunion> newPoints = new ArrayList<>();
        for(MarkerInfo marker : markersInfo){
            if(marker.isCreated()){
                NewPointOfReunion newPoint = new NewPointOfReunion();
                newPoint.setAddress(marker.address);
                newPoint.setLatitude(marker.latitude);
                newPoint.setLongitude(marker.longitude);
                newPoints.add(newPoint);
            }else if(marker.isEnabled() || marker.isDisabled()){
                previousPoints.add(marker.toPointOfReunion());
            }
        }
        presenter.editMeetingPoints(getContext(), sessionId, SyncPointOfReunion.toPointOfReunion(previousPoints), newPoints);
    }

    public void trySave(){
        if(!saved){
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
        if(!saved){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        save();
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
        googleMap.setOnMapLongClickListener(this);
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        super.prepareView(rootView, args, savedInstanceState);
        ImageView deleteIcon = (ImageView)rootView.findViewById(R.id.map_bar_delete_icon);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.isNetworkConnected(getContext())) {
                    if (lastMarkerObject != null) {
                        MarkerInfo marker = getLastMarker();
                        if (marker.isCreated()) {
                            markersInfo.remove(marker);
                            GoogleMap map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
                            refillMap(map);
                        } else {
                            marker.toggleEnabled();
                            lastMarkerObject.setIcon(getLastMarker().getColoredIcon());
                        }
                        saved = false;
                    }
                }else{
                    showNoInternetMessage();
                }
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        super.onMarkerClick(marker);

        ImageView deleteIcon = (ImageView)getView().findViewById(R.id.map_bar_delete_icon);
        MarkerInfo markerInfo = getLastMarker();
        deleteIcon.setVisibility(View.VISIBLE);
        if(markerInfo.isCreated() || markerInfo.isEnabled()){
            deleteIcon.setImageResource(R.drawable.ic_delete_100);
        }else if(markerInfo.isDisabled()){
            deleteIcon.setImageResource(R.drawable.ic_pin_100);
        }else{
            deleteIcon.setVisibility(View.GONE);
        }

        return true;
    }

    @Override
    public void onMapLongClick(LatLng point){
        if(NetworkManager.isNetworkConnected(getContext())) {
            GoogleMap map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
            MarkerInfo marker = new MarkerInfo(point);
            Marker m = map.addMarker(new MarkerOptions().position(point)
                    .title(marker.getTitle(getResources()))
                    .icon(marker.getColoredIcon()));
            marker.markerId = m.getId();
            markersInfo.add(marker);
            onMarkerClick(m);
            saved = false;
        }else{
            showNoInternetMessage();
        }
    }

    public void showNoInternetMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.no_internet).setNeutralButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
