package com.afiperu.interactor;

import android.content.Context;

import com.afiperu.presenter.PaymentDepositPresenter;

/**
 * Created by DABARCA on 19/10/2015.
 */
public interface PaymentDepositInteractor {
    void registerBankPayment(final Context context, final PaymentDepositPresenter presenter, int feeId, String voucherCode, long date, String bank);
}
