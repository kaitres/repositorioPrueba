/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author IP-ROUTE
 */
public class Entidad {
    String nombre;
    Figura figura; //puede ser Rectangulo ya que solo se puede representar de esa manera

    public Entidad(String nombre) {//Lo hizo el Carlos UwU
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public Figura getFigura() {
        return figura;
    }
    
    
}
