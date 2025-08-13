package com.monoflux.concurrency;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class MainEjercicioThree {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1,2,3);
        Flux<Object> numeros = emiteEnterosYCaracteres(integers);
        List<String> caracteres = Arrays.asList("A","B","C");
        Flux<Object> letras = emiteEnterosYCaracteres(caracteres);

        System.out.println("Impresion General");
        letras.subscribe((s) -> System.out.println("onNext -> "+s));
        numeros.subscribe((s) -> System.out.println("onNext -> "+s));

        System.out.println("\nCombinando con zipWith:");
        letras.zipWith(
                numeros, (letra, numero) ->  letra  + "-" + numero
                )
                .subscribe(System.out::println);

        System.out.println("\nCombinando con concatWith:");
        letras.concatWith(numeros.map(Object::toString))
                .subscribe(System.out::println);

    }
    public static Flux<Object> emiteEnterosYCaracteres(List<?> lista){
        return Flux.fromIterable(lista)
                .map(o -> {
                    if (o instanceof Integer) {
                        return (Integer) o;
                    } else if (o instanceof String) {
                        return (String) o;
                    }
                    throw new IllegalArgumentException("Unsupported type in list");
                });
    }
}
