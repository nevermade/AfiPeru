package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.domain.Blog;

import java.util.ArrayList;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface BlogSearchView {

    void setupList();

    void setupAdapter();

    void displayFoundBlogs(ArrayList<Blog> blogs);
    void displayEmptyList();
}
