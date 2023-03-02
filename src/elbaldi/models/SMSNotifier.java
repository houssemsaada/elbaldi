/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.models;

/**
 *+12766378855


 * @author USER
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.http.client.RedirectStrategy;

public class SMSNotifier {
    public static final String ACCOUNT_SID = "AC71e1feb032fa0923d3b0a48b604bd489";
    public static final String AUTH_TOKEN = "096e46ba8b1cadb1b9863abe7afb426c";
    public static final String FROM_NUMBER = "+12766378855";

//    public void notifyClients(List<String> phoneNumbers, String messageText) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        for (String phoneNumber : phoneNumbers) {
//            Message message = Message.creator(
//                new PhoneNumber(phoneNumber),
//                new PhoneNumber(FROM_NUMBER),
//                messageText)
//            .create();
//        }
//    }
    public void notifyClients(List<String> phoneNumbers, String messageText) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    for (String phoneNumber : phoneNumbers) {
        try {
            Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(FROM_NUMBER),
                messageText)
            .create();
        } catch (com.twilio.exception.ApiException e) {
            // Log the error message
            System.err.println("Failed to send SMS message to " + phoneNumber + ": " + e.getMessage());
        }
    }
}
}
