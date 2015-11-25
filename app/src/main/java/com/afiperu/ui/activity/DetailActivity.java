package com.afiperu.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseActivity;
import com.afiperu.common.BaseFragment;
import com.afiperu.component.DaggerMainActivityComponent;
import com.afiperu.domain.Drawer;
import com.afiperu.domain.Profile;
import com.afiperu.domain.User;
import com.afiperu.module.MainActivityModule;
import com.afiperu.presenter.MainActivityPresenter;
import com.afiperu.syncmodel.SyncAttendanceChild;
import com.afiperu.syncmodel.SyncAttendanceVolunteer;
import com.afiperu.syncmodel.SyncComment;
import com.afiperu.syncmodel.SyncDocument;
import com.afiperu.syncmodel.SyncDocumentUser;
import com.afiperu.syncmodel.SyncKid;
import com.afiperu.syncmodel.SyncLocation;
import com.afiperu.syncmodel.SyncNews;
import com.afiperu.syncmodel.SyncPayment;
import com.afiperu.syncmodel.SyncPointOfReunion;
import com.afiperu.syncmodel.SyncSchoolAddress;
import com.afiperu.syncmodel.SyncSession;
import com.afiperu.syncmodel.SyncUser;
import com.afiperu.ui.adapter.DrawerAdapter;
import com.afiperu.ui.dialogs.CommentSearchDialog;
import com.afiperu.ui.dialogs.KidSearchDialog;
import com.afiperu.ui.dialogs.UserSearchDialog;
import com.afiperu.ui.fragment.AttendanceFragment;
import com.afiperu.ui.fragment.DocumentsFragment;
import com.afiperu.ui.fragment.DonationFragment;
import com.afiperu.ui.fragment.LoginFragment;
import com.afiperu.ui.fragment.MapEditFragment;
import com.afiperu.ui.fragment.NewsArticleFragment;
import com.afiperu.ui.fragment.NewsTabFragment;
import com.afiperu.ui.fragment.PaymentListFragment;
import com.afiperu.ui.fragment.PeopleTabFragment;
import com.afiperu.ui.fragment.PeriodReportFragment;
import com.afiperu.ui.fragment.SessionFragment;
import com.afiperu.ui.fragment.SettingsFragment;
import com.afiperu.ui.fragment.UploadPhotosFragment;
import com.afiperu.ui.viewmodel.MainActivityView;
import com.afiperu.util.AppEnum;
import com.afiperu.util.Constants;
import com.afiperu.util.NetworkReceiver;
import com.afiperu.util.RegistrationIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Fernando on 16/09/2015.
 */
public class DetailActivity extends BaseActivity implements MainActivityView {

    public static final int FRAGMENT_NOTICIAS = 0;
    public static final int FRAGMENT_PERSONAS = 2;
    public static final int FRAGMENT_SESIONES = 4;
    public static final int FRAGMENT_DOCUMENTOS = 5;
    public static final int FRAGMENT_SUBIR_FOTOS = 6;
    public static final int FRAGMENT_PAGOS = 7;
    public static final int FRAGMENT_DONACIONES = 8;
    public static final int FRAGMENT_REPORTES_PADRINOS = 20;
    public static final int FRAGMENT_CONFIGURACIÓN = 9;

    public static final int FRAGMENT_DETALLE_NOTICIAS = 10;
    public static final int FRAGMENT_CALENDARIO = 12;
    public static final int FRAGMENT_USUARIOS_REVISADO = 13;
    public static final int FRAGMENT_ASISTENCIA = 14;
    public static final int FRAGMENT_COMENTARIOS = 15;
    public static final int FRAGMENT_LISTA_COMENTARIOS = 16;
    public static final int FRAGMENT_MAPA = 17;
    public static final int FRAGMENT_MAPA_EDITABLE = 18;
    public static final int FRAGMENT_REGISTRAR_PAGO = 19;
    public static final int FRAGMENT_LOGIN = 99;

    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    int selectedLayout;
    int toolbarMenu;
    private NetworkReceiver receiver = new NetworkReceiver();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    int previousBackStackCount;
    Drawer applyOptionItem;
    PaymentListFragment paymentListFragment;
    private static final String TAG = "paymentExample";
    @Inject
    MainActivityPresenter presenter;

    private String lastCameraPath;

    public MainActivityPresenter getPresenter(){
        return presenter;
    }

    private static final int REQUEST_IMAGE_CAPTURE =1;
    private static final int REQUEST_IMAGE_GALLERY =2;
    /* Cosas a agregar con cada layout nuevo */

