package com.enjoi.concurrency.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SyncService {
    public void imprimirHolaMundo(){
        // SIMULAR QU DEMORA 3 SEGUNDOS
        try{
            Thread.sleep(7000);
        }catch (InterruptedException ex){}
        log.info("Hola Mundo");


    }
}
