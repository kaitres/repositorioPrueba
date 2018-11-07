/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static java.lang.Math.abs;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

/**
 *
 * @author The.N
 */
public class Union {
    Figura fig1;
    Figura fig2;
    Point2D unionFig2;
    Point2D unionFig1;

    public Point2D getUnionFig2() {
        return unionFig2;
    }

    public Point2D getUnionFig1() {
        return unionFig1;
    }

    
    public Union(Figura fig1,Figura fig2 ){
        this.fig2 = fig2;
        this.fig1 = fig1;
        ConeccionEntidadRelacion();
    }

    /**
     * metodo que busca los puntos (coordenadas) mas cercanos entre una entidad y una relacion (puntos de control)
     */
    public void ConeccionEntidadRelacion(){       
        Point2D minFig2=new Point2D(0,0);
        Point2D minFig1=new Point2D(0,0);
        for (int i = 0; i < fig1.coordenadasConeccion.size() ; i++) {
            for (int j = 0; j < fig2.coordenadasConeccion.size(); j++) {
                if(j==0 && i==0){
                    minFig1 = fig1.coordenadasConeccion.get(i);
                    minFig2 = fig2.coordenadasConeccion.get(j) ;
                }
                if(minFig2.distance(minFig1) > fig1.coordenadasConeccion.get(i).distance(fig2.coordenadasConeccion.get(j) )){                 
                   minFig1 = fig1.coordenadasConeccion.get(i);
                   minFig2 = fig2.coordenadasConeccion.get(j);
                }
            }
        }
        fig1.coordenadasConeccion.remove(minFig1);
        //entidad.coordenadasConeccion.remove(minFig2);
        
        //unionFig2 = minFig2;
        unionFig2 = fig2.getPuntoCentral();
        unionFig1 = minFig1;
    }
}
