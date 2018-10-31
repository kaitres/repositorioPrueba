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
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author IP-ROUTE
 */
public  class Figura {
    ArrayList<Point2D> coordenadas = new ArrayList();
    ArrayList<Point2D> coordenadasConeccion = new ArrayList();
    ArrayList<Propiedad> propiedades;
    String nombre;
    Point2D puntoCentral; 
    int lados;
    
    public ArrayList<Point2D> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(ArrayList<Point2D> coordenadas) {
        this.coordenadas = coordenadas;
    }
    
    /**
     * metodo que crear los circulos de los puntos de control, a partir de la lista con las cooredenadas de la figura
     * @param gc GraphicsContext del diagrama
     */
    public void dibujarPuntoControl(GraphicsContext gc){
        Figura circulo = new Figura();
        for (Point2D coordenada : coordenadas) {
            circulo.crearFigura((int)(coordenada.getX()),(int)(coordenada.getY()), 2, 20);
            circulo.dibujarPoligono(gc,true);
        }
    }
    /**
     * metodo que dibuja el poligono correspndiente en la lista de coordenas del objeto de clase Figura
     * tambien dibuja los puntos de control que coinciden con los vertices de las figuras
     * @param gc GraphicsContext donde se trabaja el diagrama del programa
     */
    public void dibujar(GraphicsContext gc, boolean dibujarPuntos){
        dibujarPoligono(gc,false);
        if (dibujarPuntos) {
          dibujarPuntoControl(gc);  
        }
        //dobleLinea(gc);
        
        
    }
    
