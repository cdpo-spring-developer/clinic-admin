package com.springlessons.clinicadmin.examples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClassToInject {
    // Инъекция зависимостей реализуется аннотацией Autowired и доступна:
    // 1. на уровне свойств - плохая практика из-за нарушения принуипа инкапсуляции
    @Autowired
    private Runnable runnable;

    // 2. на уровне конструктора - считается хорошей практикой
    // для Spring Boot 3 аннотацию Autowired  можно не указывать,
    // она неявно устанавливается для конструкторов
    public ClassToInject(Runnable runnable) {
        this.runnable = runnable;
    }

    // 3. на уровне метода
    public void inject(Runnable runnable){}


    // При создании бин объектов следует избегать цикличных зависимостей,
    // когда бинам для создания требуются ссылки друг на друга

    // в контексте не один бин типа IType (AClass и BClass),
    // spring не понимает, какой из них выбрать
    // поэтому при инъекции зависимостей возникнет конфликт

    // в контексте не один бин типа Runnable (runnable01 и runnable02),
    // spring не понимает, какой из них выбрать
    // поэтому при инъекции зависимостей возникнет конфликт

    // Есть несколько вариантов инъекций в таком случае:

    // 1. Инъекция по @Primary - передается бин, отмеченный @Primary аннотацией
    // в данном случае AClass и runnable01 из ConfigClass
    @Autowired
    public void inject01(IType iType, Runnable runnable){}

    // 2. Инъекция по @Qualifier - бин передаётся по имени, установленному ему при создании
    // в данном случае BClass ("bBean") и runnable02 ("task02") из ConfigClass

    @Autowired
    public void inject02(@Qualifier("bBean") IType iType, @Qualifier("task02") Runnable runnable){}

    // 3. Инъекция по имени - имя аргумента должно соответствовать имени класса или @Bean метода
    // в данном случае AClass и runnable01 из ConfigClass
    @Autowired
    public void inject03(IType AClass, Runnable runnable01){}

}