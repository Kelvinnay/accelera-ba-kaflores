package com.enjoi.concurrency.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AsyncService {

    @Async
    public void imprimirHolaMundo(){
        // SIMULAR QU DEMORA 4 SEGUNDOS
        try{
            Thread.sleep(4000);
        }catch (InterruptedException ex){}
        log.info("Hola Mundo");
    }


}
