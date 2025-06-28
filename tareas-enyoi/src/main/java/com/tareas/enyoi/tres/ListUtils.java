/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tareas.enyoi.tres;

import java.util.List;

/**
 *
 * @author kelvin
 */
class ListUtils {
    
    private ListUtils() {
    }
    //Toma elementos de posiciones impares (1, 3, 5, etc.) de una lista fuente
    public static <T> void addOddElements(List<T> sourceList, List<T> destinationList) {
        // Validar que ninguna de las listas sea null
        if (sourceList == null) {
            throw new IllegalArgumentException("La lista fuente (sourceList) no puede ser null.");
        }
        if (destinationList == null) {
            throw new IllegalArgumentException("La lista destino (destinationList) no puede ser null.");
        }

        // Incrementamos de 2 en 2 para obtener las siguientes posiciones impares EJ = (1, 3, 5)
        for (int i = 1; i < sourceList.size(); i += 2) {
            destinationList.add(sourceList.get(i)); 
        }
    }
}
    
