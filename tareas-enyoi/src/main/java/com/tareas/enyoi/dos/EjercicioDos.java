/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tareas.enyoi.dos;

/**
 *
 * @author kelvin
 */
public class EjercicioDos {

    public static void main(String[] args) {
        //Ejercio 2
        NotNullMAp<String ,String>  miMapa;
        miMapa = new NotNullMAp();
        miMapa.put("nombre", null);
        
        System.out.println("miMapa.."+miMapa);
        
    }
    
    
}