    public String getTitle(int fragmentId){
        int id = 0;
        switch(fragmentId){
            case FRAGMENT_NOTICIAS: id = R.string.menu_noticias; break;
            case FRAGMENT_PERSONAS: id = R.string.menu_personas; break;
            case FRAGMENT_SESIONES: id = R.string.menu_sesiones; break;
            case FRAGMENT_DOCUMENTOS: id = R.string.menu_documentos; break;
            case FRAGMENT_SUBIR_FOTOS: id = R.string.menu_subir_fotos; break;
            case FRAGMENT_PAGOS: id = R.string.menu_pagos; break;
            case FRAGMENT_DONACIONES: id = R.string.menu_donaciones; break;
            case FRAGMENT_REPORTES_PADRINOS: id = R.string.menu_reportes; break;
            case FRAGMENT_CONFIGURACIÓN: id = R.string.menu_configuracion; break;

            case FRAGMENT_LOGIN: id = R.string.app_name; break;
            case FRAGMENT_DETALLE_NOTICIAS: id = R.string.menu_noticias; break;
            case FRAGMENT_ASISTENCIA: id = R.string.title_asistencia; break;
            case FRAGMENT_COMENTARIOS:
            case FRAGMENT_LISTA_COMENTARIOS:
                id = R.string.title_comentarios; break;
            case FRAGMENT_USUARIOS_REVISADO: id = R.string.menu_documentos; break;
            case FRAGMENT_MAPA:
            case FRAGMENT_MAPA_EDITABLE: id = R.string.app_name; break;
            case FRAGMENT_REGISTRAR_PAGO: id = R.string.menu_pagos; break;
            case FRAGMENT_CALENDARIO: id = R.string.title_calendar; break;
        }
        if(id != 0){
            return getResources().getString(id);
        }else{
            return "";
        }
    }

