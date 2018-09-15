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
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author IP-ROUTE
 */
public  class Figura {
    ArrayList<Point2D> coordenadas = new ArrayList();
    String nombre;
    public ArrayList<Point2D> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(ArrayList<Point2D> coordenadas) {
        this.coordenadas = coordenadas;
    }
    public void dibujar(GraphicsContext gc){
        for (int x=0;x<coordenadas.size();x++) {
            if(x+1<coordenadas.size()){
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                        ,coordenadas.get(x+1).getX(), coordenadas.get(x+1).getY());
            }else{
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                    ,coordenadas.get(0).getX(), coordenadas.get(0).getY());
            }
            
        }
        
        gc.strokeText(nombre, coordenadas.get(0).getX()+5, coordenadas.get(0).getY()+12,40);
        
    }
    public void rectangulo(int centroX,int centroY, int escala) {
        int alto=50;
        
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(new Point2D(centroX-alto/2, centroY-escala/2));
        this.coordenadas.add(new Point2D(centroX+alto/2, centroY-escala/2));
        this.coordenadas.add(new Point2D(centroX+alto/2, centroY+escala/2));
        this.coordenadas.add(new Point2D(centroX-alto/2, centroY+escala/2));
    }
    
    public void crearFigura(int centroX , int centroY , int escala , int lados){
        double angulo = 0;
        double anguloAux;
        int movX;
        int movY;
        
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
}
    
    

