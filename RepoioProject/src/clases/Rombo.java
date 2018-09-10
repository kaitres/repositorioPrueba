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
public class Rombo extends Figura {
    
    public Rombo(int centroX,int centroY, int escala){
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(new Coordenada(centroX , centroY - escala));
        this.coordenadas.add(new Coordenada(centroX + escala , centroY));
        this.coordenadas.add(new Coordenada(centroX , centroY + escala));
        this.coordenadas.add(new Coordenada(centroX - escala , centroY));
    }
}
