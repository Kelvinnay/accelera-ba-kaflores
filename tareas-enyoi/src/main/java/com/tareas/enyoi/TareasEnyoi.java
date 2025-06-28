/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tareas.enyoi;

/**
 *
 * @author kelvin
 */
import com.tareas.enyoi.dos.NotNullMAp;
import com.tareas.enyoi.uno.MyStack;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
public class TareasEnyoi {

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
        
        
        
        //Ejercio 2
        NotNullMAp<String ,String>  miMapa;
        miMapa = new NotNullMAp();
        miMapa.put("nombre", null);
        
        System.out.println("miMapa.."+miMapa);
        
        //Ejercicio 3 
        //ver paquete 3
    }
}