    public int getMenu(int fragmentId){
        switch(fragmentId){
            case FRAGMENT_NOTICIAS: return R.menu.news_menu_toolbar;
            case FRAGMENT_SESIONES: return R.menu.sessions_menu_toolbar;
            case FRAGMENT_SUBIR_FOTOS: return R.menu.upload_photos_toolbar;
            case FRAGMENT_DETALLE_NOTICIAS: return R.menu.news_article_menu_toolbar;
            case FRAGMENT_PERSONAS: return R.menu.people_menu_toolbar;
            case FRAGMENT_LISTA_COMENTARIOS: return R.menu.comments_menu_toolbar;
            case FRAGMENT_MAPA: return R.menu.map_menu_toolbar;
            case FRAGMENT_MAPA_EDITABLE: return R.menu.map_edit_menu_toolbar;
            case FRAGMENT_ASISTENCIA: return R.menu.attendance_menu_toolbar;
            default: return 0;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        BaseFragment topFragment = getTopFragment();
        int fragmentId = topFragment.getFragmentId();
        switch(fragmentId){
            case FRAGMENT_DETALLE_NOTICIAS:
                switch(item.getItemId()){
                    case R.id.news_article_menu_share:
                        ((NewsArticleFragment)getTopFragment()).shareOnFacebook();
                        break;
                }
                break;
            case FRAGMENT_PERSONAS:
                switch(item.getItemId()){
                    case R.id.people_menu_map:
                        ((PeopleTabFragment)topFragment).getUsersFragment().getLocations();
                        break;
                }
                break;
            case FRAGMENT_SESIONES:
                switch(item.getItemId()){
                    case R.id.sessions_menu_calendar:
                        ((SessionFragment)topFragment).sessionsCalendar();
                        break;
                }
                break;
            case FRAGMENT_SUBIR_FOTOS:
                switch(item.getItemId()){
                    case R.id.upload_photos_menu_gallery:
                        Intent getPictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        if (getPictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(getPictureIntent, REQUEST_IMAGE_GALLERY);
                        }
                        break;
                    case R.id.upload_photos_menu_camera:
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            File tempFile = createImageFile();
                            lastCameraPath = tempFile.getAbsolutePath();
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                        break;
                }
                break;
            case FRAGMENT_ASISTENCIA:
                AttendanceFragment attendanceFragment = (AttendanceFragment)topFragment;
                switch(item.getItemId()){
                    case R.id.attendance_menu_save:
                        attendanceFragment.trySave();
                        break;
                }
                break;
            case FRAGMENT_MAPA_EDITABLE:
                MapEditFragment mapEditFragment = (MapEditFragment)topFragment;
                switch(item.getItemId()){
                    case R.id.map_menu_save:
                        mapEditFragment.trySave();
                        break;
                }
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((requestCode == REQUEST_IMAGE_CAPTURE || requestCode == REQUEST_IMAGE_GALLERY) && resultCode == RESULT_OK){
            String filePath;
            Bitmap imageBitmap;
            switch(requestCode){
                case REQUEST_IMAGE_CAPTURE:
                    if(data != null){
                        Uri uri = data.getData();
                        String [] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                        cursor.moveToFirst();
                        int columIndex = cursor.getColumnIndex(projection[0]);
                        filePath = cursor.getString(columIndex);
                        cursor.close();
                    }else{
                        filePath = lastCameraPath;
                    }
                    break;
                default:
                    Uri uri = data.getData();
                    String [] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();
                    int columIndex = cursor.getColumnIndex(projection[0]);
                    filePath = cursor.getString(columIndex);
                    cursor.close();
                    break;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            int height = options.outHeight;
            int width = options.outWidth;

            float density = getResources().getDisplayMetrics().density;
            int requiredHeight = getResources().getDisplayMetrics().heightPixels;
            requiredHeight -= density*30; //30 dp margen abajo de botón de enviar
            requiredHeight -= density*50; //50dp de la imagen del botón
            requiredHeight -= density*getResources().getDimension(R.dimen.margin_large)*2; //Margen arriba y abajo de la foto
            int requiredWidth = getResources().getDisplayMetrics().widthPixels;
            requiredWidth -= density*getResources().getDimension(R.dimen.margin_large)*2; //Margen izquierda y derecha

            int inSampleSize = 1;
            if(height > requiredHeight || width > requiredWidth){
                int halfHeight = height / 2;
                int halfWidth = width / 2;
                while((halfHeight / inSampleSize) > requiredHeight
                        && (halfWidth / inSampleSize) > requiredWidth){
                    inSampleSize *= 2;
                }
            }

            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;
            imageBitmap = BitmapFactory.decodeFile(filePath, options);


            BaseFragment fragment = getTopFragment();
            if(fragment instanceof UploadPhotosFragment){
                ((UploadPhotosFragment)fragment).updateBitmap(imageBitmap, filePath);
            }else{
                if(imageBitmap != null) imageBitmap.recycle();
            }
        }else if (requestCode == Constants.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data
                        .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        /*Log.e(TAG, confirm.toJSONObject().toString(4));
                        Log.e(TAG, confirm.getPayment().toJSONObject()
                                .toString(4));*/
                        /*Gson gson= new Gson();
                        gson.fromJson(data.getRes)*/
                        String paymentId = confirm.toJSONObject()
                                .getJSONObject("response").getString("id");

                        String payment_client = confirm.getPayment()
                                .toJSONObject().toString();
                        /*Log.e(TAG, "paymentId: " + paymentId
                                + ", payment_json: " + payment_client);*/

                        // Now verify the payment on the server side
                        if(Constants.PAYMENT_TYPE==0) {
                            Constants.PROGRESS.setMessage("Validando pago...");
                            Constants.PROGRESS.show();
                            paymentListFragment.getPaymentListPresenter().verifyPaymentOnServer(paymentId, payment_client);
                        }
                        else {
                            Toast.makeText(this, "Gracias por su donación :)", Toast.LENGTH_SHORT).show();
                            if(getTopFragment() != null && getTopFragment() instanceof DonationFragment){
                                ((DonationFragment)getTopFragment()).clean();
                            }
                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ",
                                e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e(TAG, "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.e(TAG,
                        "An invalid Payment or PayPalConfiguration was submitted.");
            }
        }
    }

    private File createImageFile(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(Environment.getExternalStorageDirectory() + getExternalImagesDir() + "/Photos/Pic_" + timeStamp + ".jpg");
        file.getParentFile().mkdirs();
        return file;
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    /* Cosas que casi no deberían cambiar */

    private BaseFragment getTopFragment(){
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if(fragments != null) {
            for (int i = fragments.size() - 1; i >= 0; i--) {
                Fragment fragment = fragments.get(i);
                if (fragment != null && fragment instanceof BaseFragment) {
                    return (BaseFragment) fragment;
                }
            }
        }
        return null;
    }

    private FragmentManager.OnBackStackChangedListener backStackListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            boolean noStack = backStackEntryCount == 0;
            setNavIcon(noStack);

            if(backStackEntryCount < previousBackStackCount) {
                //Se sacó un elemento
                BaseFragment fragment = getTopFragment();
                Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
                toolbar.setTitle(getTitle(fragment.getFragmentId()));
                toolbarMenu = getMenu(fragment.getFragmentId());
                invalidateOptionsMenu();
            }
            previousBackStackCount = backStackEntryCount;
        }
    };

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, 4000)
                        .show();
            } else {
                Log.i("GCM", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Inicializo el servicio de Push Notifications


        super.onCreate(savedInstanceState);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.d("gcm","success");
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
        receiver = new NetworkReceiver();

        /****dialog de loading****/
        Constants.PROGRESS=new ProgressDialog(this);
        Constants.PROGRESS.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Constants.PROGRESS.setTitle(getResources().getString(R.string.loading));
        Constants.PROGRESS.setMessage(getResources().getString(R.string.please_wait));
        Constants.PROGRESS.setCancelable(false);

        setContentView(R.layout.base);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);



        selectItem(FRAGMENT_LOGIN);
    }


