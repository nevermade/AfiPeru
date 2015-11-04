package com.example.dp2.afiperu.ui.viewmodel;

import com.example.dp2.afiperu.domain.Payment;

import java.util.ArrayList;

/**
 * Created by Nevermade on 17/10/2015.
 */
public interface PaymentListView {
    void displayPayments(ArrayList<Payment> payments);
    void showPayments(ArrayList<Payment> payments);
}
