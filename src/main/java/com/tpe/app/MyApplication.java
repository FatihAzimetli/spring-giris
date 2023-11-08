package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class MyApplication {
    public static void main(String[] args) {

        Message message=new Message();
        message.setBody("Spring ile çalışmak HARİKA:)");

        //config sınıfını okur, component scan ile tüm componentları tarar
        //her birinden sadece 1 tane bean oluşturur, contextte hazır bekletir
        //bean istendiğinde eğer bu objenin bağımlılığı varsa içine enjekte eder ve verir.
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);


        //mesaj mailservice ile gönderilsin
        //MessageService service=new MailService();

        MessageService service =context.getBean(MailService.class);//biz obje oluşturmadık,rica ettik
        // MessageService service=context.getBean(MessageService.class);//Spring akıllı
        service.sendMessage(message);//service:spring bean


        //MessageService service2=context.getBean(SmsService.class);
        MessageService service2=context.getBean("sms_service",MessageService.class);//1
        service2.sendMessage(message);

        //interface i implemente eden birden fazla class olduğunda
        //getBean ile contextten interface ile birlikte bean istediğimizde
        //beanin çeşidini(ismini) belirtmeliyiz.

        MessageService service3=context.getBean(MailService.class);//2
        service3.saveMessage(message);


//        Random random=new Random();//thirdparty class
//        System.out.println(random.nextInt(10));

        //random objesi 1 kere Spring tarafından oluşturulsa ve tüm uygulamada
        //aynı objeyi kullansak, Nasıl?...

//        Random random=context.getBean(Random.class);
//        System.out.println(random.nextInt(10));
//
        SmsService service4=context.getBean(SmsService.class);//2
        service4.saveMessage(message);
        // service4.printContact();
        service4.printProperties();

        //bean scope
        //singleton:default
        //tüm uygulama boyunca sadece 1 tane bean oluşturulur
        //beanin lifecycleından tamamen Spring sorumludur.

        //prototype
        //her istekte yeni bir bean oluşturulur
        //beanin imha edilmesinden Spring sorumlu DEĞİLDİR.


        MessageService service5=context.getBean(MailService.class);//3
        MessageService service6=context.getBean(MailService.class);//4

        if (service5==service6){
            System.out.println("Aynı objeler");
            System.out.println(service5);
            System.out.println(service6);
        }else {
            System.out.println("Farklı objeler");
            System.out.println(service5);
            System.out.println(service6);
        }




        context.close();//scopeu singleton olan beanleri imha ediyor.

    }
}
