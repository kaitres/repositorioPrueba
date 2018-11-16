/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import controladores.InterfazController;
import java.util.ArrayList;

/**
 *
 * @author IP-ROUTE
 */
public class Diagrama {
    public  ArrayList<Entidad> entidades = new ArrayList<>();
    public  ArrayList<Relacion> relaciones = new ArrayList<>();
    public  ArrayList<Herencia> herencias = new ArrayList<>();
    
    public Diagrama() {
        this.entidades = new ArrayList<>();
        this.relaciones = new ArrayList<>();
        this.herencias = new ArrayList<>();
    }

     public Diagrama clon(){
        Diagrama aux= new Diagrama();
        for (Entidad entidad : entidades) {
            aux.addEntidad(entidad.clon());
        }
        for (Relacion relacion : relaciones) {
            aux.addRelacion(relacion.clon());
        }
        for (Herencia herencia : herencias) {
            aux.addHerencia(herencia.clon());
        }
        return aux;
    }
    public ArrayList<Herencia> getHerencias() {
        return herencias;
    }

    public void setHerencias(ArrayList<Herencia> herencias) {
        InterfazController.diagrama.herencias = herencias;
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
    
    public void addHerencia(Herencia herencia) {
        herencias.add(herencia);
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
        herencias.clear();
    }
}