    /**
     * metodo que conecta graficamente los puntos de la lista de coordenas de la figura
     * @param gc GraphicsContext del diagrama
     * @param circulo booelan con el cual se puede identificar si se esta dibujando un circulo para no recalcular el tamaño de la figura
     */
    public void dibujarPoligono(GraphicsContext gc ,boolean circulo ){
        if(!circulo){
            this.reCalcular();
        }else{
            gc.setStroke(Color.RED);
        }
        
        for (int x=0;x<coordenadas.size();x++) {
            if(x+1<coordenadas.size()){
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                        ,coordenadas.get(x+1).getX(), coordenadas.get(x+1).getY());
            }else{
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                    ,coordenadas.get(0).getX(), coordenadas.get(0).getY());
            }
            
        }
        gc.setStroke(Color.BLACK);
        
               
    }
    
    public void elipse (int x, int y){
        int ancho = calEscala()+calEscala()/2;
        lados = -2;
        puntoCentral = new Point2D(x + (ancho/2), y + 15);
        coordenadas.clear();
        
        coordenadas.add(new Point2D(x, y));
        coordenadas.add(new Point2D(x + ancho, y));
        coordenadas.add(new Point2D(x, y + 30));
        coordenadas.add(new Point2D(x + ancho, y + 30));
   
    }
    
    public void dibujarElipse(GraphicsContext gc, Tipo tipo){
        if(tipo==Tipo.multivaluado){
            dobleLinea(gc); //revisar pene
        }
        if(tipo==Tipo.derivado){
            gc.setLineDashes(5);
        }
        pintarElipse(gc);
        int ancho = calEscala()+calEscala()/2;
        this.reCalcular();
        gc.setStroke(Color.BLACK);
        
        gc.strokeArc(coordenadas.get(0).getX(), coordenadas.get(0).getY(), ancho, 30, 0, 360, ArcType.OPEN);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        gc.fillText(nombre, coordenadas.get(0).getX()+ancho/2, coordenadas.get(0).getY()+15); 
        gc.setLineDashes(0);
        
    }
    
    /**
     * metodo que obtien las cooredenas de los vertices correspondientes del rectanguo (entidad) 
     * @param centroX int con la coordenada (eje x) del centro del rectangulo deseeado
     * @param centroY int con la coordenada (eje y) del centro del rectangulo deseeado
     * @param escala int con la escala de la figura
     */
    public void rectangulo(int centroX,int centroY, int escala) {
        lados=-1;
        puntoCentral = new Point2D(centroX , centroY);
        int alto=20;
        
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(new Point2D(centroX-escala/2, centroY-alto/2));
        this.coordenadas.add(new Point2D(centroX+escala/2, centroY-alto/2));
        this.coordenadas.add(new Point2D(centroX+escala/2, centroY+alto/2));
        this.coordenadas.add(new Point2D(centroX-escala/2, centroY+alto/2));
        coordenadasConeccion=(ArrayList<Point2D>) coordenadas.clone();
    }
    
    /**
     * metodo que obtien las cooredenas de los vertices correspondientes de la figura deseada (relacion)
     * @param centroX int con la coordenada (eje x) del centro de la figura deseeada
     * @param centroY int con la coordenada (eje y) del centro de la figura deseeada
     * @param escala int con la escala de la figura (largo de los lados)
     * @param lados  int con la cantidad de lados que tendra la figura
     */
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
        coordenadasConeccion=(ArrayList<Point2D>) coordenadas.clone();
    }
    
    /**
     * metodo que transforma grados a radianes
     * @param grados double con grados
     * @return  double con los grados transformados a radianes
     */
    public double gradosRadianes(double grados){
        return (grados*Math.PI)/180;
    }
    
    /**
     * metodo que llama a calEscala() con lo cual crear las figuras con la escala perfecta segun lo que se encuentre en su interior
     */
    public void reCalcular(){
        int escala = calEscala();
        int centroX = (int)puntoCentral.getX();
        int centroY = (int)puntoCentral.getY();
        if(lados==-1){
            rectangulo(centroX, centroY, escala*2);
        }else if(lados == -2){
            int ancho = calEscala()+calEscala()/2;
            elipse(centroX-ancho/2, centroY-15);
        }else{
            crearFigura(centroX, centroY, escala+2, lados);
        }
        
    }

    public void setPuntoCentral(Point2D puntoCentral) {
        this.puntoCentral = puntoCentral;
    }

    public String getNombre() {
        return nombre;
    }

    public Point2D getPuntoCentral() {
        return puntoCentral;
    }

    public int getLados() {
        return lados;
    }

    /**
     * metodo que encuentra la escala de la figura a partir del texto que esta tendra dentro de su interior
     * @return int con la escala calculada
     */
    public int calEscala(){
        Text text = new Text(this.nombre);
        if((int)text.getLayoutBounds().getWidth()<(int)text.getLayoutBounds().getHeight()){
            return (int)text.getLayoutBounds().getHeight();
        }
        return (int)text.getLayoutBounds().getWidth();
    }
    
    public int[] getXP(int lados){
        int[] aux = null;
        switch (lados) {
            case -1:
                aux = new int[4];
                aux[0]=1;
                aux[1]=-1;
                aux[2]=-1;
                aux[3]=1;
                break;
            case 4:
                aux = new int[4];
                aux[0]=0;
                aux[1]=-1;
                aux[2]=0;
                aux[3]=1;
                break;
            case 3:
                aux = new int[3];
                aux[0]=0;
                aux[1]=-1;
                aux[2]=1;
                
                break;
            case 5:
                aux = new int[5];
                aux[0]=0;
                aux[1]=-1;
                aux[2]=-1;
                aux[3]=1;
                aux[4]=1;
                break;
            default:
                aux = new int[6];
                aux[0]=0;
                aux[1]=-1;
                aux[2]=-1;
                aux[3]=0;
                aux[4]=1;
                aux[5]=1;
                break;
        }
        return aux;
    }
    public int[] getYP(int lados){
        int[] aux = null;
        switch (lados) {
            case -1:
                aux = new int[4];
                aux[0]=1;
                aux[1]=1;
                aux[2]=-1;
                aux[3]=-1;
                break;
            case 4:
                aux = new int[4];
                aux[0]=1;
                aux[1]=0;
                aux[2]=-1;
                aux[3]=0;
                break;
            case 3:
                aux = new int[3];
                aux[0]=1;
                aux[1]=-1;
                aux[2]=-1;
                
                break;
            case 5:
                aux = new int[5];
                aux[0]=1;
                aux[1]=0;
                aux[2]=-1;
                aux[3]=-1;
                aux[4]=0;
                break;
            default:
                aux = new int[6];
                aux[0]=1;
                aux[1]=1;
                aux[2]=-1;
                aux[3]=-1;
                aux[4]=-1;
                aux[5]=1;
                break;
        }
        return aux;
    }
    
    public void pintar(GraphicsContext gc){
        gc.setStroke(Color.WHITE);
        int[] xp = getXP(this.lados);
        int[] yp = getYP(this.lados);
        boolean ciclo = true;
        if(this.lados ==-2){
            pintarElipse(gc);
        }
        for (int i = 1; ciclo; i++) {
            for (int x=0;x<coordenadas.size();x++) {
                if(x+1<coordenadas.size()){
                    gc.strokeLine(coordenadas.get(x).getX()+xp[x]*i, coordenadas.get(x).getY()+yp[x]*i
                            ,coordenadas.get(x+1).getX()+xp[x+1]*i, coordenadas.get(x+1).getY()+yp[x+1]*i);
                }else{
                    gc.strokeLine(coordenadas.get(x).getX()+xp[x]*i, coordenadas.get(x).getY()+yp[x]*i
                        ,coordenadas.get(0).getX()+xp[0]*i, coordenadas.get(0).getY()+yp[0]*i);
                }
                if(coordenadas.get(1).getX()+xp[x]*i==puntoCentral.getX()){
                    gc.setStroke(Color.BLACK);
                    ciclo=false;
                    break;
                }
                if(this.lados==-1 && coordenadas.get(0).getY()+yp[x]*i==puntoCentral.getY()){
                    gc.setStroke(Color.BLACK);
                    ciclo=false;
                    break;
                }
            }
            
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        gc.fillText(nombre, (int)puntoCentral.getX(), (int)puntoCentral.getY());
    }
    
    
    public void tirarLinea(ArrayList<Propiedad> e, GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        for (Propiedad prop : e) {
            
            gc.strokeLine(this.puntoCentral.getX(), this.puntoCentral.getY(),
                    prop.elip.getPuntoCentral().getX(), prop.elip.getPuntoCentral().getY());
        }
        
    }
    
    public void poligonoDoble(GraphicsContext gc){
        int escala= calEscala()+4;
        int centroX = (int) this.puntoCentral.getX();
        int centroY = (int) this.puntoCentral.getY();
        
        double angulo = 0;
        double anguloAux;
        int movX;
        int movY;
        coordenadas = new ArrayList<>();
        
        for (int i = 0; i < lados; i++ ) {
            if(angulo==0){
                coordenadas.add(new Point2D(centroX , centroY - escala));
            }
            else if (angulo > 0 && angulo < 90) {
                anguloAux = gradosRadianes(angulo);
                movX= (int) (escala * (sin(anguloAux)));
                movY= (int) ( (escala*  (cos(anguloAux))));
                coordenadas.add(new Point2D(centroX + movX , centroY - movY));
            }
            
            else if(angulo==90){
                coordenadas.add(new Point2D(centroX + escala , centroY));
            }
            
            else if (angulo >90 && angulo < 180) {
                anguloAux = gradosRadianes(180-angulo);
                movX= ((int) (escala*(sin(anguloAux))));
                movY= ((int) (escala*(cos(anguloAux))));
                coordenadas.add(new Point2D(centroX + movX , centroY + movY));
            }
            
            else if(angulo==180){
                coordenadas.add(new Point2D(centroX , centroY + escala));
            }
            
            else if (angulo >180 && angulo < 270) {
                anguloAux = gradosRadianes(angulo - 180);
                movX= ((int) (escala*(sin(anguloAux))));
                movY= ((int) (escala*(cos(anguloAux))));
                coordenadas.add(new Point2D(centroX - movX , centroY + movY));
            }
            
            else if(angulo==270){
                coordenadas.add(new Point2D(centroX - escala , centroY));
            }
            
            else if(angulo> 270 && angulo <360){
                anguloAux = gradosRadianes(360-angulo);
                movX= abs((int) (escala*sin((anguloAux))));
                movY= ((int) (escala*cos((anguloAux))));
                coordenadas.add(new Point2D(centroX - movX , centroY - movY));
            }
            angulo+=360/lados;
        }
        for (int x=0;x<coordenadas.size();x++) {
            if(x+1<coordenadas.size()){
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                        ,coordenadas.get(x+1).getX(), coordenadas.get(x+1).getY());
            }else{
                gc.strokeLine(coordenadas.get(x).getX(), coordenadas.get(x).getY()
                    ,coordenadas.get(0).getX(), coordenadas.get(0).getY());
            }
            
        }

    }
    
    public void dobleLinea(GraphicsContext gc){
        if(this.lados == -2){//elipse
            
            int ancho = (calEscala()+calEscala()/2);
            this.reCalcular();
            gc.setStroke(Color.BLACK);
            gc.strokeArc(coordenadas.get(0).getX() - 2, coordenadas.get(0).getY() -2 , ancho + 4 , 30 + 4 , 0, 360, ArcType.OPEN);
        }
        if(this.lados == -1){//rectangulo
            int alto=20;
            int escala=calEscala()*2;
            gc.strokeLine(this.puntoCentral.getX()- escala/2 -2 , this.puntoCentral.getY() - alto/2 -2, this.puntoCentral.getX()+ escala/2 +2, this.puntoCentral.getY() - alto/2 -2);
            gc.strokeLine(this.puntoCentral.getX()+ escala/2 +2, this.puntoCentral.getY() - alto/2 -2, this.puntoCentral.getX()+ escala/2 +2, this.puntoCentral.getY() + alto/2 +2);
            gc.strokeLine(this.puntoCentral.getX()+ escala/2 +2, this.puntoCentral.getY() + alto/2 +2, this.puntoCentral.getX()- escala/2 -2, this.puntoCentral.getY() + alto/2 +2);
            gc.strokeLine(this.puntoCentral.getX()- escala/2 -2, this.puntoCentral.getY() + alto/2 +2, this.puntoCentral.getX()- escala/2 -2, this.puntoCentral.getY() - alto/2 -2);


        }
        else{//figura
            poligonoDoble(gc);
            
            
        }
        
    }
    public void pintarElipse(GraphicsContext gc){
        int ancho = (calEscala()+calEscala()/2);
        
        int i=0;
        int alto=30;
        this.reCalcular();
        gc.setStroke(Color.WHITE);
        for (int j = 1; j < 29; j++) {
            gc.strokeArc(coordenadas.get(0).getX()+j, coordenadas.get(0).getY()+j, ancho-2*j, alto-2*j, 0, 360, ArcType.OPEN);
        }
        //while(i<=30){
            
            /**System.out.println("ancho = "+ ancho);
            
            gc.setStroke(Color.RED);
            if (alto == 0){
                gc.strokeArc(coordenadas.get(0).getX(), coordenadas.get(0).getY(), ancho, 0, 0, 360, ArcType.OPEN);
            }
            else{
                alto-=1;
            gc.strokeArc(coordenadas.get(0).getX(), coordenadas.get(0).getY(), ancho-2*i, alto, 0, 360, ArcType.OPEN);
            }
            ancho-=2;
            i+=1;
            * */
            
        //}
        
    }
}
    
    

