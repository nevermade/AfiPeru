package com.example.dp2.afiperu.util;

/**
 * Created by Nevermade on 18/10/2015.
 */
public class AppEnum {
    public enum EnumPaymentState {
        PAYED,
        PENDING,
        EXPIRATED
    }

    public enum EnumAction{
        SESSION_AND_DOCUMENTS(12),
        CREATE_OR_EDIT_POINTS_OF_REUNION(13),
        LIST_POINTS_OF_REUNION(14),
        ASSISTANCE(16),
        LIST_USERS(35),
        LIST_PERIOD_REPORT(21),
        PAYMENT(24),
        LIST_SCHOOL_AND_VOLUNTEER(28),
        LIST_COMMENTS(33),
        ATTENDANCE_AND_QUALIFICATION(16);

        private int numVal;

        EnumAction(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }

    }

    public enum ResponseStatus{
        SUCCESS,
        ERROR,
        FAILURE
    }



}


