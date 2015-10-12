package com.example.dp2.afiperu;

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
import android.support.v7.app.AppCompatActivity;
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

import com.example.dp2.afiperu.dialogs.CommentSearchDialog;
import com.example.dp2.afiperu.dialogs.KidSearchDialog;
import com.example.dp2.afiperu.dialogs.UserSearchDialog;
import com.example.dp2.afiperu.fragments.BaseFragment;
import com.example.dp2.afiperu.fragments.BlogTabFragment;
import com.example.dp2.afiperu.fragments.BlogsFragment;
import com.example.dp2.afiperu.fragments.FavoriteBlogFragment;
import com.example.dp2.afiperu.fragments.FavoriteNewsFragment;
import com.example.dp2.afiperu.fragments.MapEditFragment;
import com.example.dp2.afiperu.fragments.NewsTabFragment;
import com.example.dp2.afiperu.fragments.PeopleKidsFragment;
import com.example.dp2.afiperu.fragments.UploadPhotosFragment;
import com.example.dp2.afiperu.fragments.UsersFragment;
import com.example.dp2.afiperu.lists.BlogsItem;
import com.example.dp2.afiperu.lists.DocumentsItem;
import com.example.dp2.afiperu.lists.DrawerItem;
import com.example.dp2.afiperu.lists.DrawerAdapter;
import com.example.dp2.afiperu.lists.MarkerInfo;
import com.example.dp2.afiperu.lists.NewsItem;
import com.example.dp2.afiperu.lists.PeopleKidsItem;
import com.example.dp2.afiperu.lists.SessionItem;
import com.example.dp2.afiperu.fragments.DocumentsFragment;
import com.example.dp2.afiperu.fragments.LoginFragment;
import com.example.dp2.afiperu.fragments.NewsFragment;
import com.example.dp2.afiperu.fragments.SessionFragment;
import com.example.dp2.afiperu.lists.UsersItem;

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
public class DetailActivity extends AppCompatActivity {

    public static final int FRAGMENT_NOTICIAS = 0;
    public static final int FRAGMENT_BLOG = 1;
    //public static final int FRAGMENT_PERSONAS = 2;
    public static final int FRAGMENT_USUARIOS = 2;
    public static final int FRAGMENT_NIÑOS = 3;
    public static final int FRAGMENT_SESIONES = 4;
    public static final int FRAGMENT_DOCUMENTOS = 5;
    public static final int FRAGMENT_SUBIR_FOTOS = 6;
    public static final int FRAGMENT_PAGOS = 7;

    public static final int FRAGMENT_LOGIN = 8;
    public static final int FRAGMENT_DETALLE_NOTICIAS = 9;
    public static final int FRAGMENT_ASISTENCIA = 10;
    public static final int FRAGMENT_COMENTARIOS = 11;
    public static final int FRAGMENT_DETALLE_BLOG = 12;
    public static final int FRAGMENT_LISTA_COMENTARIOS = 13;
    public static final int FRAGMENT_MAPA = 14;
    public static final int FRAGMENT_MAPA_EDITABLE = 15;

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
            //case FRAGMENT_PERSONAS: id = R.string.menu_personas; break;
            case FRAGMENT_USUARIOS: id = R.string.menu_usuarios; break;
            case FRAGMENT_NIÑOS: id = R.string.menu_ninos; break;
            case FRAGMENT_SESIONES: id = R.string.menu_sesiones; break;
            case FRAGMENT_DOCUMENTOS: id = R.string.menu_documentos; break;
            case FRAGMENT_SUBIR_FOTOS: id = R.string.menu_subir_fotos; break;
            case FRAGMENT_BLOG: id = R.string.menu_blog; break;
            case FRAGMENT_PAGOS: id = R.string.menu_pagos; break;

            case FRAGMENT_LOGIN: id = R.string.app_name; break;
            case FRAGMENT_DETALLE_NOTICIAS: id = R.string.menu_noticias; break;
            case FRAGMENT_ASISTENCIA: id = R.string.title_asistencia; break;
            case FRAGMENT_COMENTARIOS: id = R.string.title_comentarios; break;
            case FRAGMENT_DETALLE_BLOG: id = R.string.menu_blog; break;
            case FRAGMENT_MAPA:
            case FRAGMENT_MAPA_EDITABLE:
                id = R.string.app_name; break;
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
            case FRAGMENT_NIÑOS: return R.menu.people_kids_menu_toolbar;
            case FRAGMENT_USUARIOS: return R.menu.users_menu_toolbar;
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

