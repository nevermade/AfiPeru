package com.example.dp2.afiperu.domain;

import java.util.Date;

/**
 * Created by Nevermade on 17/10/2015.
 */
public class Payment {
    int state;
    double mount;
    Date expirationDate;

    public Payment(int state, double mount, Date expirationDate) {
        this.state = state;
        this.mount = mount;
        this.expirationDate = expirationDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getMount() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount = mount;
    }
}
