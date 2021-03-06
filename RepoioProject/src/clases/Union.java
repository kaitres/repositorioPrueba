/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static java.lang.Math.abs;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author The.N
 */
public class Union {
    Figura fig1;
    Figura fig2;
    Point2D unionFig2;
    Point2D unionFig1;
    boolean debil;

    
    public Union clon(){
        Union aux = new Union(fig1.clon(), fig2.clon());
        aux.setUnionFig1(unionFig1);
        aux.setUnionFig2(unionFig2);
        return aux;
    }
    public void setUnionFig2(Point2D unionFig2) {
        this.unionFig2 = unionFig2;
    }

    public void setUnionFig1(Point2D unionFig1) {
        this.unionFig1 = unionFig1;
    }
    public void setDebil(boolean debil) {
        this.debil = debil;
    }

    public Point2D getUnionFig2() {
        return unionFig2;
    }

    public Point2D getUnionFig1() {
        return unionFig1;
    }
    
    public void cardinalidad(String cardinalidad, GraphicsContext gc){
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        double puntoMedioX=this.unionFig1.midpoint(unionFig2).getX();
        double puntoMedioY=this.unionFig1.midpoint(unionFig2).getY();
        puntoMedioX=this.unionFig1.midpoint(new Point2D(puntoMedioX, puntoMedioY)).getX();
        puntoMedioY=this.unionFig1.midpoint(new Point2D(puntoMedioX, puntoMedioY)).getY();
        Figura rec = new Figura();
        rec.coordenadas.add(new Point2D(puntoMedioX-6, puntoMedioY-5));
        rec.coordenadas.add(new Point2D(puntoMedioX+6, puntoMedioY-5));
        gc.setStroke(Color.WHITE);
        rec.pintarRectangulo(gc);
        gc.setStroke(Color.BLACK);
        gc.fillText(cardinalidad, puntoMedioX, puntoMedioY);
        
 
         
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
        
        minFig1 = fig1.coordenadasConeccion.get(0);
        for (Point2D puntoFig1 : fig1.coordenadasConeccion) {
            if(fig2.puntoCentral.distance(minFig1) > fig2.puntoCentral.distance(puntoFig1)){                 
                minFig1 = puntoFig1;
            }
        }
        fig1.coordenadasConeccion.remove(minFig1);
        //unionFig2 = minFig2;
        unionFig2 = fig2.getPuntoCentral();
        unionFig1 = minFig1;
    }
}
