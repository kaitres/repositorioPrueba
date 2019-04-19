/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import controladores.InterfazController;
import static controladores.InterfazController.diagrama;
import static controladores.InterfazController.mostrarPuntos;
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
 * hola mama estoy en discord
 */
public  class Figura {
    ArrayList<Point2D> coordenadas = new ArrayList();
    ArrayList<Point2D> coordenadasConeccion = new ArrayList();
    ArrayList<Propiedad> propiedades =  new ArrayList();
    String nombre;
    Point2D puntoCentral; 
    int lados;
    boolean debil = false;
    //agrupacion
    ArrayList<Point2D> cordCuadradoMov = new ArrayList();

    
    
    public Figura clon(){
        Figura aux = new Figura();
  
        //aux.setCoordenadas((ArrayList<Point2D>) coordenadas.clone());
        for (Point2D coordenada : coordenadas) {
            Point2D pAux = new Point2D(coordenada.getX(), coordenada.getY());
            aux.coordenadas.add(pAux);
        }
        for (Point2D coordenada : coordenadasConeccion) {
            Point2D pAux = new Point2D(coordenada.getX(), coordenada.getY());
            aux.coordenadasConeccion.add(pAux);
        }
        //aux.setCoordenadasConeccion((ArrayList<Point2D>) coordenadasConeccion.clone());
        for (Propiedad propiedad : propiedades) {
            aux.propiedades.add(propiedad.clon());
        }
        aux.nombre= nombre;
        aux.setPuntoCentral(puntoCentral);
        aux.setDebil(debil);
        aux.setLados(lados);
        return aux;
        
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Point2D> getCordCuadradoMov() {
        return cordCuadradoMov;
    }

    public void setLados(int lados) {
        this.lados = lados;
    }
    
    public void setDebil(boolean debil) {
        this.debil = debil;
    }

    public boolean isDebil() {
        return debil;
    }
    
    
    
    public ArrayList<Point2D> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(ArrayList<Point2D> coordenadas) {
        this.coordenadas = coordenadas;
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
    
    public void elipse (int x, int y){
        int ancho = calEscala()+calEscala()/2;
        lados = -2;
        puntoCentral = new Point2D(x + (ancho/2), y + 15);
        coordenadas.clear();
        
        coordenadas.add(new Point2D(x, y));
        coordenadas.add(new Point2D(x + ancho, y));
        coordenadas.add(new Point2D(x + ancho, y + 30));
        coordenadas.add(new Point2D(x, y + 30));
   
    }    
    public void circulo(int x, int y){
        this.lados=-3;
        this.puntoCentral= new Point2D(x, y);
        int largo = calEscala();
        coordenadas.clear();
        coordenadas.add(new Point2D(puntoCentral.getX()-largo, puntoCentral.getY()-largo));
        coordenadas.add(new Point2D(puntoCentral.getX()+largo, puntoCentral.getY()-largo));
        coordenadas.add(new Point2D(puntoCentral.getX()+largo, puntoCentral.getY()+largo));
        coordenadas.add(new Point2D(puntoCentral.getX()-largo, puntoCentral.getY()+largo));
        
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
        }else if(lados== -3){
                circulo(centroX, centroY);
            }else{
                crearFigura(centroX, centroY, escala+2, lados);
        }
        
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
        if (debil){
            dobleLinea(gc);
        }
    }
    /**
     * metodo que conecta graficamente los puntos de la lista de coordenas de la figura
     * @param gc GraphicsContext del diagrama
     * @param circulo booelan con el cual se puede identificar si se esta dibujando un circulo para no recalcular el tamaño de la figura
     */
    public void dibujarPoligono(GraphicsContext gc ,boolean circulo ){
        gc.setStroke(Color.BLACK);
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
        if (tipo==Tipo.clave) {
            gc.strokeLine(coordenadas.get(0).getX()+7, coordenadas.get(0).getY()+23,
                    coordenadas.get(1).getX()-7, coordenadas.get(0).getY()+23);
        }
        if (tipo==Tipo.parcial){
            gc.setLineDashes(5);
            gc.strokeLine(coordenadas.get(0).getX()+7, coordenadas.get(0).getY()+23,
                    coordenadas.get(1).getX()-7, coordenadas.get(0).getY()+23);
            gc.setLineDashes(0);
        }
        gc.setLineDashes(0);
        
    }
    public void dibujarCirculo(GraphicsContext gc){
        reCalcular();
        int largo = (int) (coordenadas.get(1).getX()-coordenadas.get(0).getX());
        gc.strokeArc(coordenadas.get(0).getX(), coordenadas.get(0).getY(), largo, largo, 0, 360, ArcType.OPEN);
        gc.setStroke(Color.WHITE);
        for (int j = 1; j < largo-1; j++) {
            gc.strokeArc(coordenadas.get(0).getX()+j, coordenadas.get(0).getY()+j, largo-2*j, largo-2*j, 0, 360, ArcType.OPEN);
        }
        gc.setStroke(Color.BLACK);
        //gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        gc.fillText(nombre, (int)puntoCentral.getX(), (int)puntoCentral.getY());
        
    }
    
    public void pintar(GraphicsContext gc){
        gc.setStroke(Color.WHITE);
        if(this.lados ==-2){
            pintarElipse(gc);
        }
        if(this.lados ==-1){
            pintarRectangulo(gc);
        }
        else{
            pintarPoligono(gc);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        gc.fillText(nombre, (int)puntoCentral.getX(), (int)puntoCentral.getY());
    }
    public void pintarElipse(GraphicsContext gc){
        int ancho = (calEscala()+calEscala()/2);
        int alto=30;
        this.reCalcular();
        gc.setStroke(Color.WHITE);
        for (int j = 1; j < 29; j++) {
            gc.strokeArc(coordenadas.get(0).getX()+j, coordenadas.get(0).getY()+j, ancho-2*j, alto-2*j, 0, 360, ArcType.OPEN);
        }
    }
    
    public void tirarLinea(GraphicsContext gc, Entidad e){
        gc.setStroke(Color.BLACK);
        gc.strokeLine(this.puntoCentral.getX(), this.puntoCentral.getY(),
                    e.getFigura().getPuntoCentral().getX(), e.getFigura().getPuntoCentral().getY());
    }
    public void tirarLinea(ArrayList<Propiedad> e, GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        for (Propiedad prop : e) {
            
            gc.strokeLine(this.puntoCentral.getX(), this.puntoCentral.getY(),
                    prop.elip.getPuntoCentral().getX(), prop.elip.getPuntoCentral().getY());
            if(prop.getPropiedades()!=null){
                for (Propiedad prop2 : prop.getPropiedades()) {
                    gc.strokeLine(prop.elip.getPuntoCentral().getX(), prop.elip.getPuntoCentral().getY(),
                        prop2.elip.getPuntoCentral().getX(), prop2.elip.getPuntoCentral().getY());
            
                }
            }
        }
        
    }  
    public void tirarLinea(GraphicsContext gc,ArrayList<Entidad> a){
        gc.setStroke(Color.BLACK);
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        Point2D pM ;
        Point2D aux ;
        for (int i = 0; i < a.size(); i++) {
            gc.strokeLine(this.puntoCentral.getX(), this.puntoCentral.getY(),
                    a.get(i).getFigura().getPuntoCentral().getX(), a.get(i).getFigura().getPuntoCentral().getY());
            
                x1 = (int) this.puntoCentral.getX();
                y1 = (int) this.puntoCentral.getY();
                x2 = (int) a.get(i).figura.puntoCentral.getX();
                y2 = (int) a.get(i).figura.puntoCentral.getY();
                pM = this.puntoCentral.midpoint(a.get(i).figura.puntoCentral);
                aux = new Point2D(x1, y2);
                double angulo = 0;

                if( x2 > x1 && y2 < y1){
                    angulo = (a.get(i).figura.puntoCentral.angle(this.puntoCentral, aux)-90);
                }

                else if( x2 < x1 && y2 < y1 ){
                    angulo = (this.puntoCentral.angle(a.get(i).figura.puntoCentral, aux));
                }

                else if(x2 < x1 && y2 == y1){
                    angulo=90;
                }     
                else if( x2 < x1 && y2 > y1){
                    angulo = (a.get(i).figura.puntoCentral.angle(this.puntoCentral, aux)+90);
                }
                else if(x2 == x1 && y2 > y1){
                    angulo=180;
                }  
                else if( x2 > x1 && y2 > y1){
                    angulo = (this.puntoCentral.angle(a.get(i).figura.puntoCentral, aux)+180);        
                }
                else if(x2 > x1 && y2 == y1){
                    angulo=270;
                }  
                if(angulo <0){
                        angulo=360 - abs(angulo);
                    }
                gc.strokeArc(pM.getX()-15, pM.getY()-15, 30, 30, angulo, 180, ArcType.OPEN);
            
        }
       
    }
    public void tirarLinea(GraphicsContext gc, Union u){
        gc.strokeLine(u.getUnionFig1().getX() , u.getUnionFig1().getY() , u.getUnionFig2().getX() , u.getUnionFig2().getY());
    }
    public void tirarDobleLinea(GraphicsContext gc , Union u){
        gc.setLineWidth(3);
        gc.strokeLine(u.getUnionFig1().getX() , u.getUnionFig1().getY() , u.getUnionFig2().getX() , u.getUnionFig2().getY());
        gc.setLineWidth(1);
        gc.setStroke(Color.WHITE);
        gc.strokeLine(u.getUnionFig1().getX() , u.getUnionFig1().getY() , u.getUnionFig2().getX() , u.getUnionFig2().getY());
        gc.setStroke(Color.BLACK);
    }
    
    public void poligonoDoble(GraphicsContext gc){
        int escala= calEscala()+5;
        int centroX = (int) this.puntoCentral.getX();
        int centroY = (int) this.puntoCentral.getY();
        
        double angulo = 0;
        double anguloAux;
        int movX;
        int movY;
        ArrayList<Point2D>coordenadasPintar = new ArrayList<>();
        
        for (int i = 0; i < lados; i++ ) {
            if(angulo==0){
                coordenadasPintar.add(new Point2D(centroX , centroY - escala));
            }
            else if (angulo > 0 && angulo < 90) {
                anguloAux = gradosRadianes(angulo);
                movX= (int) (escala * (sin(anguloAux)));
                movY= (int) ( (escala*  (cos(anguloAux))));
                coordenadasPintar.add(new Point2D(centroX + movX , centroY - movY));
            }
            
            else if(angulo==90){
                coordenadasPintar.add(new Point2D(centroX + escala , centroY));
            }
            
            else if (angulo >90 && angulo < 180) {
                anguloAux = gradosRadianes(180-angulo);
                movX= ((int) (escala*(sin(anguloAux))));
                movY= ((int) (escala*(cos(anguloAux))));
                coordenadasPintar.add(new Point2D(centroX + movX , centroY + movY));
            }
            
            else if(angulo==180){
                coordenadasPintar.add(new Point2D(centroX , centroY + escala));
            }
            
            else if (angulo >180 && angulo < 270) {
                anguloAux = gradosRadianes(angulo - 180);
                movX= ((int) (escala*(sin(anguloAux))));
                movY= ((int) (escala*(cos(anguloAux))));
                coordenadasPintar.add(new Point2D(centroX - movX , centroY + movY));
            }
            
            else if(angulo==270){
                coordenadasPintar.add(new Point2D(centroX - escala , centroY));
            }
            
            else if(angulo> 270 && angulo <360){
                anguloAux = gradosRadianes(360-angulo);
                movX= abs((int) (escala*sin((anguloAux))));
                movY= ((int) (escala*cos((anguloAux))));
                coordenadasPintar.add(new Point2D(centroX - movX , centroY - movY));
            }
            angulo+=360/lados;
        }
        
        for (int x=0;x<coordenadasPintar.size();x++) {
            if(x+1<coordenadasPintar.size()){
                gc.strokeLine(coordenadasPintar.get(x).getX(), coordenadasPintar.get(x).getY()
                        ,coordenadasPintar.get(x+1).getX(), coordenadasPintar.get(x+1).getY());
            }else{
                gc.strokeLine(coordenadasPintar.get(x).getX(), coordenadasPintar.get(x).getY()
                    ,coordenadasPintar.get(0).getX(), coordenadasPintar.get(0).getY());
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
        else{
            
            poligonoDoble(gc);
            
            
        }
        
    }
    
    public void pintarPoligono (GraphicsContext gc){
        double escala= calEscala()+1;
        int centroX = (int) this.puntoCentral.getX();
        int centroY = (int) this.puntoCentral.getY();
        //gc.setStroke(Color.BLACK);
        
        while(escala>=0){
            double angulo = 0;
            double anguloAux;
            int movX;
            int movY;
            ArrayList<Point2D>coordenadasPintar = new ArrayList<>();

            for (int i = 0; i < lados; i++ ) {
                if(angulo==0){
                    coordenadasPintar.add(new Point2D(centroX , centroY - escala));
                }
                else if (angulo > 0 && angulo < 90) {
                    anguloAux = gradosRadianes(angulo);
                    movX= (int) (escala * (sin(anguloAux)));
                    movY= (int) ( (escala*  (cos(anguloAux))));
                    coordenadasPintar.add(new Point2D(centroX + movX , centroY - movY));
                }

                else if(angulo==90){
                    coordenadasPintar.add(new Point2D(centroX + escala , centroY));
                }

                else if (angulo >90 && angulo < 180) {
                    anguloAux = gradosRadianes(180-angulo);
                    movX= ((int) (escala*(sin(anguloAux))));
                    movY= ((int) (escala*(cos(anguloAux))));
                    coordenadasPintar.add(new Point2D(centroX + movX , centroY + movY));
                }

                else if(angulo==180){
                    coordenadasPintar.add(new Point2D(centroX , centroY + escala));
                }

                else if (angulo >180 && angulo < 270) {
                    anguloAux = gradosRadianes(angulo - 180);
                    movX= ((int) (escala*(sin(anguloAux))));
                    movY= ((int) (escala*(cos(anguloAux))));
                    coordenadasPintar.add(new Point2D(centroX - movX , centroY + movY));
                }

                else if(angulo==270){
                    coordenadasPintar.add(new Point2D(centroX - escala , centroY));
                }

                else if(angulo> 270 && angulo <360){
                    anguloAux = gradosRadianes(360-angulo);
                    movX= abs((int) (escala*sin((anguloAux))));
                    movY= ((int) (escala*cos((anguloAux))));
                    coordenadasPintar.add(new Point2D(centroX - movX , centroY - movY));
                }
                angulo+=360/lados;
            }

            for (int x=0;x<coordenadasPintar.size();x++) {
                if(x+1<coordenadasPintar.size()){
                    gc.strokeLine(coordenadasPintar.get(x).getX(), coordenadasPintar.get(x).getY()
                            ,coordenadasPintar.get(x+1).getX(), coordenadasPintar.get(x+1).getY());
                }else{
                    gc.strokeLine(coordenadasPintar.get(x).getX(), coordenadasPintar.get(x).getY()
                        ,coordenadasPintar.get(0).getX(), coordenadasPintar.get(0).getY());
                }

            }
            escala-=0.25;
        }
    }
    
    public void pintarRectangulo(GraphicsContext gc){

        Point2D p1 = coordenadas.get(0);
        Point2D p2 = coordenadas.get(1);
        
        p1= new Point2D(p1.getX()+1, p1.getY()+1);
        while(p1.getX() < p2.getX()){
            gc.strokeLine(p1.getX() , p1.getY() ,p1.getX() , p1.getY()+18 );
            p1 = new Point2D(p1.getX()+1 ,p1.getY());
        }
        
    }
    
    // -----------------------------------agrupacion--------------------------------
    private Point2D getMayor(Relacion relacion){
        double XMayor = relacion.getFigura().getCoordenadas().get(0).getX();
        double YMayor = relacion.getFigura().getCoordenadas().get(0).getY();
        for (Propiedad prop : relacion.getPropiedades()) {
            for (Point2D cord : prop.getElip().getCoordenadas()) {
                if(cord.getX()> XMayor){
                    XMayor = cord.getX();
                }
                if(cord.getY()> YMayor){
                    YMayor = cord.getY();
                }  
            }
            if(prop.tipo == Tipo.compuesto){
                        for (Propiedad propiedade : prop.propiedades) {
                            for (Point2D cord : propiedade.getElip().getCoordenadas()) {
                                if(cord.getX()> XMayor){
                                    XMayor = cord.getX();
                                }
                                if(cord.getY() > YMayor){
                                    YMayor = cord.getY();
                                }  
                            }
                        }
                    }
        }
        
        for (Point2D cord : relacion.getFigura().getCoordenadas()) {
            if(cord.getX()> XMayor){
                XMayor = (int)cord.getX();
            }
            if(cord.getY()> YMayor){
                YMayor = (int)cord.getY();
            }
        }
        for (Entidad entidad : relacion.getComponentes()) {
             if(!(entidad instanceof Agrupacion)){
                for (Point2D cord : entidad.getFigura().getCoordenadas()) {
                    if(cord.getX()> XMayor){
                        XMayor = cord.getX();
                    }
                    if(cord.getY()> YMayor){
                        YMayor = cord.getY();
                    }  
                }
                for (Propiedad prop : entidad.getPropiedades()){
                    for (Point2D cord : prop.getElip().getCoordenadas()) {
                        if(cord.getX()> XMayor){
                            XMayor = cord.getX();
                        }
                        if(cord.getY()> YMayor){
                            YMayor = cord.getY();
                        }  
                    }
                    if(prop.tipo == Tipo.compuesto){
                        for (Propiedad propiedade : prop.propiedades) {
                            for (Point2D cord : propiedade.getElip().getCoordenadas()) {
                                if(cord.getX()> XMayor){
                                    XMayor = cord.getX();
                                }
                                if(cord.getY() > YMayor){
                                    YMayor = cord.getY();
                                }  
                            }
                        }
                    }
                }
            }
            else{
                ((Agrupacion)entidad).figura.obtenerCordenadas(((Agrupacion)entidad).getRelacion());
                for (Point2D cord : ((Agrupacion)entidad).getFigura().getCoordenadas()) {
                    if(cord.getX()> XMayor){
                        XMayor = cord.getX();
                    }
                    if(cord.getY()> YMayor){
                        YMayor = cord.getY();
                    }
                }
            }
        }      

        return new Point2D(XMayor, YMayor);
    }
    private Point2D getMenor(Relacion relacion){
        double XMenor = relacion.getFigura().getCoordenadas().get(0).getX();
        double YMenor = relacion.getFigura().getCoordenadas().get(0).getY();
        for (Propiedad prop : relacion.getPropiedades()) {
            for (Point2D cord : prop.getElip().getCoordenadas()) {
                if(cord.getX()< XMenor){
                    XMenor = cord.getX();
                }
                if(cord.getY()< YMenor){
                    YMenor = cord.getY();
                }  
            }
            if(prop.tipo == Tipo.compuesto){
                        for (Propiedad propiedade : prop.propiedades) {
                            for (Point2D cord : propiedade.getElip().getCoordenadas()) {
                                if(cord.getX()< XMenor){
                                    XMenor = cord.getX();
                                }
                                if(cord.getY()< YMenor){
                                    YMenor = cord.getY();
                                }  
                            }
                        }
                    }
        }
        for (Point2D cord : relacion.getFigura().getCoordenadas()) {
            if(cord.getX()< XMenor){
                XMenor = cord.getX();
            }
            if(cord.getY()< YMenor){
                YMenor = cord.getY();
            }              
        }
        for (Object entidad : relacion.getComponentes()) {
            if(!(entidad instanceof Agrupacion)){
                for (Point2D cord : ((Entidad)entidad).getFigura().getCoordenadas()) {
                    if(cord.getX()< XMenor){
                        XMenor = cord.getX();
                    }
                    if(cord.getY()< YMenor){
                        YMenor = cord.getY();
                    }
                }
                for (Propiedad prop : ((Entidad)entidad).getPropiedades()){
                    
                    for (Point2D cord : prop.getElip().getCoordenadas()) {
                        if(cord.getX()< XMenor){
                            XMenor = cord.getX();
                        }
                        if(cord.getY()< YMenor){
                            YMenor = cord.getY();
                        }  
                    }
                    if(prop.tipo == Tipo.compuesto){
                        for (Propiedad propiedade : prop.propiedades) {
                            for (Point2D cord : propiedade.getElip().getCoordenadas()) {
                                if(cord.getX()< XMenor){
                                    XMenor = cord.getX();
                                }
                                if(cord.getY()< YMenor){
                                    YMenor = cord.getY();
                                }  
                            }
                        }
                    }
                }
            } 
            else{
                ((Agrupacion)entidad).figura.obtenerCordenadas(((Agrupacion)entidad).getRelacion());
                for (Point2D cord : ((Agrupacion)entidad).getFigura().getCoordenadas()) {
                    if(cord.getX()< XMenor){
                        XMenor = cord.getX();
                    }
                    if(cord.getY()< YMenor){
                        YMenor = cord.getY();
                    }
                }
            }
        }

        return new Point2D(XMenor, YMenor);
    }
    protected void obtenerCordenadas(Relacion relacion){
        Point2D menor = getMenor(relacion);
        Point2D mayor = getMayor(relacion);
        coordenadasConeccion.clear();
        coordenadas.clear();
        coordenadas.add(new Point2D(menor.getX() - 10  , menor.getY() - 10 ));
        coordenadas.add(new Point2D(mayor.getX() + 10  , menor.getY() - 10 ));
        coordenadas.add(new Point2D(mayor.getX() + 10  , mayor.getY() + 10 ));
        coordenadas.add(new Point2D(menor.getX() - 10  , mayor.getY() + 10 ));
        puntoCentral = menor.midpoint(mayor);
        coordenadasConeccion = (ArrayList<Point2D>) coordenadas.clone();
        
    }
    protected void dibujarMarco(GraphicsContext gc,Relacion relacion){
        gc.setStroke(Color.BLACK);
        obtenerCordenadas(relacion);
        gc.strokeLine(coordenadas.get(0).getX(), coordenadas.get(0).getY(), coordenadas.get(1).getX(), coordenadas.get(1).getY());
        gc.strokeLine(coordenadas.get(1).getX(), coordenadas.get(1).getY(), coordenadas.get(2).getX(), coordenadas.get(2).getY());
        gc.strokeLine(coordenadas.get(2).getX(), coordenadas.get(2).getY(), coordenadas.get(3).getX(), coordenadas.get(3).getY());
        gc.strokeLine(coordenadas.get(3).getX(), coordenadas.get(3).getY(), coordenadas.get(0).getX(), coordenadas.get(0).getY());
        Point2D p1 = coordenadas.get(0);
        Point2D p2 = coordenadas.get(1);
        int largo = (int) (coordenadas.get(2).getY() - coordenadas.get(1).getY());
        
        gc.setStroke(Color.WHITE);
        p1= new Point2D(p1.getX()+1, p1.getY()+1);
        while(p1.getX() < p2.getX()){
            gc.strokeLine(p1.getX() , p1.getY() ,p1.getX() , p1.getY()+ largo-2 );
            p1 = new Point2D(p1.getX()+1 ,p1.getY());
        }
        Point2D pCentro = coordenadas.get(1);
        cordCuadradoMov.clear();
        cordCuadradoMov.add(new Point2D(pCentro.getX() - 10 , pCentro.getY() - 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() + 10 , pCentro.getY() - 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() + 10 , pCentro.getY() + 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() - 10 , pCentro.getY() + 10 ));
        dibujarCuadrado(gc, cordCuadradoMov);
        
        //ipntarCuadrados(gc , coordenadas);
    }  
    /*private void pintarCuadrados(GraphicsContext gc ,Relacion relacion) {
        obtenerCordenadas( relacion);
        Point2D p1 = coordenadas.get(0);
        Point2D p2 = coordenadas.get(1);
        int largo = (int) (coordenadas.get(2).getY() - coordenadas.get(1).getY());
        
        gc.setStroke(Color.WHITE);
        p1= new Point2D(p1.getX()+1, p1.getY()+1);
        while(p1.getX() < p2.getX()){
            gc.strokeLine(p1.getX() , p1.getY() ,p1.getX() , p1.getY()+ largo-2 );
            p1 = new Point2D(p1.getX()+1 ,p1.getY());
        }
    }
   */
    
    private void CuadradoMov(GraphicsContext gc){
        Point2D pCentro = coordenadas.get(1);
        cordCuadradoMov.clear();
        cordCuadradoMov.add(new Point2D(pCentro.getX() - 10 , pCentro.getY() - 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() + 10 , pCentro.getY() - 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() + 10 , pCentro.getY() + 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() - 10 , pCentro.getY() + 10 ));
        dibujarCuadrado(gc, cordCuadradoMov);
       
        
    }
    protected void dibujarCuadrado(GraphicsContext gc, ArrayList<Point2D> cordCuadradoMov) {
        gc.setStroke(Color.BLACK);
        gc.strokeLine(cordCuadradoMov.get(0).getX(), cordCuadradoMov.get(0).getY(), cordCuadradoMov.get(1).getX(), cordCuadradoMov.get(1).getY());
        gc.strokeLine(cordCuadradoMov.get(1).getX(), cordCuadradoMov.get(1).getY(), cordCuadradoMov.get(2).getX(), cordCuadradoMov.get(2).getY());
        gc.strokeLine(cordCuadradoMov.get(2).getX(), cordCuadradoMov.get(2).getY(), cordCuadradoMov.get(3).getX(), cordCuadradoMov.get(3).getY());
        gc.strokeLine(cordCuadradoMov.get(3).getX(), cordCuadradoMov.get(3).getY(), cordCuadradoMov.get(0).getX(), cordCuadradoMov.get(0).getY());
       // pintarCuadrados(gc, cordCuadradoMov);
    }
    public void dibujar(GraphicsContext gc , Relacion relacion , Agrupacion agru){
        if (InterfazController.mostrarPuntos) {
          dibujarPuntoControl(gc);  
        }
        dibujarMarco(gc, relacion);
         CuadradoMov(gc);
         dibujarInterior(gc , relacion);
         reDibujarTodo(gc , agru);
         
                 
    }
    public void reDibujarTodo(GraphicsContext gc , Agrupacion agru){
        
        
        Relacion relacion = agru.relacion;
        agru.figura.dibujarMarco(gc, relacion);
        relacion.correccion();
        relacion.metamorfosear();
        relacion.getFigura().dibujar(gc,mostrarPuntos);
        relacion.crearUniones();
        relacion.f(gc);
        
        gc.setStroke(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        
        
        for (Propiedad prop : relacion.getPropiedades()){
            prop.getElip().dibujarElipse(gc, prop.getTipo());
            if(prop.getPropiedades()!=null){
                for(Propiedad prop2 : prop.getPropiedades()){
                    prop2.getElip().dibujarElipse(gc, prop2.getTipo());

                }
            }
        }
        relacion.getFigura().dibujar(gc,mostrarPuntos);
        relacion.getFigura().pintar(gc);
        System.out.println(nombre);
        gc.fillText(nombre, coordenadas.get(0).getX() ,coordenadas.get(0).getY()-10 );
         for (int i = 0; i < relacion.componentes.size(); i++) {
            if(relacion.componentes.get(i) instanceof Agrupacion){
                reDibujarTodo(gc, ((Agrupacion)relacion.componentes.get(i)));
            }
            else{
                relacion.componentes.get(i).getFigura().dibujar(gc, mostrarPuntos);
                relacion.componentes.get(i).f(gc);
                relacion.componentes.get(i).getFigura().pintar(gc);
                for (Propiedad prop : relacion.componentes.get(i).getPropiedades()){
                    prop.getElip().dibujarElipse(gc, prop.getTipo());
                    if(prop.getPropiedades()!=null){
                        for(Propiedad prop2 : prop.getPropiedades()){
                            prop2.getElip().dibujarElipse(gc, prop2.getTipo());
                        }
                    }
                }
                
            }
        }
        
    
    }
    protected void dibujarInterior(GraphicsContext gc , Relacion relacion) {
        relacion.getFigura().dibujar(gc, InterfazController.mostrarPuntos);
        relacion.figura.pintar(gc);
        for(Entidad entidad : relacion.getComponentes()){
            entidad.getFigura().dibujar(gc, InterfazController.mostrarPuntos);
            entidad.figura.pintar(gc);
        }
    }
}