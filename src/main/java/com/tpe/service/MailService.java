package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repo.DbRepository;
import com.tpe.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component//bu classın objesinin üretiminden, yönetilmesinden Spring sorumlu olsun.Yani bean oluşturulsun.
@Scope("prototype")//her seferinde yeni bir obje(farklı referansta)
//@Scope("singleton")
public class MailService implements MessageService{

    @PostConstruct//obje oluşturulunca metodu çağırır
    public void postConstruct(){
        System.out.println("MailService objesi oluşturuldu.");
    }

    @PreDestroy//obje imha edilmeden önce metodu çağırır
    public void preDestroy(){
        System.out.println("MailService objesi imha ediliyooor... ");
    }



    //field injection
//    @Autowired//mail servise repo enjekte edilecek
//    @Qualifier("fileRepository")
//    private Repository repo;

    //setter injection

//    private Repository repo;
//    @Autowired
//    @Qualifier("fileRepository")
//    public void setRepo(Repository repo) {
//        this.repo = repo;
//    }

    //constructor injection:daha güvenli,daha anlaşılır,test etmek daha kolay
    private Repository repo;
    @Autowired
    public MailService(@Qualifier("fileRepository") Repository repo) {
        this.repo = repo;
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println("Ben bir mail servisiyim. Mesajınız : "+message.getBody());
    }

    @Override
    public void saveMessage(Message message) {
        //reponun save metoduna erişmek için repo objesi gerekiyor
        //Repository repository=new DbRepository();
        repo.save(message);

    }
}
