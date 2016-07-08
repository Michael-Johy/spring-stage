package com.johnny.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * Created by johnson.yang on 2016/5/16.
 */
public class JMSReceiver {

    private static final String AMQ_URL = "tcp://localhost:61616?socketBufferSize=262144&ioBufferSize=32768";

    public static void main(String[] args) throws JMSException, InterruptedException {
        MessageListener messageListener = new CustomerMessageListener();

        ConnectionFactory factory = new ActiveMQConnectionFactory(AMQ_URL);
        Connection connection = factory.createConnection();
        connection.setExceptionListener(exception ->
                System.out.println("exceptionlistener")
        );
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = new ActiveMQQueue("johnny");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(messageListener);
        Thread.sleep(1000 * 10);

        connection.close();

        CompletionListener completionListener;
    }

}
