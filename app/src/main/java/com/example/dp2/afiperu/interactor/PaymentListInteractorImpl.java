package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.domain.Payment;
import com.example.dp2.afiperu.util.AppEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Nevermade on 17/10/2015.
 */
public class PaymentListInteractorImpl implements PaymentListInteractor {
    public PaymentListInteractorImpl() {
    }

    @Override
    public ArrayList<Payment> getAllPayments() throws ParseException {
        ArrayList<Payment> payments= new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        payments.add(new Payment(AppEnum.EnumPaymentState.EXPIRATED.ordinal(),120.00,dateFormat.parse("14/08/2015")));
        payments.add(new Payment(AppEnum.EnumPaymentState.EXPIRATED.ordinal(),130.00,dateFormat.parse("01/09/2015")));
        payments.add(new Payment(AppEnum.EnumPaymentState.PENDING.ordinal(),220.00,dateFormat.parse("21/10/2015")));
        payments.add(new Payment(AppEnum.EnumPaymentState.PAYED.ordinal(),185.00,dateFormat.parse("13/06/2015")));
        payments.add(new Payment(AppEnum.EnumPaymentState.PAYED.ordinal(),450.00,dateFormat.parse("08/07/2015")));

        return payments;
    }
}
