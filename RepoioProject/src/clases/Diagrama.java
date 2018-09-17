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
public class Diagrama {
    public static ArrayList<Entidad> entidades;
    public static ArrayList<Relacion> relaciones;

    public Diagrama() {
        this.entidades = new ArrayList<>();
        this.relaciones = new ArrayList<>(); 
    }

    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(ArrayList<Entidad> entidades) {
        this.entidades = entidades;
    }

    public ArrayList<Relacion> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(ArrayList<Relacion> relaciones) {
        this.relaciones = relaciones;
    }
    
    public void addEntidad(Entidad entidad){//Lo hizo el Carlos UwU
        entidades.add(entidad);
    }
    
    public void addRelacion(Relacion relacion){//Lo hizo el Carlos UwU
        relaciones.add(relacion);
    }
    
    public void clear(){//Lo hizo el Carlos UwU
        entidades.clear();
        relaciones.clear();
    }
}
