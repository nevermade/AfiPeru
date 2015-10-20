package com.example.dp2.afiperu.ui.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseActivity;
import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.domain.Drawer;
import com.example.dp2.afiperu.domain.MarkerInfo;
import com.example.dp2.afiperu.ui.dialogs.CommentSearchDialog;
import com.example.dp2.afiperu.ui.dialogs.KidSearchDialog;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.ui.dialogs.UserSearchDialog;
import com.example.dp2.afiperu.ui.dialogs.recoverPasswordDialog;
import com.example.dp2.afiperu.ui.fragment.BlogSearchFragment;
import com.example.dp2.afiperu.ui.fragment.BlogTabFragment;
import com.example.dp2.afiperu.ui.fragment.FavoriteBlogFragment;
import com.example.dp2.afiperu.ui.fragment.FavoriteNewsFragment;
import com.example.dp2.afiperu.ui.fragment.MapEditFragment;
import com.example.dp2.afiperu.ui.fragment.NewsTabFragment;
import com.example.dp2.afiperu.ui.fragment.PaymentListFragment;
import com.example.dp2.afiperu.ui.fragment.PeopleKidsFragment;
import com.example.dp2.afiperu.ui.fragment.PeopleTabFragment;
import com.example.dp2.afiperu.ui.fragment.SettingsFragment;
import com.example.dp2.afiperu.ui.fragment.UploadPhotosFragment;
import com.example.dp2.afiperu.ui.fragment.UsersFragment;
import com.example.dp2.afiperu.domain.Documents;
import com.example.dp2.afiperu.ui.adapter.DrawerAdapter;
import com.example.dp2.afiperu.domain.News;
import com.example.dp2.afiperu.domain.PeopleKids;
import com.example.dp2.afiperu.domain.Session;
import com.example.dp2.afiperu.ui.fragment.DocumentsFragment;
import com.example.dp2.afiperu.ui.fragment.LoginFragment;
import com.example.dp2.afiperu.ui.fragment.NewsFragment;
import com.example.dp2.afiperu.ui.fragment.SessionFragment;
import com.example.dp2.afiperu.domain.Users;

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
import java.util.List;

/**
 * Created by Fernando on 16/09/2015.
 */
public class DetailActivity extends BaseActivity {

    public static final int FRAGMENT_NOTICIAS = 0;
    public static final int FRAGMENT_BLOG = 1;
    public static final int FRAGMENT_PERSONAS = 2;
    public static final int FRAGMENT_SESIONES = 4;
    public static final int FRAGMENT_DOCUMENTOS = 5;
    public static final int FRAGMENT_SUBIR_FOTOS = 6;
    public static final int FRAGMENT_PAGOS = 7;
    public static final int FRAGMENT_DONACIONES = 8;
    public static final int FRAGMENT_CONFIGURACIÓN = 9;

    public static final int FRAGMENT_DETALLE_NOTICIAS = 10;
    public static final int FRAGMENT_ASISTENCIA = 11;
    public static final int FRAGMENT_COMENTARIOS = 12;
    public static final int FRAGMENT_DETALLE_BLOG = 13;
    public static final int FRAGMENT_LISTA_COMENTARIOS = 14;
    public static final int FRAGMENT_MAPA = 15;
    public static final int FRAGMENT_MAPA_EDITABLE = 16;
    public static final int FRAGMENT_REGISTRAR_PAGO = 17;
    public static final int FRAGMENT_LOGIN = 99;

    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    int selectedLayout;
    int toolbarMenu;

    int previousBackStackCount;

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
            case FRAGMENT_CONFIGURACIÓN: id = R.string.menu_configuracion; break;

