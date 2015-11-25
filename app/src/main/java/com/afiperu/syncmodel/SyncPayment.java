package com.afiperu.syncmodel;

import com.afiperu.domain.Payment;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Fernando on 11/11/2015.
 */
public class SyncPayment extends SugarRecord<SyncPayment> implements Serializable, Comparable<SyncPayment> {

    private Integer feeId;
    private Integer status;
    private Long dueDate;
    private Integer amount;

    public SyncPayment(){}
    private SyncPayment(Integer feeId, Integer status, Long dueDate, Integer amount) {
        this.feeId = feeId;
        this.status = status;
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public Integer getFeeId() {
        return feeId;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public static SyncPayment fromPayment(Payment payment){
        return new SyncPayment(payment.getFeeId(), payment.getStatus(), payment.getDueDate()*1000, payment.getAmount());
    }

    public int compareTo(SyncPayment o2){
        return o2.dueDate.compareTo(dueDate);
    }
}
