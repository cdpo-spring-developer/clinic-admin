package com.springlessons.clinicadmin.examples.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration обычно используется, если в классе есть методы,
 * создающие Bean объекты и помещающие их в контекст spring.
 * */
@Configuration
public class ConfigurationByProfile {

    /**
     * Bean - объект, который возвращает метод будет помещен в контекст спринг, как бин-объект;   <br>
     * Profile("prod") - создается только для профиля prod
     * */
    @Profile("prod")
    @Bean
    public Runnable runnable01(){
        System.out.println("prod");
        return ()-> System.out.println("runnable01");
    }

    /**
     * Bean - объект, который возвращает метод будет помещен в контекст спринг, как бин-объект;   <br>
     * Profile("prod") - создается только для профиля dev
     * */
    @Profile("dev")
    @Bean
    public Runnable runnable02(){
        return ()-> System.out.println("runnable02");
    }
}