package com.example.dp2.afiperu.ui.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseActivity;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.component.DaggerMainActivityComponent;
import com.example.dp2.afiperu.domain.AFIEvent;
import com.example.dp2.afiperu.domain.Action;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.domain.Drawer;
import com.example.dp2.afiperu.domain.News;
import com.example.dp2.afiperu.domain.PeopleKids;
import com.example.dp2.afiperu.domain.Profile;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.module.MainActivityModule;
import com.example.dp2.afiperu.presenter.MainActivityPresenter;
import com.example.dp2.afiperu.rest.model.LoginUser;
import com.example.dp2.afiperu.ui.adapter.DrawerAdapter;
import com.example.dp2.afiperu.ui.dialogs.CommentSearchDialog;
import com.example.dp2.afiperu.ui.dialogs.KidSearchDialog;
import com.example.dp2.afiperu.ui.fragment.BlogSearchFragment;
import com.example.dp2.afiperu.ui.fragment.BlogTabFragment;
import com.example.dp2.afiperu.ui.fragment.CalendarFragment;
import com.example.dp2.afiperu.ui.fragment.DocumentsFragment;
import com.example.dp2.afiperu.ui.fragment.DonationFragment;
import com.example.dp2.afiperu.ui.fragment.FavoriteBlogFragment;
import com.example.dp2.afiperu.ui.fragment.FavoriteNewsFragment;
import com.example.dp2.afiperu.ui.fragment.LoginFragment;
import com.example.dp2.afiperu.ui.fragment.MapEditFragment;
import com.example.dp2.afiperu.ui.fragment.NewsFragment;
import com.example.dp2.afiperu.ui.fragment.NewsTabFragment;
import com.example.dp2.afiperu.ui.fragment.PaymentListFragment;
import com.example.dp2.afiperu.ui.fragment.PeopleKidsFragment;
import com.example.dp2.afiperu.ui.fragment.PeopleTabFragment;
import com.example.dp2.afiperu.ui.fragment.PeriodReportFragment;
import com.example.dp2.afiperu.ui.fragment.SessionFragment;
import com.example.dp2.afiperu.ui.fragment.SettingsFragment;
import com.example.dp2.afiperu.ui.fragment.UploadPhotosFragment;
import com.example.dp2.afiperu.ui.fragment.UsersFragment;
import com.example.dp2.afiperu.ui.viewmodel.MainActivityView;
import com.example.dp2.afiperu.util.AppEnum;
import com.example.dp2.afiperu.util.Constants;
import com.example.dp2.afiperu.util.NetworkReceiver;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Fernando on 16/09/2015.
 */
public class DetailActivity extends BaseActivity implements MainActivityView {

    public static final int FRAGMENT_NOTICIAS = 0;
    public static final int FRAGMENT_BLOG = 1;
    public static final int FRAGMENT_PERSONAS = 2;
    public static final int FRAGMENT_SESIONES = 4;
    public static final int FRAGMENT_DOCUMENTOS = 5;
    public static final int FRAGMENT_SUBIR_FOTOS = 6;
    public static final int FRAGMENT_PAGOS = 7;
    public static final int FRAGMENT_DONACIONES = 8;
    public static final int FRAGMENT_REPORTES_PADRINOS = 20;
    public static final int FRAGMENT_CONFIGURACIÓN = 9;

    public static final int FRAGMENT_DETALLE_NOTICIAS = 10;
    public static final int FRAGMENT_DETALLE_BLOG = 11;
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
    int previousBackStackCount;
    Drawer applyOptionItem;
    SharedPreferences sharedPreferences;
    @Inject
    MainActivityPresenter presenter;

    /* Cosas a agregar con cada layout nuevo */

