/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author cmsan
 */
public class Propiedad {
    String nombre;
    Tipo tipo;
    Figura elip;
    ArrayList<Propiedad> propiedades;

    public Propiedad(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        elip = new Figura();
        elip.nombre = nombre;
        elip.elipse(370, 285);
    }
    
    public Propiedad(String nombre, Tipo tipo, ArrayList<Propiedad> propiedad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.propiedades = propiedad;
        elip = new Figura();
        elip.nombre = nombre;
        elip.elipse(370, 285);
    }
    public Figura getElip() {
        return elip;
    }

    public void setElip(Figura elip) {
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

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }
    
    @Override
    public String toString() {
        return nombre +" " +tipo;
    }
    
}
