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
public class Relacion {
    String nombre;
    Figura figura;
    ArrayList<Entidad> componentes;

    public Relacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.figura.nombre=nombre;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
        this.figura.nombre = nombre;
    }

    public Figura getFigura() {
        return figura;
    }
    
    public void setComponentes(ArrayList<Entidad> componentes) {
        this.componentes = componentes;
    }

    public ArrayList<Entidad> getComponentes() {
        return componentes;
    }
    
    public Entidad getComponente(int index) {
        return componentes.get(index);
    }

    public void addComponente(Entidad componente){
        componentes.add(componente);
    }
    
    public Entidad popComponente(int index){
        Entidad ent = componentes.get(index);
        componentes.remove(index);
        return ent;
    }
    
    
}
