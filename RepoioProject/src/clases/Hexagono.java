/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author IP-ROUTE
 */
public class Hexagono extends Figura{
    public Hexagono(int centroX , int centroY , int escala){
        this.coordenadas = new ArrayList<>();
        //punto 1
        this.coordenadas.add(new Coordenada((int) (centroX - (sqrt(pow(escala , 2) - pow(escala/2,2) ))) , centroY - escala/2));
        //punto 2
        this.coordenadas.add(new Coordenada((int) (centroX - (sqrt(pow(escala , 2) - pow(escala/2,2) ))) , centroY + escala/2));
        //punto 3
        this.coordenadas.add(new Coordenada(centroX , centroY + escala ));
        //punto 4
        this.coordenadas.add(new Coordenada((int) (centroX + (sqrt(pow(escala , 2) - pow(escala/2,2) ))), centroY + escala/2 ));
        //punto 5
        this.coordenadas.add(new Coordenada((int) (centroX + (sqrt(pow(escala , 2) - pow(escala/2,2) ))), centroY - escala/2 ));
        //punto 6
        this.coordenadas.add(new Coordenada(centroX , centroY - escala ));  
    }   
}
