/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author IP-ROUTE
 */
public abstract class Figura {
    ArrayList<Integer> coordenadas;

    public ArrayList<Integer> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(ArrayList<Integer> coordenadas) {
        this.coordenadas = coordenadas;
    }
    
    
}
