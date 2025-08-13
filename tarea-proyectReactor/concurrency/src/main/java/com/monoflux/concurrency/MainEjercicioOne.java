package com.monoflux.concurrency;

import reactor.core.publisher.Mono;

public class MainEjercicioOne {
    public static void main(String[] args) {

        Mono<String> emitidor = emiteMsj("Hola Mundo reactivo!!");
        emitidor.subscribe(
                str-> System.out.println("On Next -> "+str),
                null,
                null
        );

        emitidor.map(s -> s + " Desde Project Reactor")
                .filter(s-> s.length()>4)
                .subscribe(System.out::println);
//                .subscribe(
//                str-> System.out.println("On Next -> "+str));

    }
    public static Mono<String> emiteMsj(String msj){
        return Mono.just(msj);
    }
}