        ArrayList<DrawerItem> list = new ArrayList<>();
        list.add(new DrawerItem(getTitle(FRAGMENT_NOTICIAS), R.drawable.ic_drawer_news));
        list.add(new DrawerItem(getTitle(FRAGMENT_BLOG), R.drawable.ic_drawer_blog));
        //list.add(new DrawerItem(getTitle(FRAGMENT_PERSONAS), R.drawable.ic_drawer_people));
        list.add(new DrawerItem(getTitle(FRAGMENT_USUARIOS), R.drawable.ic_drawer_people));
        list.add(new DrawerItem(getTitle(FRAGMENT_NIÑOS), R.drawable.ic_drawer_people));
        list.add(new DrawerItem(getTitle(FRAGMENT_SESIONES), R.drawable.ic_drawer_sessions));
        list.add(new DrawerItem(getTitle(FRAGMENT_DOCUMENTOS), R.drawable.ic_drawer_docs));
        list.add(new DrawerItem(getTitle(FRAGMENT_SUBIR_FOTOS), R.drawable.ic_drawer_upload_photos));
        list.add(new DrawerItem(getTitle(FRAGMENT_PAGOS), R.drawable.ic_drawer_payments));
        list.add(new DrawerItem(getTitle(FRAGMENT_LOGIN), R.drawable.ic_drawer_news)); //Temporal
        list.add(new DrawerItem(getResources().getString(R.string.menu_postular), R.drawable.ic_drawer_postulate));

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
                case R.menu.users_menu_toolbar:
                    menuItem = R.id.users_menu_search;
                    dialogTag = DIALOG_TAG_SEARCH_USERS;
                    dialogFragmentClass = UserSearchDialog.class;
                    break;
                case R.menu.people_kids_menu_toolbar:
                    menuItem = R.id.people_kids_menu_search;
                    dialogTag = DIALOG_TAG_SEARCH_KIDS;
                    dialogFragmentClass = KidSearchDialog.class;
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