            case FRAGMENT_LOGIN: id = R.string.app_name; break;
            case FRAGMENT_DETALLE_NOTICIAS: id = R.string.menu_noticias; break;
            case FRAGMENT_ASISTENCIA: id = R.string.title_asistencia; break;
            case FRAGMENT_COMENTARIOS: id = R.string.title_comentarios; break;
            case FRAGMENT_DETALLE_BLOG: id = R.string.menu_blog; break;
            case FRAGMENT_MAPA:
            case FRAGMENT_MAPA_EDITABLE: id = R.string.app_name; break;
            case FRAGMENT_REGISTRAR_PAGO: id = R.string.menu_pagos; break;
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
                setTitle(getTitle(fragment.getFragmentId()));
                toolbarMenu = getMenu(fragment.getFragmentId());
                invalidateOptionsMenu();
            }
            previousBackStackCount = backStackEntryCount;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);

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
        list.add(new Drawer(FRAGMENT_CONFIGURACIÓN, getTitle(FRAGMENT_CONFIGURACIÓN), R.drawable.ic_settings));
        list.add(new Drawer(FRAGMENT_LOGIN, getTitle(FRAGMENT_LOGIN), R.drawable.ic_drawer_news)); //Temporal

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new DrawerAdapter(this, list));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportFragmentManager().addOnBackStackChangedListener(backStackListener);

        selectItem(FRAGMENT_LOGIN);
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
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        boolean noStack = backStackEntryCount == 0;
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            if(noStack){
                finish();
            }else{
                goBack(); //Close drawer
            }
        }else{
            if(noStack){
                mDrawerLayout.openDrawer(GravityCompat.START);
                //Open drawer
            }else if(getTopFragment().tryBack()) {
                goBack();
            }
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
                    if(((PeopleTabFragment)getTopFragment()).showingUsers()){
                        dialogTag = DIALOG_TAG_SEARCH_USERS;
                        dialogFragmentClass = UserSearchDialog.class;
                    }else{
                        dialogTag = DIALOG_TAG_SEARCH_KIDS;
                        dialogFragmentClass = KidSearchDialog.class;
                    }
                    break;
                case R.menu.comments_menu_toolbar:
                    menuItem = R.id.comments_menu_search;
                    dialogTag = DIALOG_TAG_SEARCH_COMMENTS;
                    dialogFragmentClass = CommentSearchDialog.class;
                    break;
                case R.menu.map_menu_toolbar:
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
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

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

    private void tryPostulate(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){

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

    private void selectItem(int fragmentId){
        Bundle args = new Bundle();
        Fragment fragment;
        switch(fragmentId){
            default:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_LOGIN);
                fragment = new LoginFragment();
                break;
            case FRAGMENT_NOTICIAS:
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
                ArrayList<Users> users= new ArrayList<>();
                users.add(new Users("dabarca","20101147","Daekef","Abarca","Cusimayta",3.5));
                users.add(new Users("fbanda","20107845","Fernando","Banda","Cardenas",4.8));
                users.add(new Users("lbarcena","20101019","Luis","Barcena","Navarro",1.0));
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
                ArrayList<MarkerInfo> markers = new ArrayList<>();
                markers.add(new MarkerInfo(-12.0731492, -77.0819083, MarkerInfo.MARKER_KIND_SESSION_ADDRESS, null));
                markers.add(new MarkerInfo(-12.0767993, -77.0811531, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
                markers.add(new MarkerInfo(-12.0587955, -77.0815501, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
                markers.add(new MarkerInfo(-12.067451, -77.061305, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
                
                ArrayList<Session> sessions = new ArrayList<>();
                calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
                sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
                sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
                sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
                sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 00);
                sessions.add(new Session("Cerro el Pino", calendar.getTime().getTime(), markers));
                Collections.sort(sessions);
                args.putSerializable(SessionFragment.SESSION_ARG, sessions);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SESIONES);
                fragment = new SessionFragment();
                break;
            case FRAGMENT_DOCUMENTOS:
                ArrayList<Documents> documents = new ArrayList<>();
                calendar = new GregorianCalendar(2015, 8, 22, 15, 21);
                documents.add(new Documents("Guía de actividades 27/09.pdf", R.drawable.ic_docs_pdf, "254 KB", calendar.getTime().getTime()));
                calendar = new GregorianCalendar(2015, 8, 21, 12, 05);
                documents.add(new Documents("Materiales para 27/09.xlsx", R.drawable.ic_docs_xls, "1.2 MB", calendar.getTime().getTime()));
                calendar = new GregorianCalendar(2015, 8, 18, 13, 14);
                documents.add(new Documents("Documento sin ícono", R.drawable.ic_docs_generic, "13 KB", calendar.getTime().getTime()));
                calendar = new GregorianCalendar(2015, 8, 22, 15, 24);
                documents.add(new Documents("Material extra 27/09.docx", R.drawable.ic_docs_doc, "126 KB", calendar.getTime().getTime()));
                Collections.sort(documents);

                args.putSerializable(DocumentsFragment.DOCUMENTS_ARG, documents);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_DOCUMENTOS);
                fragment = new DocumentsFragment();
                break;
            case FRAGMENT_PAGOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG,FRAGMENT_PAGOS);
                fragment=new PaymentListFragment();
                break;
            case FRAGMENT_SUBIR_FOTOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SUBIR_FOTOS);
                fragment = new UploadPhotosFragment();
                break;
            /*case FRAGMENT_PAGOS:
                break;*/
            /*case FRAGMENT_DONACIONES:
                break;*/
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
        //There isn't dependencies
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
    private class RecoverPasswordClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            //selectItem(2);
            /*
            Bundle args = new Bundle();
            Fragment fragment = new ChangePasswordFragment();
            args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SUBIR_FOTOS);
            fragment = new ChangePasswordFragment();
            fragment.setArguments(args);
            changeFragment(fragment, "Recuperar contraseña", R.menu.users_menu_toolbar);

            */
            //DialogFragment newFragment = new KidCommentDialog();
            DialogFragment dialog = new recoverPasswordDialog();
            dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
            //getFragment().showPopup(newFragment, DetailActivity.DIALOG_TAG_DETAIL_COMMENT);



            /*
            Bundle args = new Bundle();
            ArrayList<DocumentsItem> documents = new ArrayList<>();
            Fragment fragment = new ChangePasswordFragment();
            args.putSerializable(ChangePasswordFragment.DOCUMENTS_ARG, documents);
            */
            /*
            Fragment fragment = new DocumentsFragment();
            args.putSerializable(DocumentsFragment.DOCUMENTS_ARG, null);
            */
            /*
            args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_DOCUMENTOS);
            fragment.setArguments(args);
            changeFragment(fragment, "Recuperar contraseña", R.menu.users_menu_toolbar);
            */

        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Button recoverpass;
        recoverpass = (Button)findViewById(R.id.button_recoverpass);
        recoverpass.setOnClickListener(new RecoverPasswordClickListener());
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public String getExternalImagesDir(){
        return "/" + getResources().getString(R.string.app_name) + "/Images";
    }

    public void setImage(ImageView v, String URL, String title){
        String path = Environment.getExternalStorageDirectory() + getExternalImagesDir() + "/" + title;
        File file = new File(path);
        if(file.exists()){
            v.setImageURI(Uri.parse(path));
            Log.d("imgs", "Uri set to ImageView already there: " + path);
        }else{
            AsyncTask task = new DetailActivity.DownloadImageTask(v);
            task.execute(this, URL, path);
            Log.d("imgs", "Task created for:" + title);
        }
    }

    public static class DownloadImageTask extends AsyncTask<Object, Void, String>{
        ImageView view;

        public DownloadImageTask(ImageView view){
            this.view = view;
        }

        @Override
        protected String doInBackground(Object... args){
            DetailActivity activity = (DetailActivity)args[0];
            String url = (String)args[1];
            String path = (String)args[2];

            return activity.downloadImage(url, path);
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

    public String downloadImage(String strUrl, String path){
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
        Log.d("search", "ON NEW INTENT CALLED");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(intent.getAction().equals(Intent.ACTION_SEARCH)){
            String query = intent.getStringExtra(SearchManager.QUERY);
            getTopFragment().onSearch(query);
        }
    }

}