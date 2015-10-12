package com.example.dp2.afiperu.fragments;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.lists.MarkerInfo;
import com.example.dp2.afiperu.util.FetchAddressIntentService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.example.dp2.afiperu.R;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Fernando on 08/10/2015.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String MARKERS_ARG = "markers_arg";

    protected Geocoder geocoder;
    protected ArrayList<MarkerInfo> markersInfo;
    protected int lastMarker = -1;
    protected Marker lastMarkerObject = null;

    public boolean sameMarker(Marker marker){
        MarkerInfo markerInfo = getLastMarker();
        return markerInfo == null ? false : marker.getId().equals(markerInfo.getId());
    }

    public MarkerInfo getLastMarker(){
        return lastMarker == -1 ? null : markersInfo.get(lastMarker);
    }

    public void setLastMarker(Marker marker){
        for(int i=0; i<markersInfo.size(); i++){
            if(markersInfo.get(i).getId().equals(marker.getId())){
                lastMarker = i;
                lastMarkerObject = marker;
                return;
            }
        }

        lastMarker = -1;
        lastMarkerObject = null;
    }

    @Override
    public int getLayout() {
        return R.layout.map;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        SupportMapFragment map = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map_fragment);
        map.getMapAsync(this);

        if(geocoder == null){
            geocoder = new Geocoder(getContext(), Locale.getDefault());
        }
    }

    public boolean markersAreDraggable(){
        return false;
    }

    public void refillMap(GoogleMap googleMap){
        googleMap.clear();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(MarkerInfo marker : markersInfo){
            LatLng latLng = new LatLng(marker.getLatitude(), marker.getLongitude());
            builder.include(latLng);
            Marker m = googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(marker.getTitle(getResources()))
                    .icon(marker.getColoredIcon())
                    .draggable(marker.isReunion() && markersAreDraggable()));
            marker.setId(m.getId());
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 40));
        if(markersInfo.size() <= 1){
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(14.0f));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings ui = googleMap.getUiSettings();
        ui.setZoomControlsEnabled(true);
        ui.setMapToolbarEnabled(false);

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(this);

        markersInfo = (ArrayList<MarkerInfo>)getArguments().getSerializable(MARKERS_ARG);
        refillMap(googleMap);
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        boolean sameMarker = sameMarker(marker);
        if(!sameMarker) {
            setLastMarker(marker);

            TextView addressText = (TextView) MapFragment.this.getView().findViewById(R.id.map_address_text);
            addressText.setText("");
            RelativeLayout mapBar = (RelativeLayout) MapFragment.this.getView().findViewById(R.id.map_bar);
            mapBar.setVisibility(View.VISIBLE);

            LatLng position = marker.getPosition();
            Location location = new Location("");
            location.setLatitude(position.latitude);
            location.setLongitude(position.longitude);

            AddressResultReceiver receiver = new AddressResultReceiver(new Handler());
            Intent intent = new Intent(getContext(), FetchAddressIntentService.class);
            intent.putExtra(FetchAddressIntentService.LOCATION_ARG, location);
            intent.putExtra(FetchAddressIntentService.RECEIVER_ARG, receiver);
            getActivity().startService(intent);
        }

        return false;
    }

    @Override
    public void onSearch(String query){
        /*if(geocoder == null) return;
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
        }*/
    }

    public class AddressResultReceiver extends ResultReceiver{

        public AddressResultReceiver(Handler handler){
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData){
            TextView addressText = (TextView)MapFragment.this.getView().findViewById(R.id.map_address_text);
            String text;
            if(resultCode == FetchAddressIntentService.RESULT_SUCCESS){
                text = resultData.getString(FetchAddressIntentService.ADDRESS_RESULT_ARG);
            }else{
                text = "";
            }
            addressText.setText(text);
        }
    }


}
