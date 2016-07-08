package com.johnny.mq;

import javax.jms.CompletionListener;
import javax.jms.Message;

/**
 * Created by johnson.yang on 2016/5/16.
 */
public class CustomerCompletionListener implements CompletionListener {

    @Override
    public void onCompletion(Message message) {
        System.out.println("success");
    }

    @Override
    public void onException(Message message, Exception exception) {
        System.out.println("fail");
    }
}
