package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;

/**
 * Created by Fernando on 05/10/2015.
 */
public class PeopleTabFragment extends BaseFragment {

    private final BaseFragment[] tabs = new BaseFragment[2];

    public PeopleTabFragment(){
        super();
    }

    public boolean showingUsers(){
        final ViewPager pager = (ViewPager)getView().findViewById(R.id.pager);
        return pager.getCurrentItem() == 0;
    }

    @Override
    public int getLayout() {
        return R.layout.view_pager;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        tabs[0] = new UsersFragment();
        tabs[1] = new PeopleKidsFragment();

        TabLayout tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_users));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_kids));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pager = (ViewPager)rootView.findViewById(R.id.pager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return tabs[position];
            }
            @Override
            public int getCount() {
                return tabs.length;
            }
        };
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        
    }

    public UsersFragment getUsersFragment(){
        return (UsersFragment)tabs[0];
    }

    @Override
    public void onSearch(String query){
        if(showingUsers()){
            tabs[0].onSearch(query);
        }else{
            tabs[1].onSearch(query);
        }
    }


    @Override
    public void onCloseSearch(){
        if(showingUsers()){
            tabs[0].onCloseSearch();
        }else{
            tabs[1].onCloseSearch();
        }
    }


    @Override
    public void onUserAdvancedSearch(String name, String nrodoc, String perfil){
        if(showingUsers()){
            tabs[0].onUserAdvancedSearch(name, nrodoc, perfil);
        }else{
            //tabs[1].onKidAdvancedSearch(name, nrodoc, perfil);
        }
    }
    @Override
    public void onKidAdvancedSearch(String name, String edadini, String edadfin,String genero){
        if(showingUsers()){
            //tabs[0].onUserAdvancedSearch(name,nrodoc,perfil);
        }else{
            tabs[1].onKidAdvancedSearch(name, edadini,edadfin,genero);
        }
    }


}
