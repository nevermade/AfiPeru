package com.example.dp2.afiperu.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.others.MarkerInfo;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Fernando on 08/10/2015.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String MARKERS_ARG = "markers_arg";
    public static final String SESSION_ID_ARG = "session_id_arg";

    protected int sessionId;
    protected Geocoder geocoder;
    protected ArrayList<MarkerInfo> markersInfo;
    protected int lastMarker = -1;
    protected Marker lastMarkerObject = null;

    public boolean sameMarker(Marker marker){
        MarkerInfo markerInfo = getLastMarker();
        return markerInfo == null ? false : marker.getId().equals(markerInfo.markerId);
    }

    public MarkerInfo getLastMarker(){
        return lastMarker == -1 ? null : markersInfo.get(lastMarker);
    }

    public void setLastMarker(Marker marker){
        for(int i=0; i<markersInfo.size(); i++){
            if(markersInfo.get(i).markerId.equals(marker.getId())){
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

        sessionId = args.getInt(SESSION_ID_ARG);

        if(geocoder == null){
            geocoder = new Geocoder(getContext(), Locale.getDefault());
        }
    }

    public void refillMap(GoogleMap googleMap){
        googleMap.clear();

        LatLngBounds.Builder consideredForCamera = new LatLngBounds.Builder();
        for(MarkerInfo marker : markersInfo){
            LatLng latLng = new LatLng(marker.latitude, marker.longitude);
            if(!marker.isDisabled()) {
                consideredForCamera.include(latLng);
            }
            Marker m = googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(marker.getTitle(getResources()))
                    .icon(marker.getColoredIcon()));
            marker.markerId = (m.getId());
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(consideredForCamera.build(), 40));
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

            AddressResultReceiver receiver = new AddressResultReceiver(getLastMarker(), new Handler());
            Intent intent = new Intent(getContext(), FetchAddressIntentService.class);
            intent.putExtra(FetchAddressIntentService.LOCATION_ARG, location);
            intent.putExtra(FetchAddressIntentService.RECEIVER_ARG, receiver);
            getActivity().startService(intent);

            marker.showInfoWindow();
        }

        return true;
    }

    @Override
    public void onSearch(String query){
        if(geocoder == null) return;
        try {
            final List<Address> addresses = geocoder.getFromLocationName(query + ", Peru", 4);
            if(addresses.isEmpty()){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.no_address).setNeutralButton(android.R.string.ok, null);
                AlertDialog alert = builder.create();
                alert.show();
            }else if(addresses.size() == 1){
                Address address = addresses.get(0);
                goToAddress(address);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                String[] options = new String[addresses.size()];
                for(int i=0; i<addresses.size(); i++){
                    Address address = addresses.get(i);
                    options[i] = address.getAddressLine(1);
                    for(int j=2; j<address.getMaxAddressLineIndex(); j++){
                        options[i] += "\n" + address.getAddressLine(j);
                    }
                }
                builder.setTitle(R.string.choose_address).setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToAddress(addresses.get(which));
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }catch(IOException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.connection_failed).setNeutralButton(android.R.string.ok, null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void goToAddress(Address address){
        GoogleMap map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
        if(map != null){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(address.getLatitude(), address.getLongitude()), 18f));
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {

    }

    public class AddressResultReceiver extends ResultReceiver{

        private MarkerInfo marker;

        public AddressResultReceiver(MarkerInfo marker, Handler handler){
            super(handler);
            this.marker = marker;
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
            marker.address = text;
        }
    }


}
