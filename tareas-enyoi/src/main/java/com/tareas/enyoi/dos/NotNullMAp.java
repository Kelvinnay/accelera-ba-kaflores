/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tareas.enyoi.dos;

import java.util.HashMap;

/**
 *
 * @author kelvin
 */
public class NotNullMAp<K,V> extends HashMap<K,V> {

    @Override
    public V put(K key, V value) {
        if(value != null){
            return super.put(key, value); // super(). si llama a constructor super. llama metodos
        }
       throw new RuntimeException("NO PERMITE AGREGAR NULL");
    }
    
    
}