    @Override
    public void onResume(){
        super.onResume();
        if(Constants.loggedUser != null && Constants.GCM_TOKEN != null){
            presenter.setGCMToken(this, Constants.GCM_TOKEN);
        }
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver, filter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.getCurrecyRate(this);
    }

    @Override
    public void onPause(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void setActions(User user){
        ArrayList<Drawer> list = new ArrayList<>();
        if(isVolunteer(user.getProfiles()) && user.isCanReapply() == 1 && user.getPeriod()!=null) {//si es voluntario puede postular al periodo
            applyOptionItem = new Drawer(-1, "Postular a " + user.getPeriod().getName(), R.drawable.ic_drawer_postulate);
            list.add(applyOptionItem);
        }
        list.add(new Drawer(FRAGMENT_NOTICIAS, getTitle(FRAGMENT_NOTICIAS), R.drawable.ic_drawer_news));
        if(AppEnum.EnumAction.LIST_USERS.hasPermission(user)){
            list.add(new Drawer(FRAGMENT_PERSONAS, getTitle(FRAGMENT_PERSONAS), R.drawable.ic_drawer_people));
        }
        if(AppEnum.EnumAction.SESSION_AND_DOCUMENTS.hasPermission(user)){
            list.add(new Drawer(FRAGMENT_SESIONES, getTitle(FRAGMENT_SESIONES), R.drawable.ic_drawer_sessions));
            list.add(new Drawer(FRAGMENT_DOCUMENTOS, getTitle(FRAGMENT_DOCUMENTOS), R.drawable.ic_drawer_docs));
        }
        if(!isOnlyGodfather(user.getProfiles())) {
            list.add(new Drawer(FRAGMENT_SUBIR_FOTOS, getTitle(FRAGMENT_SUBIR_FOTOS), R.drawable.ic_drawer_upload_photos));
        }
        if(AppEnum.EnumAction.PAYMENT.hasPermission(user) && isGodfather(user.getProfiles())){
            list.add(new Drawer(FRAGMENT_PAGOS, getTitle(FRAGMENT_PAGOS), R.drawable.ic_drawer_payments));
        }
        list.add(new Drawer(FRAGMENT_DONACIONES, getTitle(FRAGMENT_DONACIONES), R.drawable.ic_donations));
        if(AppEnum.EnumAction.LIST_PERIOD_REPORT.hasPermission(user)){
            list.add(new Drawer(FRAGMENT_REPORTES_PADRINOS, getTitle(FRAGMENT_REPORTES_PADRINOS), R.drawable.ic_reports));
        }
        list.add(new Drawer(FRAGMENT_CONFIGURACIÓN, getTitle(FRAGMENT_CONFIGURACIÓN), R.drawable.ic_settings));

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new DrawerAdapter(this, list));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportFragmentManager().addOnBackStackChangedListener(backStackListener);
        mDrawerToggle.syncState();
    }

    public boolean isVolunteer(List<Profile> profiles){
        if(profiles!=null){
            for(Profile p:profiles){
                if(p.getId()==3)
                    return true;
            }
        }

        return false;
    }

    public static boolean isGodfather(List<Profile> profiles){
        if(profiles!=null){
            for(Profile p:profiles){
                if(p.getId()==4)
                    return true;
            }
        }

        return false;
    }

    public static boolean isOnlyGodfather(List<Profile> profiles){
        return profiles != null && profiles.size() == 1 && profiles.get(0).getId() == 4;
    }

    public void hideAppElements(boolean isGone){
        if(isGone){
            toolbar.setVisibility(View.GONE);
            ((android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            //mDrawerList.setVisibility(View.GONE);
        }else{
            toolbar.setVisibility(View.VISIBLE);
            ((android.support.v4.widget.DrawerLayout)findViewById(R.id.drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            //mDrawerList.setVisibility(View.VISIBLE);
        }
    }
    protected void setNavIcon(boolean noStack) {
        mDrawerToggle.setDrawerIndicatorEnabled(noStack);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDrawerToggle.isDrawerIndicatorEnabled()) {
                    onBackPressed();
                }
            }
        });
    }

