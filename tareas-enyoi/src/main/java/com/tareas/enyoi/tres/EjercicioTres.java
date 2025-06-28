/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tareas.enyoi.tres;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EjercicioTres {

    public static void main(String[] args) {
        
        // Ejemplo con Integers
        List<Integer> sourceIntegers = new ArrayList<>();
        sourceIntegers.add(10); // Índice 0
        sourceIntegers.add(20); // Índice 1 (IMPAR)
        sourceIntegers.add(30); // Índice 2
        sourceIntegers.add(40); // Índice 3 (IMPAR)

        List<Integer> destinationIntegers = new ArrayList<>();
        destinationIntegers.add(5); // Ya tiene un elemento
        System.out.println("Lista Fuente de Enteros: " + sourceIntegers);
        System.out.println("Lista Destino de Enteros (inicial): " + destinationIntegers);

        ListUtils.addOddElements(sourceIntegers, destinationIntegers);
        System.out.println("Lista Destino de Enteros (después de addOddElements): " + destinationIntegers);
        // Salida esperada: [5, 20, 40]
        
    }
    
}
