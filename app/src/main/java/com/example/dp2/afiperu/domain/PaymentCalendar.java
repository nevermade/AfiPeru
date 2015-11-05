package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nevermade on 01/10/2015.
 */
public class PaymentCalendar implements Serializable, Comparable<PaymentCalendar> {
    @SerializedName("fee_id")
    @Expose
    private Integer feeId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("due_date")
    @Expose
    private Integer dueDate;
    @SerializedName("fee_number")
    @Expose
    private Integer feeNumber;

    /**
     *
     * @return
     * The feeId
     */
    public Integer getFeeId() {
        return feeId;
    }

    /**
     *
     * @param feeId
     * The fee_id
     */
    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    /**
     *
     * @return
     * The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The dueDate
     */
    public Integer getDueDate() {
        return dueDate;
    }

    /**
     *
     * @param dueDate
     * The due_date
     */
    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }

    /**
     *
     * @return
     * The feeNumber
     */
    public Integer getFeeNumber() {
        return feeNumber;
    }

    /**
     *
     * @param feeNumber
     * The fee_number
     */
    public void setFeeNumber(Integer feeNumber) {
        this.feeNumber = feeNumber;
    }

    @Override
    public int compareTo(PaymentCalendar o2) {
        return dueDate.compareTo(o2.dueDate);
    }
}