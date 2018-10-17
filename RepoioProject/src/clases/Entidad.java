/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author IP-ROUTE
 */
public class Entidad {
    String nombre;
    Figura figura; //puede ser Rectangulo ya que solo se puede representar de esa manera
    ArrayList<String> propiedades;

    public Entidad(String nombre) {//Lo hizo el Carlos UwU
        this.nombre = nombre;
    }
    public ArrayList<String> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<String> propiedades) {
        this.propiedades = (ArrayList<String>) propiedades.clone();
        this.figura.propiedades = this.propiedades;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.figura.nombre= nombre;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
        this.figura.nombre = nombre;
    }

    public Figura getFigura() {
        return figura;
    }
    
    
}
