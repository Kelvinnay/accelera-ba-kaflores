package com.enjoi.concurrency;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SmsService {

    public boolean sendSMS(){
        // SIMULAR QUE DEMORA 2 SEGUNDOS
        try{
            Thread.sleep(2000);
        }catch (InterruptedException ex){}

        return true;
    }

    @Async
    public CompletableFuture<Boolean> sendSms() {
        // Simulate a delay for the SMS service
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture(false);
        }
        return CompletableFuture.completedFuture(true);
    }
}
