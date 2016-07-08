package com.johnny.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by johnson.yang on 2016/5/16.
 */
public class CustomerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        String text;
        try {
            text = textMessage.getText();
            System.out.println("Received :" + text);
        } catch (JMSException e) {
            System.out.println(e);
        }
    }
}
