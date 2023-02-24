package org.example.demo;

import org.example.entry.MLG;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class Consumer1 {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(null, null, "tcp://localhost:61616");
        List<String> list = new ArrayList<String>();
        list.add(MLG.class.getPackage().getName());
        factory.setTrustedPackages(list);
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Queue user = session.createQueue("user?consumer.exclusive=true&consumer.priority=1");
        Queue user = session.createQueue("user");
        MessageConsumer consumer = session.createConsumer(user,"i=5");
        consumer.setMessageListener(Consumer1::onMessage);
        connection.start();
        System.out.println("-------->");
    }

    private static void onMessage(Message message) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        ObjectMessage msg = (ObjectMessage) message;
        try {
            MLG mlg = (MLG) msg.getObject();
            System.out.println("msg2:"+mlg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
