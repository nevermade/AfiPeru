package com.afiperu.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.afiperu.AfiAppComponent;
import com.afiperu.R;
import com.afiperu.common.BaseFragment;
import com.afiperu.common.BasePresenter;

/**
 * Created by Fernando on 05/10/2015.
 */
public class NewsTabFragment extends BaseFragment {

    private final BaseFragment[] tabs = new BaseFragment[2];

    public NewsTabFragment(){
        super();
    }

    public boolean showingAll(){
        final ViewPager pager = (ViewPager)getView().findViewById(R.id.pager);
        return pager.getCurrentItem() == 0;
    }

    @Override
    public int getLayout() {
        return R.layout.view_pager;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        tabs[0] = new NewsFragment();
        tabs[1] = new FavoriteNewsFragment();

        TabLayout tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_favorite));
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
                int pos = tab.getPosition();
                if (pos == 1) {
                    ((FavoriteNewsFragment) tabs[1]).updateFavorites();
                }
                pager.setCurrentItem(pos, true);
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
    @Override
    public void onSearch(String query){
        for(int i=0; i<tabs.length; i++){
            tabs[0].onSearch(query);
            tabs[1].onSearch(query);
        }
    }


    @Override
    public void onCloseSearch(){
        for(int i=0; i <tabs.length; i++){
            tabs[0].onCloseSearch();
            tabs[1].onCloseSearch();
        }
    }

}
