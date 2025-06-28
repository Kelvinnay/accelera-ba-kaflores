/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tareas.enyoi.uno;

import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class MyStack<K> extends Stack<K> { //Se puede poner dato o texto  o letra unica
    
    
    public MyStack() {
        super(); // Llama al constructor de la clase padre (java.util.Stack)
    }
    
    public void pushAll(List<K> lista){
        for(K e : lista){
            super.push(e); //llamo metodo de padre
        }
    }
    public List<K> popAll(){
        List<K> output = new ArrayList<>();
        while(!this.empty()){
            K e= super.pop();
            output.add(e);
        }
        return output;
    }
    
    
    
    
    
    
    
}
