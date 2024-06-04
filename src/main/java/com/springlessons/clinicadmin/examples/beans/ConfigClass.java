package com.springlessons.clinicadmin.examples.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * Configuration обычно используется, если в классе есть методы,
 * создающие Bean объекты и помещающие их в контекст spring.
 * */
@Configuration
public class ConfigClass {

    /**
     * Bean - объект, который возвращает метод будет помещен в контекст спринг, как бин-объект;   <br>
     * Primary - при инъекции зависимсоти одного типа (Runnable) в отсутствие аннотации Qualifier или
     * инъекции по имени метода, данному бину будет отдаваться предпочтение (дефолтная инъекция).
     * Аннотация Primary используется для разрешения кофликта при инъекции зависимостей одного типа.
     * */
    @Primary
    @Bean
    public Runnable runnable01(){
        return ()-> System.out.println("runnable01");
    }

    /**
     * Bean - объект, который возвращает метод будет помещен в контекст спринг, как бин-объект;   <br>
     * Bean("task02") - при инъекции данного бина можно использовать Qualifier с именен бина - @Qualifier("task02").
     * Именование бинов используется для разрешения кофликта при инъекции зависимостей одного типа
     * */
    @Bean("task02")
    public Runnable runnable02(){
        return ()-> System.out.println("runnable02");
    }
}