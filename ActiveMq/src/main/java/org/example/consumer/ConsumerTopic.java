package org.example.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerTopic {
    @JmsListener(containerFactory = "jmsListenerContainerTopic",destination = "${consumer.topic.name}")
    public void consumer(String msg){
        System.out.println(this.getClass().getName()+"--->"+msg);
    }
}