    public void goBack(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed(){
        if(mDrawerLayout != null) {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            boolean noStack = backStackEntryCount == 0;
            BaseFragment topFragment = getTopFragment();
            if(topFragment == null || (topFragment instanceof LoginFragment)){
                goBack();
            }else if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                if (noStack) {
                    finish();
                } else {
                    goBack(); //Close drawer
                }
            } else {
                if (noStack) {
                    mDrawerLayout.openDrawer(GravityCompat.START); //Open drawer
                } else if (getTopFragment().tryBack()) {
                    goBack();
                }
            }
        }else{
            goBack();
        }
    }

    public static final String DIALOG_TAG_SEARCH_COMMENTS = "search_comments";
    public static final String DIALOG_TAG_SEARCH_USERS = "search_users";
    public static final String DIALOG_TAG_SEARCH_KIDS = "search_kids";
    public static final String DIALOG_TAG_DETAIL_COMMENT = "detail_comment";
    public static final String DIALOG_TAG_DATE = "pick_date";

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.clear();
        if(toolbarMenu != 0){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(toolbarMenu, menu);
            if(toolbarMenu == R.menu.people_menu_toolbar && !AppEnum.EnumAction.LIST_SCHOOL_AND_VOLUNTEER.hasPermission()){
                menu.findItem(R.id.people_menu_map).setVisible(false);
            }

            //Search view
            if(toolbarMenu == R.menu.people_menu_toolbar) {
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                final SearchView searchView = (SearchView) menu.findItem(R.id.people_menu_search).getActionView();
                SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
                searchView.setSearchableInfo(searchableInfo);
                MenuItem menuItem = menu.findItem(R.id.people_menu_search);

                if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    if (menuItem != null) {
                        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
                            @Override
                            public boolean onMenuItemActionExpand(MenuItem item) {
                                return true;
                            }

                            @Override
                            public boolean onMenuItemActionCollapse(MenuItem item) {
                                getTopFragment().onCloseSearch();
                                return true;
                            }
                        });
                    }
                } else {
                    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            getTopFragment().onCloseSearch();
                            return true;
                        }
                    });
                }
                LinearLayout parent = (LinearLayout) searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
                ImageView plusIcon = new ImageView(this);
                plusIcon.setImageResource(R.drawable.ic_menu_advanced_search);
                plusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((PeopleTabFragment) getTopFragment()).showingUsers()) {
                            UserSearchDialog dialog = new UserSearchDialog(){
                                @Override
                                public void onSearch(String name, String doc, String profile) {
                                    getTopFragment().onUserAdvancedSearch(name, doc, profile);
                                }
                            };
                            dialog.show(getSupportFragmentManager(), DIALOG_TAG_SEARCH_USERS);
                        } else {
                            DialogFragment dialog = new KidSearchDialog(){

                                @Override
                                public void onSearch(String name, String fromAge, String toAge, String gender) {
                                    getTopFragment().onKidAdvancedSearch(name, fromAge, toAge, gender);
                                }
                            };
                            dialog.show(getSupportFragmentManager(), DIALOG_TAG_SEARCH_KIDS);
                        }
                    }
                });
                parent.addView(plusIcon);
            }else if(toolbarMenu == R.menu.comments_menu_toolbar) {
                MenuItem menuItem = menu.findItem(R.id.comments_menu_search);
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                final SearchView searchView = (SearchView) menuItem.getActionView();
                SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
                searchView.setSearchableInfo(searchableInfo);
                if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
                        @Override
                        public boolean onMenuItemActionExpand(MenuItem item) {
                            return true;
                        }
                        @Override
                        public boolean onMenuItemActionCollapse(MenuItem item) {
                            getTopFragment().onCloseSearch();
                            return true;
                        }
                    });
                } else {
                    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            getTopFragment().onCloseSearch();
                            return true;
                        }
                    });
                }
                LinearLayout parent = (LinearLayout) searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
                ImageView plusIcon = new ImageView(this);
                plusIcon.setImageResource(R.drawable.ic_menu_advanced_search);
                plusIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommentSearchDialog dialog = new CommentSearchDialog() {
                            @Override
                            public void search(String authorOrContent, long fromDate, long toDate,String orderBy) {
                                getTopFragment().onCommentAdvancedSearch(authorOrContent, fromDate, toDate,orderBy);
                            }
                        };
                        dialog.show(getSupportFragmentManager(), DIALOG_TAG_SEARCH_COMMENTS);
                    }
                });
                parent.addView(plusIcon);
            }else if(toolbarMenu == R.menu.map_menu_toolbar || toolbarMenu == R.menu.map_edit_menu_toolbar){
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) menu.findItem(R.id.map_menu_search).getActionView();
                SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
                searchView.setSearchableInfo(searchableInfo);
            }else if(toolbarMenu == R.menu.news_menu_toolbar){SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                MenuItem menuItem = menu.findItem(R.id.news_menu_search);
                SearchView searchView = (SearchView) menuItem.getActionView();
                SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
                searchView.setSearchableInfo(searchableInfo);
                if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
                        @Override
                        public boolean onMenuItemActionExpand(MenuItem item) {
                            return true;
                        }
                        @Override
                        public boolean onMenuItemActionCollapse(MenuItem item) {
                            getTopFragment().onCloseSearch();
                            return true;
                        }
                    });
                } else {
                    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            getTopFragment().onCloseSearch();
                            return true;
                        }
                    });
                }
            }
        }
        return true;
    }

    public void changeFragment(Fragment fragment, String toolbarTitle, int toolbarMenu){
        try {
            if (getTopFragment() == null || getTopFragment().tryBack()) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                if (toolbarTitle != null) {
                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
                    toolbar.setTitle(toolbarTitle);
                }
                this.toolbarMenu = toolbarMenu;
                invalidateOptionsMenu();
            }
        }catch(IllegalStateException e){

        }
    }

    public void addFragment(Fragment fragment, String toolbarTitle, int toolbarMenu){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
        if(toolbarTitle != null){
            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
            toolbar.setTitle(toolbarTitle);
        }
        this.toolbarMenu = toolbarMenu;
        invalidateOptionsMenu();
    }

    @Override
    public void removeApplyOption(){
        TextView name = (TextView)findViewById(R.id.drawer_list_item_name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            name.setTextColor(getResources().getColor(R.color.dark_text, null));
        }else{
            name.setTextColor(getResources().getColor(R.color.dark_text));
        }
        ((DrawerAdapter) mDrawerList.getAdapter()).removeItem(applyOptionItem);
    }

    @Override
    public void saveUserToSharedPreferences(){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        Gson gson= new Gson();
        editor.putString("loggedUser", gson.toJson(Constants.loggedUser));
        editor.commit();
    }

    @Override
    public void loadUserFromSharedPreferences(){
        Gson gson= new Gson();
        User user = gson.fromJson(PreferenceManager.getDefaultSharedPreferences(this).getString("loggedUser", null), User.class);
        Constants.loggedUser=user;
    }

    @Override
    public void logOff(){
        Constants.loggedUser = null;
        PreferenceManager.getDefaultSharedPreferences(this).edit().remove("loggedUser").commit();
        selectItem(DetailActivity.FRAGMENT_LOGIN);

        SyncAttendanceChild.deleteAll(SyncAttendanceChild.class);
        SyncAttendanceVolunteer.deleteAll(SyncAttendanceVolunteer.class);
        SyncComment.deleteAll(SyncComment.class);
        SyncDocument.deleteAll(SyncDocument.class);
        SyncDocumentUser.deleteAll(SyncDocumentUser.class);
        SyncKid.deleteAll(SyncKid.class);
        SyncLocation.deleteAll(SyncLocation.class);
        SyncNews.deleteAll(SyncNews.class);
        SyncPayment.deleteAll(SyncPayment.class);
        SyncPointOfReunion.deleteAll(SyncPointOfReunion.class);
        SyncSchoolAddress.deleteAll(SyncSchoolAddress.class);
        SyncSession.deleteAll(SyncSession.class);
        SyncUser.deleteAll(SyncUser.class);

        presenter.clearGCMToken(this);
    }

    private void tryPostulate(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    presenter.apply(Constants.loggedUser.getPeriod().getId());

                }else if(which == DialogInterface.BUTTON_NEGATIVE){

                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirm_postulate).setPositiveButton(android.R.string.yes, dialogClickListener)
                .setNegativeButton(android.R.string.no, dialogClickListener);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void selectItem(int fragmentId){
        Bundle args = new Bundle();
        Fragment fragment;
        switch(fragmentId){
            default:
                Gson gson= new Gson();
                User user = gson.fromJson(PreferenceManager.getDefaultSharedPreferences(this).getString("loggedUser", null),User.class);
                if(user==null) {
                    hideAppElements(true);
                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_LOGIN);
                    fragment = new LoginFragment();
                    fragment.setArguments(args);
                    selectedLayout = fragmentId;
                    changeFragment(fragment, getTitle(selectedLayout), getMenu(selectedLayout));
                }else{
                    presenter.validateUser(this, user.getUsername(),user.getPassword());
                    Constants.loggedUser=user;
                    Constants.TOKEN=user.getAuthToken();
                    setActions(user);
                    selectItem(FRAGMENT_NOTICIAS);
                }
                return;

            case FRAGMENT_NOTICIAS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_NOTICIAS);
                fragment = new NewsTabFragment();
                break;
            case FRAGMENT_PERSONAS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_PERSONAS);
                fragment = new PeopleTabFragment();
                break;
            case FRAGMENT_SESIONES:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SESIONES);
                fragment = new SessionFragment();
                break;
            case FRAGMENT_DOCUMENTOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_DOCUMENTOS);
                fragment = new DocumentsFragment();
                break;
            case FRAGMENT_SUBIR_FOTOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SUBIR_FOTOS);
                fragment = new UploadPhotosFragment();
                break;
            case FRAGMENT_PAGOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_PAGOS);
                fragment=new PaymentListFragment();
                paymentListFragment=(PaymentListFragment)fragment;
                break;
            case FRAGMENT_REPORTES_PADRINOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_REPORTES_PADRINOS);
                fragment=new PeriodReportFragment();
                break;
            case FRAGMENT_DONACIONES:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_DONACIONES);
                fragment = new DonationFragment();
                break;
            case FRAGMENT_CONFIGURACIÓN:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_CONFIGURACIÓN);
                fragment = new SettingsFragment();
                break;
        }
        fragment.setArguments(args);
        mDrawerList.setItemChecked(fragmentId, true);
        mDrawerLayout.closeDrawer(mDrawerList);
        selectedLayout = fragmentId;
        changeFragment(fragment, getTitle(selectedLayout), getMenu(selectedLayout));
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .afiAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void displayApplySuccessMessage() {
        Toast.makeText(getBaseContext(), "Has postulado al periodo " + Constants.loggedUser.getPeriod().getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayApplyFailureMessage(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int fragmentId = (Integer)view.getTag();
            if(fragmentId == -1) {
                tryPostulate();
            }else{
                selectItem(fragmentId);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    public String getExternalImagesDir(){
        return "/" + getResources().getString(R.string.app_name) + "/Images";
    }

    public String getExternalFilesDir(){
        return "/" + getResources().getString(R.string.app_name) + "/Files";
    }

    public String getPath(String path, boolean overwrite){
        File file = new File(path);
        String finalPath;
        if(overwrite || !file.exists()){
            finalPath = path;
        }else{
            int extensionIndex = path.length() - 1;
            while(extensionIndex >= 0 && path.charAt(extensionIndex) != '.'){
                extensionIndex--;
            }
            int i=0;
            do{
                i++;
                if(extensionIndex >= 0){
                    finalPath = path.substring(0, extensionIndex) + "(" + i + ")" + path.substring(extensionIndex, path.length());
                }else{
                    finalPath = path + "(" + i + ")";
                }
            }while((new File(finalPath)).exists());
        }
        return finalPath;
    }

    public void setDownloadFile(String URL, String title, boolean overwrite){
        String path = Environment.getExternalStorageDirectory() + getExternalFilesDir() + "/" + title;
        String finalPath = getPath(path, overwrite);
        DownloadFileTask task = new DownloadFileTask(this);
        task.execute(URL, finalPath);
    }

    public void setDownloadDocument(SyncDocument doc, String URL){
        String path = Environment.getExternalStorageDirectory() + getExternalFilesDir() + "/" + doc.getName();
        String finalPath = getPath(path, false);
        DownloadDocumentTask task = new DownloadDocumentTask(this, doc);
        task.execute(URL, finalPath);
    }

    public void setImage(ImageView v, String URL, String title){
        String path = Environment.getExternalStorageDirectory() + getExternalImagesDir() + "/" + title;
        File file = new File(path);
        if(file.exists()){
            v.setImageURI(Uri.parse(path));
            Log.d("imgs", "Uri set to ImageView already there: " + path);
        }else{
            DownloadImageTask task = new DownloadImageTask(this, v);
            task.execute(URL, path);
            Log.d("imgs", "Task created for:" + title);
        }
    }

    public static class DownloadFileTask extends AsyncTask<String, Void, String>{
        DetailActivity activity;
        public DownloadFileTask(DetailActivity activity){
            this.activity = activity;
        }

        public boolean openAfterDownloading(){
            return true;
        }

        @Override
        protected String doInBackground(String... args){
            String url = args[0];
            String path = args[1];

            return activity.downloadFile(url, path);
        }

        @Override
        protected void onPostExecute(String uriResult){
            super.onPostExecute(uriResult);
            if(uriResult != null && openAfterDownloading()) {
                boolean openedFile = activity.openFile(uriResult);
                if(!openedFile){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage(
                            activity.getResources().getString(R.string.no_application_to_open_file,
                                    activity.getResources().getString(R.string.app_name)))
                            .setNeutralButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        }
    }

    public static class DownloadDocumentTask extends DownloadFileTask{
        SyncDocument doc;

        public DownloadDocumentTask(DetailActivity activity, SyncDocument doc){
            super(activity);
            this.doc = doc;
        }

        @Override
        protected void onPostExecute(String uriResult){
            super.onPostExecute(uriResult);
            if(uriResult != null) {
                Log.d("docs", "Uri set to SyncDocument just downloaded: " + uriResult);
                doc.setLastUri(uriResult);
                doc.save();
            }
        }
    }

    public static class DownloadImageTask extends DownloadFileTask{
        ImageView view;

        public DownloadImageTask(DetailActivity activity, ImageView view){
            super(activity);
            this.view = view;
        }

        @Override
        public boolean openAfterDownloading(){
            return false;
        }

        @Override
        protected void onPostExecute(String uriResult){
            super.onPostExecute(uriResult);
            if(uriResult != null) {
                Log.d("imgs", "Uri set to ImageView just downloaded: " + uriResult);
                view.setImageURI(Uri.parse(uriResult));
            }
        }
    }

    public String downloadFile(String strUrl, String path){
        try{
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.connect();

            File file = new File(path);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(file);
            InputStream is = urlConn.getInputStream();
            int totalSize = urlConn.getContentLength();
            int downloadedSize = 0;
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ( (bufferLength = is.read(buffer)) > 0 )
            {
                os.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }
            os.close();
            if(downloadedSize==totalSize){
                return path;
            }
        }catch(IOException e) {
            Log.e("imgs", "", e);
        }
        return null;
    }

    public boolean openFile(String uri){
        File file = new File(uri);
        if(file.exists()) {
            String MIMEType;
            if (uri.endsWith(".pdf")) {
                MIMEType = "application/pdf";
            } else if (uri.endsWith(".doc")) {
                MIMEType = "application/doc";
            } else if (uri.endsWith(".docx")) {
                MIMEType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            } else if (uri.endsWith(".xls")) {
                MIMEType = "application/vnd.ms-excel";
            } else if (uri.endsWith(".xlsx")) {
                MIMEType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            } else {
                MIMEType = null;
            }

            try {
                if (MIMEType == null) {
                    throw new ActivityNotFoundException();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + uri), MIMEType);
                startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    getResources().getString(R.string.file_not_found))
                    .setNeutralButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
    }

    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(intent.getAction() != null && intent.getAction().equals(Intent.ACTION_SEARCH)){
            String query = intent.getStringExtra(SearchManager.QUERY);
            getTopFragment().onSearch(query);
        }
    }



    @Override
    public void synchronize(){
        List<SyncComment> comments = SyncComment.find(SyncComment.class, "needsync = ?", "1");
        if (!comments.isEmpty()) {
            Toast.makeText(this, getString(R.string.sync_comments), Toast.LENGTH_SHORT).show();
            for (SyncComment comment : comments) {
                List<SyncAttendanceChild> child = SyncAttendanceChild.find(SyncAttendanceChild.class, "attendance_child_id = ?",
                        String.valueOf(comment.getAttendanceChild()));
                presenter.makeComment(this, child.get(0), comment);
            }
        }

        List<SyncAttendanceVolunteer> volunteers = SyncAttendanceVolunteer.find(SyncAttendanceVolunteer.class, "needsync = ?", "1");
        if(!volunteers.isEmpty()){
            Toast.makeText(this, getString(R.string.sync_attendance), Toast.LENGTH_SHORT).show();
            HashMap<Integer, List<SyncAttendanceVolunteer>> vols = new HashMap<>();
            for(SyncAttendanceVolunteer vol : volunteers){
                int ses = vol.getSession();
                if(vols.containsKey(ses)){
                    vols.get(ses).add(vol);
                }else{
                    ArrayList<SyncAttendanceVolunteer> list = new ArrayList<>();
                    list.add(vol);
                    vols.put(ses, list);
                }
            }

            for(Map.Entry<Integer, List<SyncAttendanceVolunteer>> entry : vols.entrySet()){
                presenter.editAttendance(this, entry.getKey(), entry.getValue());
            }
        }
    }
}
