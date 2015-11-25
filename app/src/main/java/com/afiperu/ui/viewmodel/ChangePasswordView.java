package com.afiperu.ui.viewmodel;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface ChangePasswordView {
/*
    void setupList();
*/
    void setupAdapter();
/*
    void displayFoundBlogs(ArrayList<Blog> blogs);
    void displayEmptyList();
    */
    void displayPasswordChangedSuccess();

    void displayPasswordChangedError(String message);
    void displayPasswordChangedFailure();
}
