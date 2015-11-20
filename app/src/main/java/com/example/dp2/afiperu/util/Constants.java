package com.example.dp2.afiperu.util;

import android.app.ProgressDialog;

import com.example.dp2.afiperu.domain.User;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;

/**
 * Created by DABARCA on 21/10/2015.
 */
public class Constants {

    public static final String DRUPAL_ARTICLES_URL="http://162.243.118.33/afiperudrupal/node/";
    public static final String DOCS_URL ="http://162.243.118.33/afiperularavel/public/";
    public static final String BASE_URL="http://162.243.118.33/";

    public static String TOKEN="";
    public static ProgressDialog PROGRESS;
    public static User loggedUser;

    public static final String PAYPAL_CLIENT_ID = "AaojGtvv8YNz-PGOlN3B9qeKdu8UaWRDgGg5RBQgByyAaru1-kVTY4B5zQB1ZnSFqcKMBmuXsSdaHmow";
    public static final String PAYPAL_CLIENT_SECRET = "";

    public static final String PAYPAL_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    public static final String PAYMENT_INTENT = PayPalPayment.PAYMENT_INTENT_SALE;
    public static Double FROM_USD_TO_PEN = 0.00;
    public static final int REQUEST_CODE_PAYMENT = 3;
    public static int PAYMENT_FEE_ID;

}
