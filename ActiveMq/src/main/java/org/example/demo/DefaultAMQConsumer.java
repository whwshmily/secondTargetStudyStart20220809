package org.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class DefaultAMQConsumer {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(null, null, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue user = session.createQueue("user");
        MessageConsumer consumer = session.createConsumer(user);
        connection.start();
        while (true){
            TextMessage receive = (TextMessage) consumer.receive();
            System.out.println(receive.getText());
            Thread.sleep(2000);
            receive.acknowledge();
        }
    }
}
