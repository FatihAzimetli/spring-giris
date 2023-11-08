package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Random;

@Configuration//bu classta konfigürasyon yapılacak
@ComponentScan("com.tpe")//bu packagedaki tüm componentları tarama işlevini gerçekleştirir.
//defaultta:com.tpe(AppConfiguration classının bulunduğu package)
@PropertySource("classpath:application.properties")//properties dosyasının uyg. içinde kaynak olarak erişilmesini sağlar
public class AppConfiguration {

    //Environment:Springin interfacei; .properties uzantılı dosya içindeki key/value lara ulaşmamızı sağlar.
    //aynı zamanda uygulamanın çalıştığı sistemdeki ortam değişkenlerine de ulaşabilir.

    @Autowired
    private Environment environment;


    //thirdParty classlardan bean oluşturulmasını istersek:bean
    @Bean
    public Random random(){
        return new Random();
    }

    //value anatasyonu ile yaptığımızı Javanın Properties classı ile de yapabiliriz.
    //Properties-->hashtable extend

    @Bean//1 tane properties bean
    public Properties properties(){
        Properties properties=new Properties();
        properties.put("mymail",environment.getProperty("app.email"));
        properties.put("myphone",environment.getProperty("app.phone"));
        return properties;
    }




}