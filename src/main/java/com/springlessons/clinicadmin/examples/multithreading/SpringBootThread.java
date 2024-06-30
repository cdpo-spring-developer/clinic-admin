package com.springlessons.clinicadmin.examples.multithreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class SpringBootThread {

    /**
     *  Метод будет вызываться в отдельном потоке
     *  Имя пула задается в параметрах аннотации
     *  Можно вернуть CompletableFuture и описать callback -
     *  действия, которые будут выпонены после завершения работы метода
     *  */
    @Async("xxx-executor")
    public CompletableFuture<String> /*void*/ doInOtherThread(){
        log.info("doInOtherThread");
        return CompletableFuture.completedFuture("Hello World");
    }
}
