package com.example.dp2.afiperu.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.example.dp2.afiperu.lists.MarkerInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.example.dp2.afiperu.R;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Fernando on 08/10/2015.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    public static final String MARKERS_ARG = "markers_arg";

    Geocoder geocoder;

    @Override
    public int getLayout() {
        return R.layout.map;
    }

    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        SupportMapFragment map = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map_fragment);
        map.getMapAsync(this);

        if(geocoder == null){
            geocoder = new Geocoder(getContext(), Locale.getDefault());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings ui = googleMap.getUiSettings();
        ui.setZoomControlsEnabled(true);
        ui.setMapToolbarEnabled(false);

        ArrayList<MarkerInfo> markers = (ArrayList<MarkerInfo>)getArguments().getSerializable(MARKERS_ARG);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(MarkerInfo marker : markers){
            LatLng latLng = new LatLng(marker.getLatitude(), marker.getLongitude());
            builder.include(latLng);
            googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(marker.getTitle(getResources()))
                    .icon(marker.getColoredIcon()));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 2));
        if(markers.size() <= 1){
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(14.0f));
        }
    }

    @Override
    public void onSearch(String query){
        if(geocoder == null) return;
        try {
            List<Address> addresses = geocoder.getFromLocationName(query, 1);
            if(!addresses.isEmpty()){
                Address address = addresses.get(0);
                GoogleMap map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
                if(map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(address.getLatitude(), address.getLongitude())));
                }
            }
        }catch(IOException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.connection_failed).setNeutralButton(android.R.string.ok, null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
