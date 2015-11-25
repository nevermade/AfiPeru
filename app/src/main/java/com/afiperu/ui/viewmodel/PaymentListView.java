package com.afiperu.ui.viewmodel;

import com.afiperu.syncmodel.SyncPayment;

import java.util.List;

/**
 * Created by Nevermade on 17/10/2015.
 */
public interface PaymentListView {
    void displayPayments(List<SyncPayment> payments);
    void displayNoPaymentsFound();
    void displayPaymentSuccess();
    void displayPaymentError();
    void displayPaymentFailure();
}
