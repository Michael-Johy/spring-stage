package com.johnny.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * Created by johnson.yang on 2016/5/16.
 */
public class JMSSender {

    private static final String AMQ_URL = "tcp://localhost:61616?socketBufferSize=262144&ioBufferSize=32768";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory(AMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = new ActiveMQQueue("johnny");

        TextMessage textMessage = session.createTextMessage("aaaa");
        textMessage.setStringProperty("type", "johnny");
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
        CompletionListener completionListener = new CustomerCompletionListener();

        for (int i = 0; i < 10; i++) {
            textMessage = session.createTextMessage("bbb" + i);
            producer.send(textMessage);
            System.out.println(textMessage.getText());
        }
        connection.close();
        System.out.println("ok3");
    }
}
