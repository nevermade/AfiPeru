package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Payment;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Nevermade on 17/10/2015.
 */
public interface PaymentListInteractor {
    public ArrayList<Payment> getAllPayments() throws ParseException;
}
