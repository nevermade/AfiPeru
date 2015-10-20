package com.example.dp2.afiperu.util;

/**
 * Created by Nevermade on 18/10/2015.
 */
public class EnumMapping {

    public static String getPaymentState(int state){
        if(state==AppEnum.EnumPaymentState.EXPIRATED.ordinal()){
            return "vencido";
        }else if(state==AppEnum.EnumPaymentState.PAYED.ordinal()){
            return "pagado";
        }else if(state==AppEnum.EnumPaymentState.PENDING.ordinal()){
            return "pendiente";
        }

        return "estado no mapeado";
    }
}
