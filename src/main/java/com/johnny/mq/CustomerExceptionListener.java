package com.johnny.mq;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

/**
 * Created by johnson.yang on 2016/5/16.
 */
public class CustomerExceptionListener implements ExceptionListener {

    @Override
    public void onException(JMSException exception) {
        System.out.println(exception.getErrorCode() + ":" + exception.getMessage());
    }
}
