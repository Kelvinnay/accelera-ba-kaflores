package com.monoflux.concurrency;

import reactor.core.publisher.Flux;

public class MainEjercicioTwo {
    public static void main(String[] args) {
        Flux<Integer> emitidor = emiteEnteros();
        emitidor.subscribe((s) -> System.out.println("onNext for each -> "+s),
                null,
                null
        );
        emitidor.filter(n -> n % 2 == 0)
                .map(s -> s*10)
                .subscribe(
                        System.out::println,
                        null,
                        null
                );

    }

    public static Flux<Integer> emiteEnteros(){
        return Flux.just(1,2,3,4,5);
    }
}
