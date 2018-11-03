/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author IP-ROUTE
 */
public class Relacion {
    String nombre;
    Figura figura;
    ArrayList<Entidad> componentes = new ArrayList();
    ArrayList<Union> uniones = new ArrayList();
    ArrayList<Propiedad> propiedades = new ArrayList<>();
    /**
     * metodo que dibuja todas las uniones del objeto Relacion 
     * @param gc GraphicsContext del diagrama
     */
    public void dibujarUniones(GraphicsContext gc){
        for (Union union : uniones) {
            union.dibujarUnion(gc);
        }
    }

    public ArrayList<Union> getUniones() {
        return uniones;
    }
    
    /**
     * metodo que crea todas las uniones(objeto Union) del objeto Relacion 
     */
    public void crearUniones(){
        
        uniones.clear();
        
        for (Entidad e: componentes) {
            uniones.add(new Union(this.figura , e.figura));
        }
        if (componentes.size()==1){
            for (Entidad e: componentes) {
                uniones.add(new Union(this.figura , e.figura));
            }
        }
    }

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
        ArrayList<Entidad> nuevo = new ArrayList<>();
        for (Entidad componente : componentes) {
            nuevo.add(componente);
        }
        this.componentes = nuevo;
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
    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        this.propiedades = (ArrayList<Propiedad>) propiedades.clone();
        this.figura.propiedades = this.propiedades;
    }
    public void f(GraphicsContext gc){
        this.figura.tirarLinea(propiedades, gc);
        
    }
    
}
