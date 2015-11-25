package com.afiperu.util;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Fernando on 11/10/2015.
 */
public class FetchAddressIntentService extends IntentService {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILURE = 1;
    public static final String LOCATION_ARG = "location_arg";
    public static final String RECEIVER_ARG = "receiver_arg";
    public static final String ADDRESS_RESULT_ARG = "address_result_arg";

    protected ResultReceiver receiver;

    public FetchAddressIntentService(){
        super("Fetch address");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Location location = intent.getParcelableExtra(LOCATION_ARG);
        receiver = intent.getParcelableExtra(RECEIVER_ARG);
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                ArrayList<String> addressLines = new ArrayList<>(address.getMaxAddressLineIndex());
                for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    addressLines.add(address.getAddressLine(i));
                }
                deliverResultToReceiver(RESULT_SUCCESS, TextUtils.join("\n", addressLines));
                return;
            }else{
                deliverResultToReceiver(RESULT_FAILURE, "No addresses");
            }
        }catch(IOException | IllegalArgumentException e){

        }
        deliverResultToReceiver(RESULT_FAILURE, "Error");
    }

    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(ADDRESS_RESULT_ARG, message);
        receiver.send(resultCode, bundle);
    }
}