    private void selectItem(int position){
        if(position == FRAGMENT_LOGIN + 1){
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
            return;
        }

        Bundle args = new Bundle();
        Fragment fragment;
        switch(position){
            default:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_LOGIN);
                fragment = new LoginFragment();
                break;
            case FRAGMENT_NOTICIAS:
                ArrayList<NewsItem> news = new ArrayList<>();
                Calendar calendar = new GregorianCalendar(2015, 8, 25, 0, 31);
                news.add(new NewsItem(
                        1001,
                        "https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xat1/v/t1.0-9/13779_10153228555392486_8679903887061635913_n.jpg?oh=00b977b776d2b46e53c88f229bc38250&oe=5668F7B7&__gda__=1453937731_0addbe5c62688f1ca9aa12cef23593eb",
                        "Paseo pinoteco al parque de las leyendas",
                        2001,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                        "Manuel", calendar.getTime().getTime(), true));
                calendar = new GregorianCalendar(2015, 8, 24, 23, 40);
                news.add(new NewsItem(
                        1002,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/11024597_10153107115432486_3774679476823351402_n.jpg?oh=2e13703d43f85c64bde46ce3b0ff4738&oe=5697777F",
                        "Voluntarios accederan a puntajes para becas y viviendas",
                        2001,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                        "Gabriela", calendar.getTime().getTime(), false));
                calendar = new GregorianCalendar(2015, 8, 21, 15, 22);
                news.add(new NewsItem(
                        1003,
                        "https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-9/11150479_10153215037187486_66126204997766216_n.jpg?oh=efc6181115bfcd36dd12eeda1f2be26d&oe=56A16C75&__gda__=1449207363_cde5ddb130a171ea4e9fc3d09933356b",
                        "Recurso multimedia que utiliza el cuento como motivación inicial, dirigido a niños",
                        2001,
                        "https://scontent-mia1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/10392539_10153410963797486_885580920541938912_n.png?oh=f05a7187f83b64568b81f9a023552651&oe=56A5DF4D",
                        "Yuri", calendar.getTime().getTime(), false));
                args.putSerializable(NewsFragment.NEWS_ARG, news);
                ArrayList<NewsItem> favoriteNews = new ArrayList<>();
                favoriteNews.add(news.get(0));
                args.putSerializable(FavoriteNewsFragment.FAVORITE_NEWS_ARG, favoriteNews);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_NOTICIAS);
                fragment = new NewsTabFragment();
                break;
            case FRAGMENT_BLOG:
                ArrayList<BlogsItem> blogs= new ArrayList<>();
                calendar=new GregorianCalendar(2015,8,22,14,25);
                blogs.add(new BlogsItem("Titulo 1","Daekef Abarca",calendar.getTime().getTime(),false));
                calendar=new GregorianCalendar(2015,7,24,48,27);
                blogs.add(new BlogsItem("Titulo 2","Fernando Banda",calendar.getTime().getTime(),true));
                calendar=new GregorianCalendar(2015,6,15,9,45);
                blogs.add(new BlogsItem("Titulo 3","Luis Barcena",calendar.getTime().getTime(),false));
                Collections.sort(blogs);
                args.putSerializable(BlogsFragment.BLOG_ARG, blogs);
                ArrayList<BlogsItem> favoriteBlogs = new ArrayList<>();
                favoriteBlogs.add(blogs.get(1));
                args.putSerializable(FavoriteBlogFragment.FAVORITE_BLOG_ARG, favoriteBlogs);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_BLOG);
                fragment=new BlogTabFragment();
                break;
            case FRAGMENT_USUARIOS:
                ArrayList<UsersItem> users= new ArrayList<>();
                users.add(new UsersItem("dabarca","20101147","Daekef","Abarca","Cusimayta",3.5));
                users.add(new UsersItem("fbanda","20107845","Fernando","Banda","Cardenas",4.8));
                users.add(new UsersItem("lbarcena","20101019","Luis","Barcena","Navarro",1.0));
                Collections.sort(users);
                args.putSerializable(UsersFragment.USER_ARG, users);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_USUARIOS);
                fragment=new UsersFragment();
                break;
            case FRAGMENT_NIÑOS:
                ArrayList<PeopleKidsItem> kids= new ArrayList<>();
                kids.add(new PeopleKidsItem(false,12,"Perales","Perez","Paola"));
                kids.add(new PeopleKidsItem(true,10,"Perales","Perez","Juan"));
                kids.add(new PeopleKidsItem(false,11,"Perales","Perez","Rosario"));
                Collections.sort(kids);
                args.putSerializable(PeopleKidsFragment.PEOPLE_KIDS_ARG, kids);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_NIÑOS);
                fragment = new PeopleKidsFragment();
                break;
            case FRAGMENT_SESIONES:
                ArrayList<MarkerInfo> markers = new ArrayList<>();
                markers.add(new MarkerInfo(-12.0731492, -77.0819083, MarkerInfo.MARKER_KIND_SESSION_ADDRESS, null));
                markers.add(new MarkerInfo(-12.0767993, -77.0811531, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
                markers.add(new MarkerInfo(-12.0587955, -77.0815501, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));
                markers.add(new MarkerInfo(-12.067451, -77.061305, MarkerInfo.MARKER_KIND_SESSION_REUNION, null));

                ArrayList<SessionItem> sessions = new ArrayList<>();
                calendar = new GregorianCalendar(2015, 8, 16, 16, 0);
                sessions.add(new SessionItem("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 0);
                sessions.add(new SessionItem("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 0);
                sessions.add(new SessionItem("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 0);
                sessions.add(new SessionItem("Cerro el Pino", calendar.getTime().getTime(), markers));
                calendar = new GregorianCalendar(2015, 8, 16, 16, 0);
                sessions.add(new SessionItem("Cerro el Pino", calendar.getTime().getTime(), markers));
                Collections.sort(sessions);
                args.putSerializable(SessionFragment.SESSION_ARG, sessions);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SESIONES);
                fragment = new SessionFragment();
                break;
            case FRAGMENT_DOCUMENTOS:
                ArrayList<DocumentsItem> documents = new ArrayList<>();
                calendar = new GregorianCalendar(2015, 8, 22, 15, 21);
                documents.add(new DocumentsItem("Guía de actividades 27/09.pdf", R.drawable.ic_docs_pdf, "254 KB", calendar.getTime().getTime()));
                calendar = new GregorianCalendar(2015, 8, 21, 12, 5);
                documents.add(new DocumentsItem("Materiales para 27/09.xlsx", R.drawable.ic_docs_xls, "1.2 MB", calendar.getTime().getTime()));
                calendar = new GregorianCalendar(2015, 8, 18, 13, 14);
                documents.add(new DocumentsItem("Documento sin ícono", R.drawable.ic_docs_generic, "13 KB", calendar.getTime().getTime()));
                calendar = new GregorianCalendar(2015, 8, 22, 15, 24);
                documents.add(new DocumentsItem("Material extra 27/09.docx", R.drawable.ic_docs_doc, "126 KB", calendar.getTime().getTime()));
                Collections.sort(documents);
                args.putSerializable(DocumentsFragment.DOCUMENTS_ARG, documents);
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_DOCUMENTOS);
                fragment = new DocumentsFragment();
                break;
            case FRAGMENT_SUBIR_FOTOS:
                args.putInt(BaseFragment.FRAGMENT_ID_ARG, FRAGMENT_SUBIR_FOTOS);
                fragment = new UploadPhotosFragment();
                break;
        }
        fragment.setArguments(args);
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
        selectedLayout = position;
        changeFragment(fragment, getTitle(selectedLayout), getMenu(selectedLayout));
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
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
        }catch(IOException e){
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
