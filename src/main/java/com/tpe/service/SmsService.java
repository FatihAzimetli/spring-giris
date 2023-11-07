package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("sms_service")//defaultta:smsService
public class SmsService implements MessageService{

    @Autowired
    @Qualifier("dbRepository")
    private Repository repo;

    @Override
    public void sendMessage(Message message) {
        System.out.println("Ben bir sms servisiyim. Mesajınız : "+message.getBody());
    }

    @Override
    public void saveMessage(Message message) {
        Random random=new Random();
        System.out.println(random.nextInt(10));
        repo.save(message);

    }
}