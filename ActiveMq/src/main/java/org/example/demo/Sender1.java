package org.example.demo;

import org.example.entry.MLG;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class Sender1 {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(null, null, "tcp://localhost:61616?jms.producerWindowSize=1");
        List<String> list = new ArrayList<String>();
        list.add(MLG.class.getPackage().getName());
        factory.setTrustedPackages(list);
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("user");
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//        producer.setTimeToLive(1000);
        for (int i = 0; i < 10; i++) {
            MLG mlg = new MLG("xiaoqiu"+i,i,i*1.15,"LOVE ME",2.158*i);
            ObjectMessage message = session.createObjectMessage(mlg);
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,10*1000);
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,2*1000);
//            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,9);
            message.setIntProperty("i",i);
            producer.send(message);
            Thread.sleep(1000);
        }
        connection.close();
        System.out.println("exit");
    }
}
