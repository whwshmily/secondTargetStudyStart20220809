package org.example.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;
import java.io.Serializable;

@Component
public class ConsumerQueue {


    @JmsListener(containerFactory = "jmsListenerContainerQueue",destination = "${consumer.queue.name}")
    public void consumer(ObjectMessage objectMessage) throws Exception{
        Serializable object = objectMessage.getObject();
        System.out.println(this.getClass().getName()+"--->"+object.toString());
    }




}
