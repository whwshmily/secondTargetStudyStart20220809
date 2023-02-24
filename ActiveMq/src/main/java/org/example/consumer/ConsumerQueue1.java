package org.example.consumer;

import org.springframework.jms.annotation.JmsListener;

//@Service
public class ConsumerQueue1 {
    @JmsListener(containerFactory = "jmsListenerContainerQueue",destination = "${consumer.queue.name}")
    public void consumer(String msg){
        System.out.println(this.getClass().getName()+"--->"+msg);
    }
}
