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
        SESSION_AND_DOCUMENTS(15),
        CREATE_POINTS_OF_REUNION(13),
        LIST_POINTS_OF_REUNION(14),
        ASSISTANCE(16),
        LIST_USERS(31),
        LIST_PERIOD_REPORT(20),
        PAYMENT(21),
        LIST_SCHOOL_VOLUNTEER(28);

        private int numval;

        private int numVal;

        EnumAction(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }

    }
}


