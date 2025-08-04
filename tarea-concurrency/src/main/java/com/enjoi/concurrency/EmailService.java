package com.enjoi.concurrency;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class EmailService {
    @Async
    public Future<Boolean> sendEmail(){
        // SIMULAR QU DEMORA 3 SEGUNDOS
        try{
            Thread.sleep(7000);
        }catch (InterruptedException ex){
            return CompletableFuture.completedFuture(false); // Return a failed future on interruption
        }

        return CompletableFuture.completedFuture(true);
    }
    @Async
    public CompletableFuture<Boolean>  sendEmailDos(){
        // SIMULAR QU DEMORA 3 SEGUNDOS
        try{
            Thread.sleep(7000);
        }catch (InterruptedException ex){
            return CompletableFuture.completedFuture(false); // Return a failed future on interruption
        }

        return CompletableFuture.completedFuture(true);
    }
}
