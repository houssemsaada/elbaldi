/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author houss
 */
public class SmsServicee {
    public static final String ACCOUNT_SID = "AC21dc5d3f63665f41ea25ef019b493cea";
    public static final String AUTH_TOKEN = "4d6fdefdb26bcaaef45f4c395ae18df0";
    public static final String TWILIO_NUMBER = "+12764962871";

    public static void sendSms(String to, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(TWILIO_NUMBER), messageBody).create();
        System.out.println(message.getSid());
    }
}
