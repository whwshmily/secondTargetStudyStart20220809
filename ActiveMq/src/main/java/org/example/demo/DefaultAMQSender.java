package org.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class DefaultAMQSender {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(null, null, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("user");
        connection.start();
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("hello,word-->"+i);
            producer.send(textMessage);
            Thread.sleep(1000);
        }
        producer.close();
        connection.close();
        System.out.println("exit");
    }
}
