package com.tpe;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//bu classta konfigürasyon yapılacak
@ComponentScan("com.tpe")//bu packagedaki tüm componentları tarama işlevini gerçekleştirir.
//defaultta:com.tpe(AppConfiguration classının bulunduğu package)
public class AppConfiguration {
}
