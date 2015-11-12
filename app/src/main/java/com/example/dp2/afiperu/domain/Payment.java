package com.example.dp2.afiperu.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Nevermade on 17/10/2015.
 */
public class Payment {

    @SerializedName("fee_id")
    @Expose
    private Integer feeId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("due_date")
    @Expose
    private Long dueDate;
    @SerializedName("fee_number")
    @Expose
    private Integer feeNumber;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Payment() {
    }

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getFeeNumber() {
        return feeNumber;
    }

    public void setFeeNumber(Integer feeNumber) {
        this.feeNumber = feeNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
