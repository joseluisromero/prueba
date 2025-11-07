package com.prueba;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Adycto {

    public static final String ACCOUNT_SID = "AC1717f12b8e665c9c9a2730094678ca59";
    public static final String AUTH_TOKEN = "58626e37706c82c28241cf4f66749fdb";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+593987276214"),
                        new com.twilio.type.PhoneNumber("+13854387907"),
                        "Mensaje manaba")
                .create();

        System.out.println(message.getSid());
    }

}
