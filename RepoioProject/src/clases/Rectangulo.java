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
public class Rectangulo extends Figura{

    public Rectangulo(int verticeX1, int verticeY1,int verticeX2, int verticeY2,
                      int verticeX3, int verticeY3,int verticeX4, int verticeY4) {
        
        
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(new Coordenada(verticeX1, verticeY1));
        this.coordenadas.add(new Coordenada(verticeX2, verticeY2));
        this.coordenadas.add(new Coordenada(verticeX3, verticeY3));
        this.coordenadas.add(new Coordenada(verticeX4, verticeY4));
    }
    
}
