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

    private final Fragment[] tabs = new Fragment[2];

    public PeopleTabFragment(){
        super();
    }

    public boolean showingUsers(){
        return true;
    }

    @Override
    public int getLayout() {
        return R.layout.view_pager;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState) {
        Bundle args0 = new Bundle();
        args0.putSerializable(UsersFragment.USER_ARG, args.getSerializable(UsersFragment.USER_ARG));
        tabs[0] = new UsersFragment();
        tabs[0].setArguments(args0);
        Bundle args1 = new Bundle();
        args1.putSerializable(PeopleKidsFragment.PEOPLE_KIDS_ARG, args.getSerializable(PeopleKidsFragment.PEOPLE_KIDS_ARG));
        tabs[1] = new PeopleKidsFragment();
        tabs[1].setArguments(args1);

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

}
