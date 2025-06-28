/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tareas.enyoi.uno;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author kelvin
 */
public class EjercicioUno {

    public static void main(String[] args) {
        //Ejercicio 1
        Stack<Integer> pila = new Stack<>();
        pila.push(1);
        pila.push(2);
        pila.push(3);
        System.out.println(pila.pop());
        System.out.println(pila.pop()); // accede a ultimo dato y eliminarlo
        System.out.println(pila.pop());
        
        MyStack<Integer> pilaCustom = new MyStack<Integer>();
        List<Integer> lstaInt = Arrays.asList(1,2,3);
        pilaCustom.pushAll(lstaInt);
        System.out.println(""+pilaCustom.popAll());
        
        
        
    }
    
}
