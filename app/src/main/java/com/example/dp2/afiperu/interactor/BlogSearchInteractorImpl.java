package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Blog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by Nevermade on 12/10/2015.
 */
public class BlogSearchInteractorImpl implements BlogSearchInteractor {

    public BlogSearchInteractorImpl(){}
    @Override
    public ArrayList<Blog> getAllBlogs() {
        Calendar calendar= new GregorianCalendar();
        ArrayList<Blog> blogs= new ArrayList<>();
        calendar=new GregorianCalendar(2015,8,22,14,25);
        blogs.add(new Blog("Titulo 1","Daekef Abarca",calendar.getTime().getTime(),false));
        calendar=new GregorianCalendar(2015,7,24,48,27);
        blogs.add(new Blog("Titulo 2","Fernando Banda",calendar.getTime().getTime(),true));
        calendar=new GregorianCalendar(2015,6,15,9,45);
        blogs.add(new Blog("Titulo 3","Luis Barcena",calendar.getTime().getTime(),false));
        Collections.sort(blogs);
        return blogs;
    }
}
