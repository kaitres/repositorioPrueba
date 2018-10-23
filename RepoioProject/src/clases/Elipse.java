/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

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
 * @author cmsan
 */
public class Elipse{
    String nombre;
    int x;
    int y;
    int ancho;

    public Elipse(String nombre) {
        this.nombre = nombre;
        this.ancho = calcularAncho()+calcularAncho()/4;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    

    
    public void dibujarElipse (GraphicsContext gc, int x, int y){
        this.x = x;
        this.y = y;
        gc.setStroke(Color.BLACK);
        gc.strokeArc(x, y, ancho, 30, 0, 360, ArcType.OPEN);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(15));
        gc.fillText(nombre, x+ancho/2, y+15);
    }
    
    public void setPuntoCentral(Point2D punto){
        this.x = (int)(punto.getX())-(ancho/2);
        this.y = (int)(punto.getY())-15;
    }
    
    private int calcularAncho(){
        Text text = new Text(this.nombre);
        if((int)text.getLayoutBounds().getWidth()<(int)text.getLayoutBounds().getHeight()){
            return (int)text.getLayoutBounds().getHeight();
        }
        return (int)text.getLayoutBounds().getWidth();
    }
}
