package org.example.demo;

import org.example.entry.MLG;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class Consumer2 {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(null, null, "tcp://localhost:61616");
        List<String> list = new ArrayList<String>();
        list.add(MLG.class.getPackage().getName());
        factory.setTrustedPackages(list);
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue user = session.createQueue("USER.TOuser");
        MessageConsumer consumer = session.createConsumer(user);
        consumer.setMessageListener(message -> {
            ObjectMessage msg = (ObjectMessage)message;
            try {
                MLG mlg = (MLG) msg.getObject();
                System.out.println(mlg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        connection.start();
        System.out.println("-------->");
    }
}
