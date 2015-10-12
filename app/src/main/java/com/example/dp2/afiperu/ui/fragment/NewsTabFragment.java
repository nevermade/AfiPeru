package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;

/**
 * Created by Fernando on 05/10/2015.
 */
public class NewsTabFragment extends BaseFragment {

    private final Fragment[] tabs = new Fragment[2];

    public NewsTabFragment(){
        super();
    }

    @Override
    public int getLayout() {
        return R.layout.view_pager;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        Bundle args0 = new Bundle();
        args0.putSerializable(NewsFragment.NEWS_ARG, args.getSerializable(NewsFragment.NEWS_ARG));
        tabs[0] = new NewsFragment();
        tabs[0].setArguments(args0);
        Bundle args1 = new Bundle();
        args1.putSerializable(FavoriteNewsFragment.FAVORITE_NEWS_ARG, args.getSerializable(FavoriteNewsFragment.FAVORITE_NEWS_ARG));
        tabs[1] = new FavoriteNewsFragment();
        tabs[1].setArguments(args1);

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

}
