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
public class Triangulo extends Figura {
    public Triangulo(int centroX,int centroY, int escala){
        this.coordenadas = new ArrayList<>();
        int altura=(int) sqrt (pow(escala,2) - pow(escala/2,2));
        this.coordenadas.add(new Coordenada( centroX - escala/2 , centroY + altura/2));
        this.coordenadas.add(new Coordenada( centroX + escala/2 , centroY + altura/2 ));
        this.coordenadas.add(new Coordenada( centroX ,centroY - altura/2));
    }
}
