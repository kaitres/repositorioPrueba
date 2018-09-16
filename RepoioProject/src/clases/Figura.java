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
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author IP-ROUTE
 */
public  class Figura {
    ArrayList<Point2D> coordenadas = new ArrayList();
    String nombre;
    Point2D puntoCentral; 
    int lados;
    
    public ArrayList<Point2D> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(ArrayList<Point2D> coordenadas) {
        this.coordenadas = coordenadas;
    }
    public void dibujar(GraphicsContext gc){
        this.reCalcular();
        for (int x=0;x<coordenadas.size();x++) {
            if(x+1<coordenadas.size()){
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                        ,coordenadas.get(x+1).getX(), coordenadas.get(x+1).getY());
            }else{
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                    ,coordenadas.get(0).getX(), coordenadas.get(0).getY());
            }
            
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        
        if(lados!=-1){
            gc.fillText(nombre, coordenadas.get(0).getX(), (int)puntoCentral.getY());
        }else{
            
            gc.fillText(nombre, (int)puntoCentral.getX(), (int)puntoCentral.getY());
        }
        
        
    }
    public void rectangulo(int centroX,int centroY, int escala) {
        lados=-1;
        puntoCentral = new Point2D(centroX , centroY);
        int alto=20;
        
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(new Point2D(centroX-escala/2, centroY-alto/2));
        this.coordenadas.add(new Point2D(centroX+escala/2, centroY-alto/2));
        this.coordenadas.add(new Point2D(centroX+escala/2, centroY+alto/2));
        this.coordenadas.add(new Point2D(centroX-escala/2, centroY+alto/2));
    }
    
    public void crearFigura(int centroX , int centroY , int escala , int lados){
        puntoCentral = new Point2D(centroX , centroY);
        this.lados =lados;
        double angulo = 0;
        double anguloAux;
        int movX;
        int movY;
        this.coordenadas = new ArrayList<>();
        
        for (int i = 0; i < lados; i++ ) {
            if(angulo==0){
                this.coordenadas.add(new Point2D(centroX , centroY - escala));
            }
            else if (angulo > 0 && angulo < 90) {
                anguloAux = gradosRadianes(angulo);
                movX= (int) (escala * (sin(anguloAux)));
                movY= (int) ( (escala*  (cos(anguloAux))));
                this.coordenadas.add(new Point2D(centroX + movX , centroY - movY));
            }
            
            else if(angulo==90){
                this.coordenadas.add(new Point2D(centroX + escala , centroY));
            }
            
            else if (angulo >90 && angulo < 180) {
                anguloAux = gradosRadianes(180-angulo);
                movX= ((int) (escala*(sin(anguloAux))));
                movY= ((int) (escala*(cos(anguloAux))));
                this.coordenadas.add(new Point2D(centroX + movX , centroY + movY));
            }
            
            else if(angulo==180){
                this.coordenadas.add(new Point2D(centroX , centroY + escala));
            }
            
            else if (angulo >180 && angulo < 270) {
                anguloAux = gradosRadianes(angulo - 180);
                movX= ((int) (escala*(sin(anguloAux))));
                movY= ((int) (escala*(cos(anguloAux))));
                this.coordenadas.add(new Point2D(centroX - movX , centroY + movY));
            }
            
            else if(angulo==270){
                this.coordenadas.add(new Point2D(centroX - escala , centroY));
            }
            
            else if(angulo> 270 && angulo <360){
                anguloAux = gradosRadianes(360-angulo);
                movX= abs((int) (escala*sin((anguloAux))));
                movY= ((int) (escala*cos((anguloAux))));
                this.coordenadas.add(new Point2D(centroX - movX , centroY - movY));
            }
            angulo+=360/lados;
        }
    }
    public double gradosRadianes(double grados){
        return (grados*Math.PI)/180;
    }
    public void reCalcular(){
        int escala = nombre.length()*4;
        int centroX = (int)puntoCentral.getX();
        int centroY = (int)puntoCentral.getY();
        if(lados==-1){
            rectangulo(centroX, centroY, escala*2);
        }else{
            crearFigura(centroX, centroY, escala+4, lados);
        }
        
    }

    public void setPuntoCentral(Point2D puntoCentral) {
        this.puntoCentral = puntoCentral;
    }
    
}
    
    

