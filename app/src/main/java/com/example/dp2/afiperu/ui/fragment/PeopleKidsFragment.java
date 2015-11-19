package com.example.dp2.afiperu.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerKidComponent;
import com.example.dp2.afiperu.module.KidModule;
import com.example.dp2.afiperu.presenter.KidPresenter;
import com.example.dp2.afiperu.syncmodel.SyncKid;
import com.example.dp2.afiperu.ui.adapter.PeopleKidsAdapter;
import com.example.dp2.afiperu.ui.viewmodel.KidView;
import com.example.dp2.afiperu.util.NetworkManager;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nevermade on 02/10/2015.
 */
public class PeopleKidsFragment extends BaseFragment implements KidView{

    @Inject
    KidPresenter presenter;
    @Inject
    PeopleKidsAdapter adapter;

    public PeopleKidsFragment() {super(); }


    @Override
    public int getLayout() {
        return R.layout.people_kids;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        ListView peopleKidsList = (ListView)rootView.findViewById(R.id.people_kids_list);
        peopleKidsList.setAdapter(adapter);
        peopleKidsList.setEmptyView(rootView.findViewById(R.id.empty_people_kids_list));

        if(NetworkManager.isNetworkConnected(getContext())){
            rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }
        presenter.getAllKids(getContext());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerKidComponent.builder()
                .afiAppComponent(appComponent)
                .kidModule(new KidModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showKids(List<SyncKid> kids) {
        adapter.update(kids);
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(){
        if(getView() != null) {
            getView().findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onSearch(String query){
        //ACÁ HAZ TU BÚSQUEDA SIMPLE
        if (query.contentEquals("")) {
            presenter.getAllKids(getContext());
        }
        else
        presenter.queryKids(getContext(),query);
        int a = 0;
    }
    @Override
    public void onCloseSearch(){
        //ACÁ HAZ TU BÚSQUEDA SIMPLE

        presenter.getAllKids(getContext());

        int a = 0;
    }



    @Override
    public void onKidAdvancedSearch(String name, String edadini, String edadfin, String genero){
        //ACÁ HAZ TU BÚSQUEDA SIMPLE
        presenter.queryAdvancedKids(getContext(),name,edadini,edadfin,genero);

        int a = 0;
    }

}