    public String getTitle(int fragmentId){
        int id = 0;
        switch(fragmentId){
            case FRAGMENT_NOTICIAS: id = R.string.menu_noticias; break;
            case FRAGMENT_PERSONAS: id = R.string.menu_personas; break;
            case FRAGMENT_SESIONES: id = R.string.menu_sesiones; break;
            case FRAGMENT_DOCUMENTOS: id = R.string.menu_documentos; break;
            case FRAGMENT_SUBIR_FOTOS: id = R.string.menu_subir_fotos; break;
            case FRAGMENT_BLOG: id = R.string.menu_blog; break;
            case FRAGMENT_PAGOS: id = R.string.menu_pagos; break;
            case FRAGMENT_DONACIONES: id = R.string.menu_donaciones; break;
            case FRAGMENT_REPORTES_PADRINOS: id = R.string.menu_reportes; break;
            case FRAGMENT_CONFIGURACIÓN: id = R.string.menu_configuracion; break;

            case FRAGMENT_LOGIN: id = R.string.app_name; break;
            case FRAGMENT_DETALLE_NOTICIAS: id = R.string.menu_noticias; break;
            case FRAGMENT_ASISTENCIA: id = R.string.title_asistencia; break;
            case FRAGMENT_COMENTARIOS: id = R.string.title_comentarios; break;
            case FRAGMENT_USUARIOS_REVISADO: id = R.string.menu_documentos; break;
            case FRAGMENT_DETALLE_BLOG: id = R.string.menu_blog; break;
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
            case FRAGMENT_DOCUMENTOS: return R.menu.docs_menu_toolbar;
            case FRAGMENT_SESIONES: return R.menu.sessions_menu_toolbar;
            case FRAGMENT_SUBIR_FOTOS: return R.menu.upload_photos_toolbar;
            case FRAGMENT_DETALLE_NOTICIAS: return R.menu.news_article_menu_toolbar;
            case FRAGMENT_DETALLE_BLOG: return R.menu.blog_article_menu_toolbar;
            case FRAGMENT_BLOG: return R.menu.blogs_menu_toolbar;
            case FRAGMENT_PERSONAS: return R.menu.people_menu_toolbar;
            case FRAGMENT_LISTA_COMENTARIOS: return R.menu.comments_menu_toolbar;
            case FRAGMENT_MAPA: return R.menu.map_menu_toolbar;
            case FRAGMENT_MAPA_EDITABLE: return R.menu.map_edit_menu_toolbar;
            default: return 0;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        BaseFragment topFragment = getTopFragment();
        int fragmentId = topFragment.getFragmentId();
        switch(fragmentId){
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
                        Hashtable<Long, AFIEvent> events = new Hashtable<>();
                        Calendar dateNoTime = new GregorianCalendar(2015, 9, 20);
                        Calendar dateWithTime = new GregorianCalendar(2015, 9, 20, 16, 0);
                        events.put(dateNoTime.getTime().getTime(),
                                new AFIEvent("Av. Antonio Alarco 497", dateWithTime.getTime().getTime(), "Sesión 1"));
                        Bundle args = new Bundle();
                        args.putSerializable(CalendarFragment.EVENTS_ARG, events);
                        args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_CALENDARIO);
                        BaseFragment calendarFragment = new CalendarFragment();
                        calendarFragment.setArguments(args);
                        addFragment(calendarFragment, getTitle(FRAGMENT_CALENDARIO), getMenu(FRAGMENT_CALENDARIO));
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

    /* Cosas que casi no deberían cambiar */

    private BaseFragment getTopFragment(){
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(int i=fragments.size()-1; i>=0; i--){
            Fragment fragment = fragments.get(i);
            if (fragment != null) {
                return (BaseFragment) fragment;
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
    public void setUpPreferences(){
        sharedPreferences=getSharedPreferences("MyPreference",Context.MODE_PRIVATE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /******************************/

        setUpPreferences();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver, filter);

        /****dialog de loading****/
        Constants.PROGRESS=new ProgressDialog(this);
        Constants.PROGRESS.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Constants.PROGRESS.setTitle(getResources().getString(R.string.loading));
        Constants.PROGRESS.setMessage(getResources().getString(R.string.please_wait));

        setContentView(R.layout.base);
        /*
        ArrayList<Drawer> list = new ArrayList<>();
        list.add(new Drawer(-1, getResources().getString(R.string.menu_postular), R.drawable.ic_drawer_postulate));
        list.add(new Drawer(FRAGMENT_NOTICIAS, getTitle(FRAGMENT_NOTICIAS), R.drawable.ic_drawer_news));
        list.add(new Drawer(FRAGMENT_BLOG, getTitle(FRAGMENT_BLOG), R.drawable.ic_drawer_blog));
        list.add(new Drawer(FRAGMENT_PERSONAS, getTitle(FRAGMENT_PERSONAS), R.drawable.ic_drawer_people));
        list.add(new Drawer(FRAGMENT_SESIONES, getTitle(FRAGMENT_SESIONES), R.drawable.ic_drawer_sessions));
        list.add(new Drawer(FRAGMENT_DOCUMENTOS, getTitle(FRAGMENT_DOCUMENTOS), R.drawable.ic_drawer_docs));
        list.add(new Drawer(FRAGMENT_SUBIR_FOTOS, getTitle(FRAGMENT_SUBIR_FOTOS), R.drawable.ic_drawer_upload_photos));
        list.add(new Drawer(FRAGMENT_PAGOS, getTitle(FRAGMENT_PAGOS), R.drawable.ic_drawer_payments));
        list.add(new Drawer(FRAGMENT_DONACIONES, getTitle(FRAGMENT_DONACIONES), R.drawable.ic_donations));
        list.add(new Drawer(FRAGMENT_REPORTES_PADRINOS, getTitle(FRAGMENT_REPORTES_PADRINOS), R.drawable.ic_reports));
        list.add(new Drawer(FRAGMENT_CONFIGURACIÓN, getTitle(FRAGMENT_CONFIGURACIÓN), R.drawable.ic_settings));
        //list.add(new Drawer(FRAGMENT_LOGIN, getTitle(FRAGMENT_LOGIN), R.drawable.ic_drawer_news)); //Temporal

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
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);*/

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);

        selectItem(FRAGMENT_LOGIN);
    }

    public void setActions(LoginUser user){
        ArrayList<Action> actions=null;
        ArrayList<Drawer> list=list = new ArrayList<>();
        if(user!=null) {
           actions = (ArrayList<Action>) user.getActions();

        }
        if(user==null || isWebMaster(user.getProfiles())){
            //list.add(new Drawer(-1, getResources().getString(R.string.menu_postular), R.drawable.ic_drawer_postulate));
            list.add(new Drawer(FRAGMENT_NOTICIAS, getTitle(FRAGMENT_NOTICIAS), R.drawable.ic_drawer_news));
            list.add(new Drawer(FRAGMENT_BLOG, getTitle(FRAGMENT_BLOG), R.drawable.ic_drawer_blog));
            list.add(new Drawer(FRAGMENT_DONACIONES, getTitle(FRAGMENT_DONACIONES), R.drawable.ic_donations));
            list.add(new Drawer(FRAGMENT_SUBIR_FOTOS, getTitle(FRAGMENT_SUBIR_FOTOS), R.drawable.ic_drawer_upload_photos));
            list.add(new Drawer(FRAGMENT_PERSONAS, getTitle(FRAGMENT_PERSONAS), R.drawable.ic_drawer_people));
            list.add(new Drawer(FRAGMENT_SESIONES, getTitle(FRAGMENT_SESIONES), R.drawable.ic_drawer_sessions));
            list.add(new Drawer(FRAGMENT_DOCUMENTOS, getTitle(FRAGMENT_DOCUMENTOS), R.drawable.ic_drawer_docs));
            list.add(new Drawer(FRAGMENT_REPORTES_PADRINOS, getTitle(FRAGMENT_REPORTES_PADRINOS), R.drawable.ic_reports));
            list.add(new Drawer(FRAGMENT_PAGOS, getTitle(FRAGMENT_PAGOS), R.drawable.ic_drawer_payments));
            list.add(new Drawer(FRAGMENT_CONFIGURACIÓN, getTitle(FRAGMENT_CONFIGURACIÓN), R.drawable.ic_settings));
        }else{
            if(isVolunteer(user.getProfiles()) && user.getPeriod()!=null) {//si es voluntario puede postular al periodo
                applyOptionItem = new Drawer(-1, "Postular a " + user.getPeriod().getName(), R.drawable.ic_drawer_postulate);
                list.add(applyOptionItem);
            }
            /**Permisos que todos tienen independiente del perfil**/
            list.add(new Drawer(FRAGMENT_NOTICIAS, getTitle(FRAGMENT_NOTICIAS), R.drawable.ic_drawer_news));
            list.add(new Drawer(FRAGMENT_BLOG, getTitle(FRAGMENT_BLOG), R.drawable.ic_drawer_blog));
            list.add(new Drawer(FRAGMENT_DONACIONES, getTitle(FRAGMENT_DONACIONES), R.drawable.ic_donations));
            list.add(new Drawer(FRAGMENT_SUBIR_FOTOS, getTitle(FRAGMENT_SUBIR_FOTOS), R.drawable.ic_drawer_upload_photos));


            for(Action a:actions){
                if(a.getId()== AppEnum.EnumAction.LIST_USERS.getNumVal())
                    list.add(new Drawer(FRAGMENT_PERSONAS, getTitle(FRAGMENT_PERSONAS), R.drawable.ic_drawer_people));
                if(a.getId()==AppEnum.EnumAction.SESSION_AND_DOCUMENTS.getNumVal()){
                    list.add(new Drawer(FRAGMENT_SESIONES, getTitle(FRAGMENT_SESIONES), R.drawable.ic_drawer_sessions));
                    list.add(new Drawer(FRAGMENT_DOCUMENTOS, getTitle(FRAGMENT_DOCUMENTOS), R.drawable.ic_drawer_docs));
                }
                if(a.getId()==AppEnum.EnumAction.LIST_PERIOD_REPORT.getNumVal())
                    list.add(new Drawer(FRAGMENT_REPORTES_PADRINOS, getTitle(FRAGMENT_REPORTES_PADRINOS), R.drawable.ic_reports));
                if(a.getId()==AppEnum.EnumAction.PAYMENT.getNumVal())
                    list.add(new Drawer(FRAGMENT_PAGOS, getTitle(FRAGMENT_PAGOS), R.drawable.ic_drawer_payments));
            }
            list.add(new Drawer(FRAGMENT_CONFIGURACIÓN, getTitle(FRAGMENT_CONFIGURACIÓN), R.drawable.ic_settings));
        }

        //list.add(new Drawer(FRAGMENT_LOGIN, getTitle(FRAGMENT_LOGIN), R.drawable.ic_drawer_news)); //Temporal

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

    public boolean isWebMaster(List<Profile> profiles){
        if(profiles!=null){
            for(Profile p:profiles){
                if(p.getId()==1)
                    return true;
            }
        }
        return false;
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
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.clear();
        if(toolbarMenu != 0){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(toolbarMenu, menu);

            //Search view
            final int menuItem;
            final String dialogTag;
            final Class<?> dialogFragmentClass;
            switch(toolbarMenu){
                case R.menu.people_menu_toolbar:
                    menuItem = R.id.people_menu_search;
                    dialogTag = DIALOG_TAG_SEARCH_KIDS;
                    dialogFragmentClass = KidSearchDialog.class;
                    break;
                /*case R.menu.people_menu_toolbar:
                    menuItem = R.id.people_menu_search;
                    if(((PeopleTabFragment)getTopFragment()).showingUsers()){
                        dialogTag = DIALOG_TAG_SEARCH_USERS;
                        dialogFragmentClass = UserSearchDialog.class;
                    }else{
                        dialogTag = DIALOG_TAG_SEARCH_KIDS;
                        dialogFragmentClass = KidSearchDialog.class;
                    }
                    break;*/
                case R.menu.comments_menu_toolbar:
                    menuItem = R.id.comments_menu_search;
                    dialogTag = DIALOG_TAG_SEARCH_COMMENTS;
                    dialogFragmentClass = CommentSearchDialog.class;
                    break;
                case R.menu.map_menu_toolbar:
                case R.menu.map_edit_menu_toolbar:
                    menuItem = R.id.map_menu_search;
                    dialogTag = null;
                    dialogFragmentClass = null;
                    break;
                default:
                    menuItem = 0;
                    dialogTag = null;
                    dialogFragmentClass = null;
                    break;
            }
            if(menuItem != 0) {
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) menu.findItem(menuItem).getActionView();
                SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
                searchView.setSearchableInfo(searchableInfo);

                if(dialogFragmentClass != null) {
                    LinearLayout parent = (LinearLayout) searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
                    ImageView plusIcon = new ImageView(this);
                    plusIcon.setImageResource(R.drawable.ic_menu_advanced_search);
                    plusIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                DialogFragment dialog = (DialogFragment) dialogFragmentClass.newInstance();
                                dialog.show(getSupportFragmentManager(), dialogTag);
                            } catch (InstantiationException e) {
                                Log.e("", "", e);
                            } catch (IllegalAccessException e) {
                                Log.e("", "", e);
                            }
                        }
                    });
                    parent.addView(plusIcon);
                }
            }
        }
        return true;
    }

