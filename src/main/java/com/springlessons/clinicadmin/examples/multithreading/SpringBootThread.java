package com.springlessons.clinicadmin.examples.multithreading;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
// sonar lint plugin
@Slf4j
@Service
public class SpringBootThread {

    @Async("xxx-executor")
    public CompletableFuture<String> /*void*/ doInOtherThread(){
        log.info("doInOtherThread");
        log.debug("doInOtherThread");
       // log.error();
        System.out.println("doInOtherThread");
        return CompletableFuture.completedFuture("Hello World");
    }
}
/*@RestController
class SomeService {
    @Autowired
    private SpringBootThread someThread;
    @GetMapping
    public void someVoidMethod(){
        someThread.doInOtherThread();
        CompletableFuture<String> future = someThread.doInOtherThread();
        future.thenAccept((stringResult)->{
            // инструкции будут выполнены,
            // когда метод doInOtherThread завершит работу

            // stringResult - результат работы метода
        });
        ///
    }
}*/
