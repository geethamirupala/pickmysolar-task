package com.pmst.qa.util;
//import  com

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TwilioUtil {

    static String accountSid = "AC1843d7a1a782d4e7f1b78962c8d51eab";
    static String authToken = "1a827f4fd7fb06aa4198efd163739122";

    public static String getOTP(){
        try {
            Twilio.init(accountSid, authToken);
            String smsBody = getMessage();
            System.out.println(smsBody);
            String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
            System.out.println(OTPNumber);
            return OTPNumber;
        } catch (Exception e){
            return null;
        }
    }

    public static String getMessage() {
        return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals("+13343734019")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(accountSid).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }

}
