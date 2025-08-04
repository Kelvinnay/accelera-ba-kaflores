package com.enjoi.concurrency.controller;

import com.enjoi.concurrency.EmailService;
import com.enjoi.concurrency.SmsService;
import com.enjoi.concurrency.service.AsyncService;
import com.enjoi.concurrency.service.SyncService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Log4j2
public class ConcurrencyController {

    final EmailService emailService;
    final SmsService smsService;
    final AsyncService asyncService;
    final SyncService syncService;

    @GetMapping("tarea/async")
    public ResponseEntity<Boolean> sendEmail(){
        Future<Boolean> future = emailService.sendEmail();
        try {
            System.out.println("Waiting");
            Boolean result = future.get();
            System.out.println("Done");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("tarea/sync")
    public ResponseEntity<Boolean> sendSMS(){
        boolean booleano = smsService.sendSMS();
        return new ResponseEntity<>(booleano, HttpStatus.CREATED);
    }


    @GetMapping("tarea/combine")
    public ResponseEntity<String> combineSmsEmail() throws ExecutionException, InterruptedException{
        // Iniciando ambos tareas asyncronas
        CompletableFuture<Boolean> emailFuture = emailService.sendEmailDos();
        CompletableFuture<Boolean> smsFuture = smsService.sendSms();
        log.info("Waiting...");
        // Wait for both tasks to complete
        CompletableFuture.allOf(emailFuture, smsFuture).join();
        log.info("Completed...");

        log.info("Waiting get()...");
        // Obtener resultados desde tareas completadas
        boolean emailResult = emailFuture.get();
        boolean smsResult = smsFuture.get();
        log.info("Completed get()...");

        if (emailResult && smsResult) {
            String successMessage = "✅ Successfully sent both email and SMS.";
            System.out.println(successMessage);
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } else {
            String failureMessage = "❌ One or more asynchronous tasks failed.";
            System.out.println(failureMessage);
            return new ResponseEntity<>(failureMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("async")
    public ResponseEntity<Void> testSendEmail(){
        asyncService.imprimirHolaMundo();
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("sync")
    public ResponseEntity<Void> testendSMS(){
        syncService.imprimirHolaMundo();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
