/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author cmsan
 */
public class Propiedad {
    String nombre;
    Tipo tipo;
    Elipse elip;

    public Propiedad(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        elip = new Elipse(nombre);
    }

    public Elipse getElip() {
        return elip;
    }

    public void setElip(Elipse elip) {
        this.elip = elip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    
}
