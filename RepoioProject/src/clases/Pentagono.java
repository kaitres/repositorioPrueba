/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;

/**
 *
 * @author IP-ROUTE
 */
public class Pentagono extends Figura{
    //no correspondo al centro las variables centro son el primer punto hasta ahora
    public Pentagono(int centroX,int  centroY , int escala ){
        this.coordenadas = new ArrayList<>();
        int avanzaX , avanzaY;
        int cordX=centroX;
        int cordY=centroY;
        
        
    // corresponden a los triangulos que se pueden formar en los costados del pentagono
        avanzaY=abs ((int) (escala*cos(72)));
        avanzaX=abs ((int) (escala*sin(72)));

        
        
    //primer punto
        this.coordenadas.add(new Coordenada(cordX , cordY));
        
    //segundo punto
        cordX += avanzaX;
        cordY += avanzaY;
        this.coordenadas.add(new Coordenada(cordX , cordY));
        
        
    //tercer punto
        cordX += escala;
        this.coordenadas.add(new Coordenada(cordX , cordY));
        
        
    //cuarto punto
        cordX += avanzaX;
        cordY -= avanzaY;
        this.coordenadas.add(new Coordenada(cordX , cordY));
        
        
    //quinto punto
        //un triangolo que se forma en la parte superior del pentagono
        avanzaX = abs((int) (escala * cos(54)));
        avanzaY = abs((int) (escala * sin(54)));
        
        cordX -= avanzaX;
        cordY -= avanzaY;
        this.coordenadas.add(new Coordenada(cordX , cordY));
        
        
    }
}
