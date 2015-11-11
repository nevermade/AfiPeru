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
        SESSION_AND_DOCUMENTS(12, 15),
        CREATE_OR_EDIT_POINTS_OF_REUNION(13),
        ATTENDANCE_AND_QUALIFICATION(16),
        LIST_USERS(35),
        LIST_PERIOD_REPORT(21, 39),
        PAYMENT(24),
        LIST_SCHOOL_AND_VOLUNTEER(28),
        LIST_COMMENTS(33);

        private int numVal;
        private int numVal2;

        EnumAction(int numVal) {
            this.numVal = numVal;
            this.numVal2 = -1;
        }
        EnumAction(int numVal, int numVal2){
            this.numVal = numVal;
            this.numVal2 = numVal2;
        }

        public boolean hasPermission(){
            return hasPermission(Constants.loggedUser);
        }

        public boolean hasPermission(User user){
            for(Action a : user.getActions()){
                if(a.getId()== numVal) {
                    return true;
                }else if(numVal2 != -1 && a.getId() == numVal2) {
                    return true;
                }
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


