package com.afiperu.domain;

import java.util.Date;

/**
 * Created by DABARCA on 19/10/2015.
 */
public class Voucher {
    double mount;
    Date paymentDate;
    String code;

    public Voucher(double mount, String code, Date paymentDate) {
        this.mount = mount;
        this.code = code;
        this.paymentDate = paymentDate;
    }

    public double getMount() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount = mount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
