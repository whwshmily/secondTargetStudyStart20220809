package org.example.sender;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

@Service
public class SenderQueue {


    @Resource
    JmsMessagingTemplate jmsTemplate;

    @Resource(name = "queue")
    Queue queue;

    @Resource(name = "topic")
    Topic topic;


    @Scheduled(fixedDelay = 3000)
    public void sendQueueMsg(String msg){
        jmsTemplate.convertAndSend(queue,msg);
    }

    public void sendQueueMsg(Object obj){
        jmsTemplate.convertAndSend(queue,obj);
    }
    @Scheduled(fixedDelay = 3000)
    public void sendQueueTopic(String msg){
        jmsTemplate.convertAndSend(topic,msg);
    }

    public void sendQueueTopic(Object obj){
        jmsTemplate.convertAndSend(topic,obj);
    }
}
