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

    public Rectangulo(int centroX,int centroY, int escala) {
        int alto=50;
        
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(new Coordenada(centroX-alto/2, centroY-escala/2));
        this.coordenadas.add(new Coordenada(centroX+alto/2, centroY-escala/2));
        this.coordenadas.add(new Coordenada(centroX+alto/2, centroY+escala/2));
        this.coordenadas.add(new Coordenada(centroX-alto/2, centroY+escala/2));
    }
    
}