    public void changeFragment(Fragment fragment, String toolbarTitle, int toolbarMenu){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        if(toolbarTitle != null){
            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
            toolbar.setTitle(toolbarTitle);
        }
        this.toolbarMenu = toolbarMenu;
        invalidateOptionsMenu();
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
    public void removeApplyOption(){
        ((DrawerAdapter)mDrawerList.getAdapter()).removeItem(applyOptionItem);
    }
    private void tryPostulate(final int position){
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

    public void selectItem(int fragmentId){
        Bundle args = new Bundle();
        Fragment fragment;
        switch(fragmentId){
            default:
                Gson gson= new Gson();
                LoginUser user = gson.fromJson(sharedPreferences.getString("loggedUser",null),LoginUser.class);
                if(user==null) {
                    hideAppElements(true);
                    //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_LOGIN);
                    fragment = new LoginFragment();
                    fragment.setArguments(args);
                    selectedLayout = fragmentId;
                    changeFragment(fragment, getTitle(selectedLayout), getMenu(selectedLayout));
                }else{
                    Constants.loggedUser=user;
                    Constants.TOKEN=user.getAuthToken();
                    setActions(user);
                    selectItem(FRAGMENT_NOTICIAS);
                }
                return;

            case FRAGMENT_NOTICIAS:
//                isNetworkConnected();
/*
                SyncUser su = new SyncUser();
                su.setName("A ver");
                su.save();
                Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
                */
                /*
                SyncSession se = new SyncSession();
                se.setName("Sesion1");
                SyncPointOfReunion pr = new SyncPointOfReunion();
                pr.setLongitude(2.2);
                pr.setLatitude(2.2);

                pr.save();
                SyncLocation lo = new SyncLocation();
                lo.setLongitude(4.4);
                lo.setLatitude(4.4);

                se.getPointsOfReunion().add(pr);
                se.setLocation(lo);

                se.save();
                pr.setSession(se);
                lo.setSession(se);
                pr.save();
                lo.save();
                Toast.makeText(getBaseContext(),se.getId().toString() + " IDD",Toast.LENGTH_SHORT).show();
                */
                ArrayList<News> news = new ArrayList<>();
                Calendar calendar = new GregorianCalendar(2015, 8, 25, 0, 31);
                news.add(new News(
                        1001,
                        "https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xat1/v/t1.0-9/13779_10153228555392486_8679903887061635913_n.jpg?oh=00b977b776d2b46e53c88f229bc38250&oe=5668F7B7&__gda__=1453937731_0addbe5c62688f1ca9aa12cef23593eb",
                        "Paseo pinoteco al parque de las leyendas",
                        2001,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                        "Manuel", calendar.getTime().getTime(), true));
                calendar = new GregorianCalendar(2015, 8, 24, 23, 40);
                news.add(new News(
                        1002,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/11024597_10153107115432486_3774679476823351402_n.jpg?oh=2e13703d43f85c64bde46ce3b0ff4738&oe=5697777F",
                        "Voluntarios accederan a puntajes para becas y viviendas",
                        2001,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                        "Gabriela", calendar.getTime().getTime(), false));
                calendar = new GregorianCalendar(2015, 8, 21, 15, 22);
                news.add(new News(
                        1003,
                        "https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-9/11150479_10153215037187486_66126204997766216_n.jpg?oh=efc6181115bfcd36dd12eeda1f2be26d&oe=56A16C75&__gda__=1449207363_cde5ddb130a171ea4e9fc3d09933356b",
                        "Recurso multimedia que utiliza el cuento como motivación inicial, dirigido a niños",
                        2001,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                        "Yuri", calendar.getTime().getTime(), false));
                args.putSerializable(NewsFragment.NEWS_ARG, news);
                ArrayList<News> favoriteNews = new ArrayList<>();
                favoriteNews.add(news.get(0));
                args.putSerializable(FavoriteNewsFragment.FAVORITE_NEWS_ARG, favoriteNews);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_NOTICIAS);
                fragment = new NewsTabFragment();
                break;
            case FRAGMENT_BLOG:
/*
                List<SyncSession> out = SyncSession.listAll(SyncSession.class);

                List<SyncPointOfReunion> loca = SyncLocation.find(SyncPointOfReunion.class, " session = ?", String.valueOf(out.get(7).getId()));
                //List<Book> books = Book.listAll(Book.class);
                //Toast.makeText(getBaseContext(),loca.get(0).getLatitude().toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(),out.get(7).getPointsOfReunion().get(0).getLatitude().toString(),Toast.LENGTH_SHORT).show();
  */
                ArrayList<Blog> blogs= new ArrayList<>();
                calendar=new GregorianCalendar(2015,8,22,14,25);
                blogs.add(new Blog("Titulo 1","Daekef Abarca",calendar.getTime().getTime(),false));
                calendar=new GregorianCalendar(2015,7,24,48,27);
                blogs.add(new Blog("Titulo 2","Fernando Banda",calendar.getTime().getTime(),true));
                calendar=new GregorianCalendar(2015,6,15,9,45);
                blogs.add(new Blog("Titulo 3","Luis Barcena",calendar.getTime().getTime(),false));
                Collections.sort(blogs);
                args.putSerializable(BlogSearchFragment.BLOG_ARG, blogs);
                ArrayList<Blog> favoriteBlogs = new ArrayList<>();
                favoriteBlogs.add(blogs.get(1));
                args.putSerializable(FavoriteBlogFragment.FAVORITE_BLOG_ARG, favoriteBlogs);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_BLOG);
                fragment=new BlogTabFragment();
                break;
            case FRAGMENT_PERSONAS:
                ArrayList<User> users= new ArrayList<>();
                users.add(new User("dabarca",20101147,"Daekef","Abarca","Cusimayta", "Miembro de AFI",3.5, true));
                users.add(new User("fbanda",20107845,"Fernando","Banda","Cardenas", "Voluntario",4.8, false));
                users.add(new User("lbarcena",20101019,"Luis","Barcena","Navarro","Padrino",1.0, true));
                Collections.sort(users);
                args.putSerializable(UsersFragment.USER_ARG, users);
                ArrayList<PeopleKids> kids= new ArrayList<>();
                kids.add(new PeopleKids(false,12,"Perales","Perez","Paola"));
                kids.add(new PeopleKids(true,10,"Perales","Perez","Juan"));
                kids.add(new PeopleKids(false,11,"Perales","Perez","Rosario"));
                Collections.sort(kids);
                args.putSerializable(PeopleKidsFragment.PEOPLE_KIDS_ARG, kids);
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
        Toast.makeText(getBaseContext(), "Has postulado al periodo "+ Constants.loggedUser.getPeriod().getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayApplyFailureMessage() {
        Toast.makeText(getBaseContext(),"No se pudo realizar la postulación",Toast.LENGTH_SHORT).show();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int fragmentId = (Integer)view.getTag();
            if(fragmentId == -1) {
                tryPostulate(position);
            }else{
                selectItem(fragmentId);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            this.unregisterReceiver(receiver);
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

    public void setDownloadFile(String URL, String title, boolean overwrite){
        String path = Environment.getExternalStorageDirectory() + getExternalFilesDir() + "/" + title;
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
        DownloadFileTask task = new DownloadFileTask(this);
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

        @Override
        protected String doInBackground(String... args){
            String url = args[0];
            String path = args[1];

            return activity.downloadFile(url, path);
        }

        @Override
        protected void onPostExecute(String uriResult){
            super.onPostExecute(uriResult);
            if(uriResult != null) {
                String MIMEType;
                if(uriResult.endsWith(".pdf")){
                    MIMEType = "application/pdf";
                }else if(uriResult.endsWith(".doc")){
                    MIMEType = "application/doc";
                }else if(uriResult.endsWith(".docx")){
                    MIMEType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                }else if(uriResult.endsWith(".xls")){
                    MIMEType = "application/vnd.ms-excel";
                }else if(uriResult.endsWith(".xlsx")){
                    MIMEType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                }else{
                    MIMEType = null;
                }

                try {
                    if(MIMEType == null){
                        throw new ActivityNotFoundException();
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse("file://" + uriResult), MIMEType);
                    activity.startActivity(intent);
                }catch (ActivityNotFoundException e){
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

    public static class DownloadImageTask extends DownloadFileTask{
        ImageView view;

        public DownloadImageTask(DetailActivity activity, ImageView view){
            super(activity);
            this.view = view;
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

    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(intent.getAction().equals(Intent.ACTION_SEARCH)){
            String query = intent.getStringExtra(SearchManager.QUERY);
            getTopFragment().onSearch(query);
        }
    }


    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
