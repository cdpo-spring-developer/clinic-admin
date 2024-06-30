package com.springlessons.clinicadmin.examples.multithreading;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling // возможность запускать методы по расписанию
public class SpringBootSchedule {

    /**
     * Метод вызывается по расписанию (раз в 7 дней), соласно настройкам,
     * описанным в параметрах аннотации Scheduled
     * Метод управляется отдельным пулом благодаря аннотации @Async
     * */
    @Async("SpringBootSchedule")
    @Scheduled(fixedRate = 7, timeUnit = TimeUnit.DAYS)
    public void runBySchedule(){
        // инструкции по расписанию
    }
}
