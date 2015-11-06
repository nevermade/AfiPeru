package com.example.dp2.afiperu.util;

import com.example.dp2.afiperu.domain.Action;
import com.example.dp2.afiperu.domain.User;

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
        CREATE_OR_EDIT_POINTS_OF_REUNION(13),
        ATTENDANCE_AND_QUALIFICATION(16),
        LIST_USERS(35),
        LIST_PERIOD_REPORT(21),
        PAYMENT(24),
        LIST_SCHOOL_AND_VOLUNTEER(28),
        LIST_COMMENTS(33);

        private int numVal;

        EnumAction(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }

        public boolean hasPermission(){
            return hasPermission(Constants.loggedUser);
        }

        public boolean hasPermission(User user){
            for(Action a : Constants.loggedUser.getActions()){
                if(a.getId()== getNumVal())
                    return true;
            }
            return false;
        }

    }

    public enum ResponseStatus{
        SUCCESS,
        ERROR,
        FAILURE
    }



}


