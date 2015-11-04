package com.example.dp2.afiperu.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.dp2.afiperu.AfiAppComponent;
import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.common.BaseFragment;
import com.example.dp2.afiperu.common.BasePresenter;
import com.example.dp2.afiperu.component.DaggerPaymentListComponent;
import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.module.PaymentListModule;
import com.example.dp2.afiperu.presenter.PaymentListPresenter;
import com.example.dp2.afiperu.ui.adapter.PaymentListAdapter;
import com.example.dp2.afiperu.ui.viewmodel.PaymentListView;

import java.util.ArrayList;

import javax.inject.Inject;

public class PaymentListFragment extends BaseFragment implements PaymentListView {

    @Inject
    PaymentListPresenter paymentListPresenter;
    @Inject
    PaymentListAdapter paymentListAdapter;

    @Override
    public int getLayout() {
        return R.layout.payments;
    }

    @Override
    public void prepareView(View rootView, Bundle args, Bundle savedInstanceState){
        /*blogSearchPresenter.getAllArtists();
        //BlogsAdapter adapter = new BlogsAdapter(getContext(), this, blogs);

        blogsList = (ListView)rootView.findViewById(R.id.blogs_list);
        blogsList.setAdapter(blogSearchAdapter);
        blogsList.setEmptyView(rootView.findViewById(R.id.empty_blogs_list));

        isFavorite = new boolean[blogSearchAdapter.getCount()];
        for(int i=0; i<isFavorite.length; i++){
            isFavorite[i] = blogSearchAdapter.getItem(i).isFavorite();
        }*/
        paymentListPresenter.getAllPayments();
        ListView paymentList=(ListView)rootView.findViewById(R.id.payment_list);
        paymentList.setAdapter(paymentListAdapter);
        paymentList.setEmptyView(rootView.findViewById(R.id.empty_payments_list));


    }

    @Override
    public void setUpComponent(AfiAppComponent appComponent) {
        DaggerPaymentListComponent.builder()
                .afiAppComponent(appComponent)
                .paymentListModule(new PaymentListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    public void displayPayments(ArrayList<Payment> payments){

        paymentListAdapter.update(payments);

    }


    public void displayNoPaymentsFound(){

    }


}
