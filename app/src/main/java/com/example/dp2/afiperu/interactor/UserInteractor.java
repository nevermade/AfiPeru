package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Blog;
import com.example.dp2.afiperu.domain.User;
import com.example.dp2.afiperu.presenter.UserPresenter;
import com.example.dp2.afiperu.rest.model.LocationsBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nevermade on 12/10/2015.
 */
public interface UserInteractor {

    ArrayList<User> getAllUsers(UserPresenter userPresenter);

    LocationsBody getLocations(UserPresenter userPresenter);
}
