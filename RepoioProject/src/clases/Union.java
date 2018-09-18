/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author The.N
 */
public class Union {
    Figura relacion;
    Figura entidad;
    Point2D unionEntidad;
    Point2D unionRelacion;

    
    public Union(Figura relacion,Figura entidad ){
        this.entidad = entidad;
        this.relacion = relacion;
        ConeccionEntidadRelacion();
    }

    /**
     * metodo que busca los puntos (coordenadas) mas cercanos entre una entidad y una relacion (puntos de control)
     */
    public void ConeccionEntidadRelacion(){       
        Point2D minimoEntidad=new Point2D(0,0);
        Point2D minimoRelacion=new Point2D(0,0);
        for (int i = 0; i < relacion.coordenadasConeccion.size() ; i++) {
            for (int j = 0; j < entidad.coordenadasConeccion.size(); j++) {
                if(j==0 && i==0){
                    minimoRelacion = relacion.coordenadasConeccion.get(i);
                    minimoEntidad = entidad.coordenadasConeccion.get(j) ;
                }
                if(minimoEntidad.distance(minimoRelacion) > relacion.coordenadasConeccion.get(i).distance(entidad.coordenadasConeccion.get(j) )){                 
                   minimoRelacion = relacion.coordenadasConeccion.get(i);
                   minimoEntidad = entidad.coordenadasConeccion.get(j);
                }
            }
        }
        relacion.coordenadasConeccion.remove(minimoRelacion);
        //entidad.coordenadasConeccion.remove(minimoEntidad);
        
        unionEntidad = minimoEntidad;
        unionRelacion = minimoRelacion;
    }
    

    /**
     * dibuja graficamente la union de dos puntos, lo cual corresponde a la union de la entidad y relacion
     * @param gc GraphicsContext del diagrama
     */
    public void dibujarUnion(GraphicsContext gc){
        gc.strokeLine(unionRelacion.getX() , unionRelacion.getY() , unionEntidad.getX() , unionEntidad.getY());
        
    }
}