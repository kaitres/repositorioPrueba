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
public class Entidad {
    String nombre;
    Figura figura; //puede ser Rectangulo ya que solo se puede representar de esa manera
    ArrayList<Propiedad> propiedades = new ArrayList<>();
   

    public Entidad(String nombre) {//Lo hizo el Carlos UwU
        this.nombre = nombre;
        
    }
    
    public Entidad clon(){
        Entidad aux = new Entidad(nombre);
        aux.setFigura(figura.clon());
        for (Propiedad propiedade : propiedades) {
            aux.propiedades.add(propiedade.clon());                 
        }
        
        
        return aux;
    }
    
    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        this.propiedades = (ArrayList<Propiedad>) propiedades.clone();
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
    public void f(GraphicsContext gc){
        this.figura.tirarLinea(propiedades, gc);
        
    }
    @Override
    public String toString() {
        return nombre;
    }

    
    
}
