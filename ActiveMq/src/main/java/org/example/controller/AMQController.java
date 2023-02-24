package org.example.controller;

import org.example.entry.User;
import org.example.sender.SenderQueue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AMQController {

    @Resource
    SenderQueue senderQueue;




    @RequestMapping("/sendMsg")
    public Object sendQueueMsg(User user){

        senderQueue.sendQueueMsg(user);
        senderQueue.sendQueueTopic(user.toString());
        return user;
    }


}
